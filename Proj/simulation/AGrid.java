package simulation;

import java.util.ArrayList;
/**
 * AGrid abstract class, contains all essential information about the grid used, as well as the methods needed to correctly use the program
 * even if with a different implementation for the actual grid used, that will extend this class.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public abstract class AGrid {
	/**Number of columns of the grid**/
	int colsnb;
	/**Number of rows of the grid**/
	int rowsnb;
	/**Maximum cost for and edge of the grid**/
	int maxcost;
	/**Number special cost zones in the grid**/
	int num_zones;
	/**Number of obstacles in the grid**/
	int num_obstacles;
	/**Final point to where the individual must go to**/
	Point finalpoint;
	
	/**
	 * Grid constructor, sets the number of columns and rows. 
	 * @param cols - number of columns
	 * @param rows - number of rows
	 */
	
	public AGrid(int cols, int rows) {
		colsnb = cols;
		rowsnb = rows;
	}
	
	public Point getFinalpoint() {
		return finalpoint;
	}
	public int getMaxcost() {
		return maxcost;
	}
	
	/*abstract methods because the way someone stores their obstacles/zones might be different than ours, but these methods are called in
	* actions throughout the program.
	*/
	
	/**Method to add a special cost zone*/
	abstract void AddZone();
	/**Method to add an obstacle*/
	abstract void AddObstacle(Point a);
	/**Method to check if a point is an obstacle*/
	abstract boolean isObstacle(Point a);
	/**Method to calculate the cost of a given path*/
	public abstract int calculateCost(ArrayList<Point> a);
	/**Method to return the next Point to where an individual must move to*/
	public abstract Point nextPoint(Point a);
	
	//end abstract methods
	
	/**
	 * Calculates the minimum number of edges needed to reach the final point, from the input point a. Disregards obstacles
	 * @param a - Point from where the number of edges must be calculated
	 * @return the value of that distance
	 */
	
	public int calculateDist(Point a) {
		int c = 0;
		c+=Math.abs(a.posx-finalpoint.posx);
		c+=Math.abs(a.posy-finalpoint.posy);
		return c;
	}
	
	/**
	 * Check if a point is the final point of the simulation.
	 * * @param a- Point of individual to test
	 */
	public boolean isFinal(Point a) {
		return finalpoint.equals(a);
	}
	
	public int getRows() {
		return rowsnb;
	}
	public int getCols() {
		return colsnb;
	}

}
