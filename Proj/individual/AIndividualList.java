package individual;

import java.util.ArrayList;

import pec.PEC;
import simulation.AGrid;

import simulation.Point;

/**
 * Abstract class for the individuals list. Has the methods and variables needed to ensure all rules are followed and to operate over the individuals.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public abstract class AIndividualList {
	/**PEC of this simulation*/
	PEC pec;
	/**Maximum population of this simulation*/
	int maxpop;
	/**Comfort sensitivity for this simulation*/
	int comfortsens;
	/**Parameter for the death event time calculation*/
	int death_param;
	/**Parameter for the reproduction event time calculation*/
	int reproduction_param;
	/**Parameter for the move event time calculation*/
	int move_param;
	
	/**Grid of this simulation*/
	AGrid grid;

	/**Initial point of the simulation*/
	Point initialpoint;
	/**Stores the best fit individual of the simulation*/
	AIndividual bestfit;
	/**Stores the individuals that are alive*/
	ArrayList<AIndividual> ilist=new ArrayList<AIndividual>();
	
	
	public void setMaxpop(int max) {
		maxpop = max;
	}
	
	public int getMaxpop() {
		return maxpop;
	}
	
	public void setComfortsens(int c) {
		comfortsens = c;
	}
	
	public int getComfortsens() {
		return comfortsens;
	}
	
	public void setInitialpoint(Point initialpoint) {
		this.initialpoint = new Point(initialpoint.getX(),initialpoint.getY());
	}
	
	public void setReproduction_param(int param) {
		reproduction_param = param;
	}
	
	public int getReproduction_param() {
		return reproduction_param;
	}
	
	public void setMove_param(int param) {
		move_param = param;
	}
	
	public int getMove_param() {
		return move_param;
	}
	public void setDeath_param(int param) {
		death_param = param;
	}
	
	public int getDeath_param() {
		return death_param;
	}
	
	public AIndividual getBestfit() {
		return bestfit;
	}
	
	public void setPec(PEC _pec) {
		pec=_pec;
	}
	public PEC getPec() {
		return pec;
	}
	
	/**
	 * Adds the individual to the list.
	 * @param aux - Individual to add to the list.
	 */
	void addIndividual(AIndividual aux) {
		ilist.add(aux);
	}
	
	/**
	 * Removes the individual to the list.
	 * @param indiv - Individual to remove from the list.
	 */
	void removeIndividual(AIndividual indiv) {
		ilist.remove(indiv);
	}
	/**Add a new individual at the beginning of the simulation*/
	abstract void addNewIndividual();
	/**Add the initial population of the simulation*/
	public abstract void AddNew(int initpop);
	/**Compare the given individual with the best fit */
	abstract void compareBest(AIndividual a);
	
	public void setGrid(AGrid _grid) {
		grid=_grid;
	}
	
	public AGrid getGrid() {
		return grid;
	}
	public Point getInitialpoint() {
		return initialpoint;
	}
	/**
	 * Updates the bestfit individual with a new one.
	 * @param a - new best fit individual
	 */
	void UpdateBestFit(AIndividual a) {
		bestfit =a;
	}

}
