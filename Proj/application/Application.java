package application;

import simulation.Simulation;
import simulation.XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import individual.IndividualList;

/**
 * Application class, contains the main method. It is from this class that the program is run.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class Application {	

	/**
	 * Reads and parses (using the SAX parser) an XML file, given as parameter for the program, then sets all the information needed in its 
	 * place. Starts the PEC and prints a final result.Also catches the exceptions thrown by the XML parser and the DTD:
	 *
	 * @param args - argument for the main function. Must be a .xml file which will be validated against a dtd.
	 */
	public static void main(String[] args) {

		/**New simulation object for this simulation**/
		Simulation sim = new Simulation();

		//Must have only one input
		if(args.length <1 || args.length>1) {
			System.out.println("Usage: Main <filename>");
			System.exit(1);
		}

		//Must be a .xml file
		if(!args[0].endsWith(".xml")) {
			System.out.println("Argument must be a .xml file. Exiting.");
			System.exit(1);

		}
		/**XML handler to read the XML file**/
		XML handler = new XML();
		/**XML parser to parse the information**/
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		//Validates against a dtd file
		saxParserFactory.setValidating(true);
		
		handler.setSim(sim);
		//In our simulation, it is used a list of type IndividualList.
		sim.setIndivList(new IndividualList());

		/*
		 * Reading and parsing the XML file catches the following exceptions:
		 * 
		 * @exception ParserConfigurationException -
		 * @exception FileNotFoundException -
		 * @exception SAXException -
		 * @exception IOException -
		 * @exception NumberFormatException -
		 * 
		 * The stack traces are not printed and each exception makes the program exit.
		 */
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse(new File(args[0]), handler);
		} catch (ParserConfigurationException e) {
			System.out.println("Error in the XML and/or DTD file! Exiting.");
			System.exit(0);
		}catch (FileNotFoundException e) {
			System.out.println("Error in the XML and/or DTD file! Exiting.");
			System.exit(0);
		} catch( SAXException e) {
			System.out.println("Error in the XML and/or DTD file! Exiting.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error in the XML and/or DTD file! Exiting.");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Error in the XML and/or DTD file! Exiting.");
			System.exit(0);
		} 

		//get the simulation object, perform needed actions before starting the PEC and print the final result 
		sim = handler.getSim();
		sim.simulate();
		sim.getPec().Start();
		System.out.println("Path of the best fit individual = "+ sim.getIndivList().getBestfit().getPath());
	}

}

