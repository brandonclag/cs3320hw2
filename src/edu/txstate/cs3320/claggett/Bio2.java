package edu.txstate.cs3320.claggett;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bio2 {
	
	
	private static ArrayList <String> headings    = new ArrayList <> ();
	private static ArrayList <String> paragraphs = new ArrayList <> ();
	private static final String INPUT_FILE  = "./iofiles/bio.txt";
	private static final String OUTPUT_FILE = "./iofiles/bio.html";
	
	private static BufferedWriter  writer = null;

	private static final String TITLE = "My Life";

	public static void main(String[] args) {
		
        readParagraphs(INPUT_FILE);
        writeBio(TITLE);

	}
	
	
	private static void writeHTML(String html)
	{
		System.out.println(html);
	}
	
	private static void writeHTMLOpening (String title) 
	{
		writeHTML(HTMLTags.HTML_HEADER_START);
		writeHTML(HTMLTags.TITLE_START + title + HTMLTags.TITLE_END);
		writeHTML(HTMLTags.HTML_HEADER_END);
		writeHTML(HTMLTags.BODY_START);
	}
	
	
	private static void writeHTMLClosing () 
	{
		writeHTML(HTMLTags.BODY_END);
		writeHTML(HTMLTags.HTML_END);
	}
	
	@SuppressWarnings("resource")
	private static boolean readParagraphs(String inputFileName)
	{
		boolean successfulRead = true;//set to true by default, returns false if unsuccessful
		
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successfulRead = false;
			return successfulRead;
		}
		
		
		final int numberOfParagraphs = 4;
		for (int i = 0; i < numberOfParagraphs; i++) {
			String headerLine = null;
			try {
				headerLine = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				successfulRead = false;
				return successfulRead;
			}
			if (headerLine != null) 
				headings.add(headerLine);
			String paragraphLine = null;
			try {
				paragraphLine = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				successfulRead = false;
				return successfulRead;
			}
			if (paragraphLine != null)
				paragraphs.add(paragraphLine);
		}
		
		
		return successfulRead;

	}
	
	
	private static void writeParagraphs () {
		for (int i=0; i<headings.size(); i++) {
		      writeHTML(HTMLTags.H1_START+ headings.get(i)+ HTMLTags.H1_END);
		      writeHTML(HTMLTags.PARAGRAPH_START);
		      writeHTML(paragraphs.get(i));
		      writeHTML(HTMLTags.PARAGRAPH_END);
		}
	}
	
	
	private static void writeBio (String title) {
		writeHTMLOpening (title);
		writeParagraphs();
		writeHTMLClosing();
	}





}
