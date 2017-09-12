package edu.txstate.cs3320.claggett;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bio2 {
	
	
	private static ArrayList <String> headings    = new ArrayList <> ();
	private static ArrayList <String> paragraphs = new ArrayList <> ();
	private static final String INPUT_FILE  = "./iofiles/bio.txt";
	private static final String OUTPUT_FILE = "./iofiles/bio.html";
	
	private static BufferedWriter  writer = null;

	private static final String TITLE = "My Life";
	/**
	 * main method used to kickstart the program. Starts the reading of paragraphs
	 * initializes the output file, then writes to the output file
	 * @param args
	 */
	public static void main(String[] args) {
		
        readParagraphs(INPUT_FILE);
        initialize(OUTPUT_FILE);
        writeBio(TITLE);

	}
	
	/**
	 * Takes the param of html code and writes it to the bio.html file using buffered writer
	 * @param html
	 */
	private static void writeHTML(String html)
	{
		try {
			writer.write(html + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Takes the param of a string of the title. Inserts the correct html headings and writes the lines using writeHTML
	 * @param title
	 */
	private static void writeHTMLOpening (String title) 
	{
		writeHTML(HTMLTags.HTML_HEADER_START);
		writeHTML(HTMLTags.TITLE_START + title + HTMLTags.TITLE_END);
		writeHTML(HTMLTags.HTML_HEADER_END);
		writeHTML(HTMLTags.BODY_START);
	}
	
	/**
	 * Takes no param. Simply uses writeHTML to write the closing statement to the html file
	 */
	private static void writeHTMLClosing () 
	{
		writeHTML(HTMLTags.BODY_END);
		writeHTML(HTMLTags.HTML_END);
	}
	/**
	 * Takes the name of the input file path and reads the info in the file. If an exception occurs, readParagraphs returns 
	 * false 
	 * @param inputFileName
	 * @return successfulRead
	 */
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
	
	/**
	 * Uses a bufferedWriter to write each paragraph with the proper html tags
	 */
	private static void writeParagraphs () {
		for (int i=0; i<headings.size(); i++) {
		      writeHTML(HTMLTags.H1_START+ formatText(headings.get(i))+ HTMLTags.H1_END);
		      writeHTML(HTMLTags.PARAGRAPH_START);
		      writeHTML(paragraphs.get(i));
		      writeHTML(HTMLTags.PARAGRAPH_END);
		}
	}
	
	/**
	 * Kicks off writing to the outputfile. Writes the HTMLopening, writes the paragraphs, then writes the closing
	 * @param title
	 */
	private static void writeBio (String title) {
		writeHTMLOpening (title);
		writeParagraphs();
		writeHTMLClosing();
	}
	
	/**
	 * Initializes the bufferedWriter to create an output file named the param fileName
	 * @param fileName
	 */
	private static void initialize (String fileName) {
		try {
			writer = new BufferedWriter (new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * strips the "[h1]" portion of each header
	 * @param text
	 * @return
	 */
	private static String formatText (String text) {
		final String SYMBOL = "[h1]";
		final int      SYMBOL_LENGTH = SYMBOL.length();
		final  int NOT_FOUND = -1;
		StringBuilder stringBuilder = new StringBuilder(text);
		int indexOf = stringBuilder.indexOf(SYMBOL);
		if (indexOf != NOT_FOUND) {
			stringBuilder.replace(indexOf, indexOf+SYMBOL_LENGTH, "");
		}
		return stringBuilder.toString();
	}






}
