package simulation;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML class, where the parsing of the XML file is done and the values needed are read from the file, while checking for errors in the input, either
 * related to the way the file is written or with the permitted values.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class XML extends DefaultHandler {
	
	/**X coordinate of the initial point of a zone*/
	private int xinitial;
	/**Y coordinate of the initial point of a zone*/
	private int yinitial;
	/**X coordinate of the final point of a zone*/
	private int xfinal;
	/**Y coordinate of the final point of a zone*/
	private int yfinal;
	/**Counter for the number of obstacles in the XML file*/
	private int count_obs;
	/**Counter for the number of special cost zones in the XML file*/
	private int count_zones;

	//initialize simulation object and set attributes
	/**Instance of the simulation to be used*/
	private Simulation sim;
	/**Boolean value to indicate there's a value to be read after the zone's attributes*/
	private boolean bcostzone = false;
	
	/**
	 * Read all of the XML file and compare the elements with their expected names. Get the corresponding attributes and store them where they are
	 * needed for the program to run correctly.
	 * 
	 * @exception SAXException - Errors occurred while using the SAX parser
	 * 
	 */
	@Override
	public void startElement(String uri, 
			String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("simulation")) {
			sim.addPEC();
			sim.getIndivList().setPec(sim.getPec());
			sim.getPec().setFinalinst(Integer.parseInt(attributes.getValue("finalinst")));
			sim.setFinalinst(Integer.parseInt(attributes.getValue("finalinst")));
			if ( sim.getFinalinst() <= 0)
			{
				System.out.println("Finalinst must be positive! Exiting.");
				System.exit(0);
			}

			sim.setInitpop(Integer.parseInt(attributes.getValue("initpop")));
			if ( sim.getInitpop() <= 0)
			{
				System.out.println("Initpop must be positive! Exiting.");
				System.exit(0);
			}

			sim.getIndivList().setMaxpop(Integer.parseInt(attributes.getValue("maxpop")));
			if ( sim.getIndivList().getMaxpop() < sim.getInitpop())
			{
				System.out.println("Maxpop must be equal or bigger than initpop! Exiting.");
				System.exit(0);
			}

			sim.getIndivList().setComfortsens(Integer.parseInt(attributes.getValue("comfortsens")));
			if ( sim.getIndivList().getComfortsens() < 0)
			{
				System.out.println("Comfort must be positive! Exiting.");
				System.exit(0);
			}

		} else if (qName.equalsIgnoreCase("grid")) {
			sim.addGrid(Integer.parseInt(attributes.getValue("colsnb")), Integer.parseInt(attributes.getValue("rowsnb")));
			if ( sim.getGrid().getCols() <= 0 || sim.getGrid().getRows() <= 0)
			{
				System.out.println("Cols and rows must be positive! Exiting.");
				System.exit(0);
			}

		} else if (qName.equalsIgnoreCase("initialpoint")) {
			Point initialpoint = new Point(Integer.parseInt(attributes.getValue("xinitial")),Integer.parseInt(attributes.getValue("yinitial")));
			sim.getIndivList().setInitialpoint(initialpoint);
			if ( Integer.parseInt(attributes.getValue("xinitial")) < 0 || Integer.parseInt(attributes.getValue("xinitial")) > sim.getGrid().getCols())
			{
				System.out.println("Initial position for x is not possible! Exiting.");
				System.exit(0);
			}
			if ( Integer.parseInt(attributes.getValue("yinitial")) < 0 || Integer.parseInt(attributes.getValue("yinitial")) > sim.getGrid().getRows())
			{
				System.out.println("Initial position for y is not possible! Exiting.");
				System.exit(0);
			}

		} else if (qName.equalsIgnoreCase("finalpoint")) {
			Point finalpoint = new Point(Integer.parseInt(attributes.getValue("xfinal")),Integer.parseInt(attributes.getValue("yfinal"))); 
			sim.getGrid().setFinalpoint(finalpoint);
			if ( Integer.parseInt(attributes.getValue("xfinal")) < 0 || Integer.parseInt(attributes.getValue("xfinal")) > sim.getGrid().getCols())
			{
				System.out.println("final position for x is not possible! Exiting.");
				System.exit(0);
			}
			if ( Integer.parseInt(attributes.getValue("yfinal")) < 0 || Integer.parseInt(attributes.getValue("yfinal")) > sim.getGrid().getRows())
			{
				System.out.println("Final position for y is not possible! Exiting.");
				System.exit(0);
			}
		}
		else if (qName.equalsIgnoreCase("specialcostzones")) {
			sim.getGrid().setNum_zones(Integer.parseInt(attributes.getValue("num")));

		}
		else if (qName.equalsIgnoreCase("zone")) {
			if(count_zones>=sim.getGrid().getNum_zones()) {
				System.out.println("More zones than declared. Existing.");
				System.exit(0);
			}
			xinitial = Integer.parseInt(attributes.getValue("xinitial"));
			yinitial = Integer.parseInt(attributes.getValue("yinitial"));
			xfinal = Integer.parseInt(attributes.getValue("xfinal"));
			yfinal = Integer.parseInt(attributes.getValue("yfinal"));

			if ( xinitial < 0 || xinitial > sim.getGrid().getCols())
			{
				System.out.println("Initial position for x_zone is not possible! Exiting.");
				System.exit(0);
			}
			if ( yinitial < 0 || yinitial > sim.getGrid().getRows())
			{
				System.out.println("Initial position for y_zone is not possible! Exiting.");
				System.exit(0);
			}
			if ( xfinal < 0 || xfinal > sim.getGrid().getCols())
			{
				System.out.println("Final position for x_zone is not possible! Exiting.");
				System.exit(0);
			}
			if ( yfinal < 0 || yfinal > sim.getGrid().getRows())
			{
				System.out.println("Final position for y_zone is not possible! Exiting.");
				System.exit(0);
			}

			count_zones++;
			bcostzone = true;//indicates there is more to be read - the cost of the zone
		}
		else if (qName.equalsIgnoreCase("obstacles")) {
			sim.getGrid().setNum_obstacles(Integer.parseInt(attributes.getValue("num")));
			if ( sim.getGrid().getNum_obstacles() < 0)
			{
				System.out.println("Number of obstacles can't be negative! Exiting.");
				System.exit(0);
			}
		}
		else if (qName.equalsIgnoreCase("obstacle")) {
			if(count_obs>=sim.getGrid().getNum_obstacles()) {
				System.out.println("More obstacles than declared. Exiting");
			}
			Point obs = new Point(Integer.parseInt(attributes.getValue("xpos")),Integer.parseInt(attributes.getValue("ypos"))); 
			sim.getGrid().AddObstacle(obs);
			if ( Integer.parseInt(attributes.getValue("xpos")) < 0 || Integer.parseInt(attributes.getValue("xpos")) > sim.getGrid().getCols())
			{
				System.out.println("Obstacle position for x is not possible! Exiting.");
				System.exit(0);
			}
			if ( Integer.parseInt(attributes.getValue("ypos")) < 0 || Integer.parseInt(attributes.getValue("ypos")) > sim.getGrid().getRows())
			{
				System.out.println("Obstacle position for y is not possible! Exiting.");
				System.exit(0);
			}
			count_obs++;
		}
		else if (qName.equalsIgnoreCase("death")) {
			sim.getIndivList().setDeath_param(Integer.parseInt(attributes.getValue("param")));

			if ( sim.getIndivList().getDeath_param() < 0)
			{
				System.out.println("Death param must be positive! Exiting.");
				System.exit(0);
			}
		}
		else if (qName.equalsIgnoreCase("reproduction")) {

			sim.getIndivList().setReproduction_param(Integer.parseInt(attributes.getValue("param")));
			if ( sim.getIndivList().getReproduction_param() < 0)
			{
				System.out.println("Reproduction param must be positive! Exiting.");
				System.exit(0);
			}
		}
		else if (qName.equalsIgnoreCase("move")) {

			sim.getIndivList().setMove_param(Integer.parseInt(attributes.getValue("param")));  
			if ( sim.getIndivList().getMove_param() < 0)
			{
				System.out.println("Move param must be positive! Exiting.");
				System.exit(0);
			}
		}
	}

	/**
	 * End of the XML file reading.
	 */

	@Override
	public void endElement(String uri, 
			String localName, String qName) throws SAXException {
	}

	/**
	 * Check for and get the value of the cost of the special cost zone.
	 */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bcostzone) {
			//cost of the special zone
			int cost = Integer.parseInt(new String(ch, start, length));
			if ( cost <= 0)
			{
				System.out.println("Special cost must be positive! Exiting.");
				System.exit(0);
			}
			sim.getGrid().AddZone(xinitial, yinitial, xfinal, yfinal, cost);
			if( cost > sim.getGrid().getMaxcost())
			{
				sim.getGrid().setMaxcost(cost);
			}
			bcostzone = false;//nothing more to be read
		}

	}

	public Simulation getSim() {
		return sim;
	}
	public void setSim(Simulation sim) {
		this.sim = sim;
	}
}