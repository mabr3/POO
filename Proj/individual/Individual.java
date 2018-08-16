package individual;

import simulation.Point;

import java.util.ArrayList;
/**
 * Abstract class for the individual used. Has constructors and methods to add the three different events related with events, as well as creating a son.
 * Has a comparable depending on the value of the individual's comfort (phi).
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */

public class Individual extends AIndividual implements Comparable<AIndividual> {
	/**Reference to the individual list to which this individual belongs to*/
	private IndividualList ilist;
	
	/**List of Points that form the path of the individual*/
	private ArrayList<Point> path = new ArrayList<Point>();
	/**Cost of the path of the individual*/
	private int cost;
	/**Distance of this individual to the final point*/
	private int dist;
	/**Comfort of this individual*/
	private double phi;
	/**Time of Death of this individual, no events from it can occurr after*/
	private double ToD;
	
	/**Reference to the move event*/
	private EvMove move;
	/**Reference to the death event*/
	private EvDeath death;
	/**Reference to the reproduction event*/
	private EvReproduction reproduction;
	
	/**
	 * Individual constructor. Adds the point received - initial point.
	 * @param a - initialpoint of the individual
	 */
	public Individual(Point a) {
		path = new ArrayList<Point>();
		path.add(a);
	}
	
	/**
	 * No-arg constructor. Only to instantiate a new object of type Individual, will be used for a son.
	 */
	
	public Individual() {
	}
	
	@Override
	public double getPhi() {
		return this.phi;
	}
	
	/**
	 * Creates the son of an individual. Calculates the new path of individual, based on 90% of the path of the parent
	 * and the rest determined by the generation of a random number between 0 and 1.
	 * Then add the son to the individuals list.
	 */
	
	public void createSon() {
		Individual son = new Individual();
		
		int i,j;
		double p;
		
		for(j= 0;j<Math.ceil((double) 0.9*this.path.size());j++) {
			son.path.add(this.path.get(j));
		}
		
		if(j!=this.path.size()) {
			p= Math.ceil(this.phi * 0.1*this.path.size());
			for(i=0;i<p;i++) {
				if(j+i<this.path.size()) {
					son.path.add(this.path.get(j+i));
				}
			}
		}
		son.ilist=this.ilist;
		this.ilist.addIndividual(son);
		
	}
	
	/**
	 * Compares phi of individuals, in order to order the individuals for the epidmics event, by descending order of their comfort (phi).
	 * @param a - Other individual to compare phi 
	 * @return value that says if phi is bigger, smaller or equal
	 */
	//Comparable to order in epidemics
	
	public int compareTo(AIndividual a) {
		if (this.phi > a.getPhi()) {
			return -1;
		}

		if (this.phi < a.getPhi()) {
			return 1;
		}

			return 0;
		}
	
	
	//Getters and Setters
	/**
	 * Gets the last point of the path of the individual from which this method is called.
	 */
	public Point getLastPoint(){
		return this.path.get(this.path.size() - 1);
	}

	
	public double getTime(int param) {
		return this.calculateRandomValue(this.getPhi(), param);
	}
	public IndividualList getList() {
		return this.ilist;
	}
	
	void setIndividualList(IndividualList ilist){
		this.ilist = ilist;
	}
	
	public EvDeath getDeath() {
		return this.death;
	}

	public void setDeath(EvDeath death) {
		this.death = death;
	}

	public EvMove getMove() {
		return this.move;
	}

	public void setMove(EvMove move) {
		this.move = move;
	}

	public EvReproduction getReproduction() {
		return this.reproduction;
	}

	public void setReproduction(EvReproduction reproduction) {
		this.reproduction = reproduction;
	}
	
	

	public ArrayList<Point> getPath() {
		return this.path;
	}
	/**
	 * Receives a path and sets this individual's path equal to the one received. Used when creating a a son.
	 * @param path
	 */
	public void setPath(ArrayList<Point> path) {
		if(this.path!=null) {
			this.path.clear();
		}else {
			this.path=new ArrayList<Point>();
		}
		
		for(int i=0;i<path.size();i++) {
			this.path.add(path.get(i));
		}
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	
	/**
	 * Add a point to the individual path
	 * @param a - point to add to the path 
	 */
	@Override
	public void addToPath(Point a) {
		if(!path.contains(a)) {
			path.add(a);
			this.calculatePhi();
		}else{
			this.removeCycle(a);
		}
		
		ilist.compareBest(this);
	}
		
	/**
	 * Calculates the individuals comfort according with the given function
	 * @return comfort
	 */
	
	double calculatePhi() {
		double f=0;
		int length=path.size()-1;
		this.cost = ilist.grid.calculateCost(this.path);
		this.dist = ilist.grid.calculateDist(this.path.get(path.size()-1));
		double a = (double) Math.pow(1-(((double) this.cost - (double)length +2)/(((double) ilist.grid.getMaxcost() -1)*(double)length + 3)),(double) ilist.getComfortsens());
		double b = (double) Math.pow(1-((double) this.dist/(1+ (double) ilist.getGrid().getRows() + (double)ilist.grid.getCols())), (double) ilist.getComfortsens());
		f=a*b;
		this.phi=f;
		return f;
	}
	
	/**
	 * Add this individual move event to the pec
	 */
	public void addMove() {
		double t = ilist.getPec().getTime() + this.calculateRandomValue(this.phi, ilist.getMove_param());
		if(t<this.ToD) {
			this.move = new EvMove(t,this);
			ilist.getPec().addEvent(this.move);
		}else {
			this.move=null;
			
		}
	}
	
	/**
	 * Add this individual reproduction event to the pec
	 */
	public void addReproduction() {
		double t = this.ilist.getPec().getTime() + this.calculateRandomValue(this.phi, ilist.getReproduction_param());
		if(t<this.ToD) {
			this.reproduction = new EvReproduction(t,this);
			this.ilist.getPec().addEvent(this.reproduction);
		}else {
			this.reproduction = null;
		}
	}
	
	/**
	 * Add this individual death event to the pec
	 */
	public void addDeath() {
		double t = ilist.getPec().getTime() + this.calculateRandomValue(this.phi, ilist.getDeath_param());
		this.ToD = t;
		this.death = new EvDeath(t,this, ilist);
		ilist.getPec().addEvent(this.death);
	}
	
}
