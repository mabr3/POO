package individual;

import java.util.ArrayList;
import java.util.Random;

import simulation.Point;

/**
 * Abstract class for individuals to use, has certain needed parameters and methods to ensure the program works
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public abstract class AIndividual implements Comparable<AIndividual> {
	/**List of Points that form the path of the individual*/
	ArrayList<Point> path = new ArrayList<Point>();
	/**Cost of the path of the individual*/
	int cost;
	/**Distance of this individual to the final point*/
	int dist;
	/**Comfort of this individual*/
	double phi;
	/**Time of Death of this individual, no events from it can occurr after*/
	double ToD;
	
	/**Reference to the move event*/
	EvMove move;
	/**Reference to the death event*/
	EvDeath death;
	/**Reference to the reproduction event*/
	EvReproduction reproduction;
	
	/**Reference to the individual list*/
	AIndividualList ilist;
	
	/**Method to add a point to the individual's path*/
	public void addToPath(Point a){
		path.add(a);
	}
	
	public AIndividualList getList() {
		return ilist;
	}
	
	/**
	 * Gets the last point of a given path from the individual.
	 * @return the last Point of the path
	 */
	public Point getLastPoint() {
		return path.get(path.size()-1);
	}
	
	public EvDeath getDeath() {
		return death;
	}
	
	public ArrayList<Point> getPath(){
		return path;
	}
	
	/**
	 * Calculates the random value to increment to the current time.
	 * @param phi
	 * @param param
	 * @return random value
	 */
		
	public double calculateRandomValue(double phi, int param) {
		double mean = (double)(1-Math.log(1-phi))*param;
		Random random = new Random();
		double next = random.nextDouble();
		return -mean*Math.log(1.0-next);
	}
	
	/**
	 * See if this new point exists in the path and if exists remove elements that create a cycle in the path
	 * @param a - point to search in the path 
	 */
	void removeCycle(Point a) {
		int i=path.indexOf(a);
		int s=path.size();
		s--;
		for(;i<s;s--){
			path.remove(s);
		}
	}
	
	/*abstract methods that might differ if the individual list has a different implementation but that are used throughout the program
	 * 
	 */
	/**method to calculate the comfort (phi)*/
	abstract double calculatePhi();
	/**Method to add a death event**/
	abstract void addDeath();
	/**Method to add a reproduction event**/
	abstract void addReproduction();
	/**Method to add a move event**/
	abstract void addMove();
	/**Method to create a son after a reproduction**/
	abstract void createSon();
	/**Method to compare individuals and sort them**/
	abstract public int compareTo(AIndividual a);
	
	double getPhi() {
		return phi;
	}

	public int getCost() {
		return cost;
	}
	
}
