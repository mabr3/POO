package individual;

import simulation.Point;
import simulation.EvObservation;
import simulation.Grid;

import java.util.ArrayList;

import pec.Event;

import pec.PEC;

import java.util.Collections;


import java.util.Random;

/**
 * Class for the individual list. Takes care of the individuals that exist, updating the best path when needed
 * and doing epidemics when the population exceeds the maximum population.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class IndividualList extends AIndividualList {
		
	/**Initial point of the simulation*/
	private Point initialpoint;
	/**List of individuals*/
	private ArrayList<Individual> ilist = new ArrayList<Individual>();
	/**Best fit individual*/
	private Individual bestfit = new Individual();

	// Setters and Getters
	
	public Individual getBestfit() {
		return bestfit;
	}


	public int getMaxpop() {
		return maxpop;
	}
	public void setMaxpop(int maxpop) {
		this.maxpop = maxpop;
	}
	public int getComfortsens() {
		return comfortsens;
	}
	public void setComfortsens(int comfortsens) {
		this.comfortsens = comfortsens;
	}
	public int getDeath_param() {
		return death_param;
	}
	public void setDeath_param(int death_param) {
		this.death_param = death_param;
	}
	public int getReproduction_param() {
		return reproduction_param;
	}
	public void setReproduction_param(int reproduction_param) {
		this.reproduction_param = reproduction_param;
	}
	public int getMove_param() {
		return move_param;
	}
	public void setMove_param(int move_param) {
		this.move_param = move_param;
	}
	public Point getInitialpoint() {
		return initialpoint;
	}

	public PEC getPec() {
		return pec;
	}

	public void setPec(PEC pec) {
		this.pec = pec;
	}


	public void setInitialpoint(Point initialpoint) {
		this.initialpoint = new Point(initialpoint.getX(),initialpoint.getY());

	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}


	/**
	 * Compares a given individual to the best fit individual. The best fit must be updated in five different situations:
	 * If there's no best fit yet;
	 * If the best fit hasn't arrived at the final point and the given individual has;
	 * If both have arrived but the given individual did it with a smaller cost;
	 * If both have arrived and both did it with the same cost, but the the given individual has a greater comfort;
	 * If none have arrived at the final point and the given individual has a greater comfort.
	 * 
	 * @param _a - Individual with new path to compare with the bestfit
	 */
	
	public void compareBest(AIndividual _a) {

		if(_a instanceof Individual) {
			Individual a = (Individual) _a;

			if(bestfit.getPath().size() ==0) {//no best fit yet
				UpdateBestFit(a);
				return;
			}
			if(grid.isFinal((bestfit.getPath().get(bestfit.getPath().size()-1)))==false && grid.isFinal(a.getPath().get(a.getPath().size()-1))==false) {
				if(a.getPhi()>bestfit.getPhi()){//both haven't arrived, check for comfort

					UpdateBestFit(a);
					return;
				}
			}

			if(grid.isFinal(bestfit.getPath().get(bestfit.getPath().size()-1))==false && grid.isFinal(a.getPath().get(a.getPath().size()-1))==true){
				//best fit hasn't arrived at the final point, but the given individual has
				UpdateBestFit(a);
				return;
			}else {
				if(grid.isFinal(bestfit.getPath().get(bestfit.getPath().size()-1))==true && grid.isFinal(a.getPath().get(a.getPath().size()-1))==true) {
					if(a.getCost()<bestfit.getCost()) {
						//both have arrived, but the given individual has a smaller cost
						UpdateBestFit(a);
						return;
					}else {
						if(a.getCost()==bestfit.getCost() && a.getPhi()>bestfit.getPhi()) {
							//both have arrived, with the same cost, but the given individual has a bigger comfort
							UpdateBestFit(a);
							return;
						}
					}
				}
			}
		}

	}



	/**
	 * Receives the number of individuals to create, creates them and checks for epidemics.
	 * Also updates the bestfit because there is none yet.
	 * @param initpop - Number of initial individuals to create.
	 */
	public void AddNew(int initpop) {
		for(int i=0;i<initpop;i++) {
			this.addNewIndividual();
			if(this.ilist.size() > this.maxpop) {
				this.epidemic();
			}
		}
		UpdateBestFit(this.ilist.get(0));
	}


	/**
	 * Creates and add individual with initial point to the List, calculates the phi and adds its events
	 */

	public void addNewIndividual() {

		Individual aux = new Individual(this.initialpoint);
		aux.setIndividualList(this);
		aux.setPhi(aux.calculatePhi());

		aux.addDeath();
		aux.addMove();
		aux.addReproduction();
		this.ilist.add(aux);
	}

	/**
	 * Adds an individual to the List and calculates his comfort (phi) and adds his events.
	 * @param aux - Individual to add
	 */
	@Override
	public void addIndividual(AIndividual aux) {

		aux.ilist =this;
		aux.phi =aux.calculatePhi();

		aux.addDeath();
		aux.addMove();
		aux.addReproduction();
		this.ilist.add((Individual) aux);

		if(this.ilist.size() > this.maxpop) {
			this.epidemic();
		}
	}

	/**
	 * Removes the individual from the list of individuals.
	 * @param indiv - Individual to add
	 */
	@Override
	public void removeIndividual(AIndividual indiv) {
		ilist.remove(indiv);
	}


	/**
	 * Does the epidemic event, i.e, when the population exceeds the maximum population defined for this simulations,
	 * some must be eliminated. For that, the 5 individuals with greater comfort survive and the rest survive based on the observation of a random variable
	 * and the individual's comfort.
	 * Creates a new set of events to be used in the PEC, by using only the events from the individuals that survived and sending them to the AddNewEvents method in the 
	 * Pec.
	 */
	private void epidemic() {
		int i=0;
		double prob =0.0;
		ArrayList<Individual> aux;

		Collections.sort(this.ilist);
		if(ilist.size()>5) {
			aux = new ArrayList<Individual>(this.ilist.subList(0,5));
			Random random = new Random();
			for(i=5;i<ilist.size();i++) {

				prob = random.nextDouble();
				if(prob <= this.ilist.get(i).getPhi()) {
					aux.add(this.ilist.get(i));
				}
			}

			ArrayList<Event> new_events =new ArrayList<Event>();
			for(i=0;i<aux.size();i++) {
				if(aux.get(i).getDeath().getTime()<pec.getFinalinst())
					new_events.add(aux.get(i).getDeath());
				if(aux.get(i).getMove()!=null && aux.get(i).getMove().getTime() < pec.getFinalinst()) {
					new_events.add(aux.get(i).getMove());
				}
				if(aux.get(i).getReproduction()!=null && aux.get(i).getReproduction().getTime() < pec.getFinalinst()) {
					new_events.add(aux.get(i).getReproduction());
				}
			}
			
			if(getPec().getObservations()<20) {
				double t =(((double)getPec().getObservations()+1)*getPec().getFinalinst())/20;
				new_events.add(new EvObservation(t,this));
			}
			pec.addNewEvents(new_events);
			this.ilist.clear();
			this.ilist=aux;
		}
		return;
	}


	/**
	 * Update the values of the bestfit individual
	 * @param indiv - Individual with the parameters that are now the best
	 */
	void UpdateBestFit(AIndividual indiv) {
		this.bestfit.setPath(indiv.getPath());
		this.bestfit.setCost(indiv.getCost());
		this.bestfit.setPhi(indiv.getPhi());	
	}


	/**
	 * Writes the observation parameters in the console
	 * @return String to write in the console
	 */
	@Override
	public String toString() {
		if (grid.isFinal(bestfit.getLastPoint()))
		{
			return "Observation "+ pec.getObservations() +": \n\t\tPresent instant:"+pec.getTime()+"\n\t\tNumber of realized events: "+pec.getCount_events() +"\n\t\tPopulation size:" +this.ilist.size()+ "\n"
					+ "\t\tFinal point has been hit: yes"+ "\n\t\tPath of the best fit individual:"+ this.bestfit.getPath()+" \n\t\tCost:"+this.bestfit.getCost()+"\n";
		}
		else
		{
			return "Observation "+ pec.getObservations() +": \n\t\tPresent instant:"+pec.getTime()+"\n\t\tNumber of realized events: "+pec.getCount_events() +"\n\t\tPopulation size:" +this.ilist.size() + "\n"
					+ "\t\tFinal point has been hit: no" + "\n\t\tPath of the best fit individual:"+ this.bestfit.getPath()+" \n\t\tComfort:"+this.bestfit.getPhi()+"\n";
		}		
	}
}
