
package simulation;

import pec.PEC;
import individual.AIndividualList;


/**
 * Class for the simulation, implements the interface ISimulation. Has the required methods and parameters to initialize the simulation.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class Simulation implements ISimulation {
	
	/**Initial population of the simulation*/
	private int initpop;
	/**Initial population of the simulation*/
	private int finalinst;
	/**PEC for this simulation*/
	private PEC pec; 
	/**Grid for this simulation*/
	private Grid grid;
	/** List of individuals for this simulation*/
	private AIndividualList indivList;
		
	// Setters and Getters
	
	public int getInitpop() {
		return initpop;
	}
	public void setInitpop(int initpop) {
		this.initpop = initpop;
	}
	public PEC getPec() {
		return pec;
	}
	public void setPec(PEC pec) {
		this.pec = pec;
	}
	public AIndividualList getIndivList() {
		return indivList;
	}
	public void setIndivList(AIndividualList indivList) {
		this.indivList = indivList;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
		
	public int getFinalinst() {
		return finalinst;
	}
	public void setFinalinst(int finalinst) {
		this.finalinst = finalinst;
	}
	
	
	/**
	 * Construct the grid and saves the reference
	 */
	@Override
	public void addGrid(int cols, int rows) {
		grid = new Grid(cols, rows);	
	}
		
	/**
	 * Creates a PEC and puts is reference in individuals list
	 */
	public void addPEC() {
		pec = new PEC();
	}	
	
	/**
	 * Initializes the individuals list and the observations events. Also checks if the initial and final points are valid.
	 */
	public void simulate() {
		indivList.setGrid(grid);
		indivList.AddNew(initpop);
		if(indivList.getGrid().isObstacle(indivList.getGrid().getFinalpoint())==true) {
			System.out.println("Final point is an obstacle. Exiting");
			System.exit(1);
		}
		if(indivList.getGrid().isObstacle(indivList.getInitialpoint())==true) {
			System.out.println("Initial point is an obstacle. Exiting");
			System.exit(1);
		}
		pec.addEvent(new EvObservation((double) this.finalinst/20,indivList));
	}
}
