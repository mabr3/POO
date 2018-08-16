package simulation;

import java.util.ArrayList;

import java.util.Random;

/**
 * Grid class, contains all essential information about the grid used, as well as the methods needed.
 * Extends the abstract class Grid.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class Grid extends AGrid{
	/**ArrayList to store the edges with special cost**/
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	/**ArrayList to store the obstacles of the grid**/
	private ArrayList<Point> obstacles;
	
	/**
	 * Constructor. Only needs number or columns and rows for the grid.
	 * Puts 1 as maxcost in order to avoid errors.
	 * @param cols - number of columns
	 * @param rows - number of rows
	 */
	public Grid(int cols, int rows){
		super(cols, rows);
		this.maxcost=1;
	}
	
	//Getters and Setters
	
	public int getNum_zones() {
		return num_zones;
	}

	public void setNum_zones(int _num_zones) {
		num_zones = _num_zones;
	}

	public int getMaxcost() {
		return maxcost;
	}
	
	public Point getFinalpoint() {
		return finalpoint;
	}

	public int getNum_obstacles() {
		return num_obstacles;
	}
	
	
	public int getCols() {
		return colsnb;
	}
	
	public int getRows() {
		return rowsnb;
	}		
	
	
	public void setMaxcost(int maxcost) {
		this.maxcost = maxcost;
	}
	
	public void setFinalpoint(Point _finalpoint) {
		finalpoint = new Point(_finalpoint.getX(), _finalpoint.getY());
	}

	public void setNum_obstacles(int _num_obstacles) {
		obstacles = new ArrayList<Point>(_num_obstacles);
		num_obstacles = _num_obstacles;
	}
	
	//Methods
	
	/**
	 * Adds a Point to the obstacles list
	 * @param obst - Point where the obstacle resides
	 */
	
	void AddObstacle(Point obst) {
		if(!obstacles.contains(obst))
			obstacles.add(obst);
	}
	
	void AddZone() {
		
	}
	
	/**
	 * Creates the edges that constitute the special cost zone. Also verifies if there is already an edge and updates its cost if the the new cost is higher, calling the
	 * method ExistEdge().
	 * @param xini - X parameter of the initial point of the special cost zone
	 * @param yini - Y parameter of the initial point of the special cost zone
	 * @param xfin - X parameter of the final point of the special cost zone
	 * @param yfin - Y parameter of the final point of the special cost zone
	 * @param cost - Cost of that zone
	 */

	void AddZone(int xini, int yini, int xfin, int yfin, int cost){
		ArrayList<Edge> newEdges = new ArrayList<Edge>();

		int b;
		
		int aux_x;
		int aux_y;
		int i;
		
		if(xfin>xini) {
			aux_x=xini;
			while(aux_x<xfin) {
				newEdges.add(new Edge(cost, aux_x, yini, aux_x+1,yini));
				newEdges.add(new Edge(cost, aux_x, yfin, aux_x+1,yfin));
				aux_x++;
			}
		}else {
			aux_x=xfin;
			while(aux_x<xini) {
				newEdges.add(new Edge(cost,aux_x,yini,aux_x+1,yini));
				newEdges.add(new Edge(cost,aux_x,yfin,aux_x+1,yfin));
				aux_x++;
			}
		}
		
		if(yfin>yini) {
			aux_y=yini;
			while(aux_y<yfin) {
				newEdges.add(new Edge(cost, xini, aux_y, xini,aux_y+1));
				newEdges.add(new Edge(cost, xfin, aux_y, xfin,aux_y+1));
				aux_y++;
			}
		}else {
			aux_y=xfin;
			while(aux_y<yini) {
				newEdges.add(new Edge(cost,xini,aux_y,xini,aux_y+1));
				newEdges.add(new Edge(cost,xfin,aux_y,xfin,aux_y+1));
				aux_y++;
			}
		}
		
		i=0;
		while(i<newEdges.size()) {
			Edge a= newEdges.get(i);
			b = ExistEdge(a);
			if(b==0) {
				edges.add(a);
			}
			i++;
		}
	}
	
	/**
	 * Method to check if the given edge exists in the ArrayList of edges and return its cost if it does. Also updates the edge if it exists and the new edge's cost is higher.
	 * The equals method in Edge is overridden to return true if the edge is up->down or down->up as well as if is left->right or right->left.
	 * Returns the cost of the edge if it was found. If not, returns 0, informing that there is no edge with special cost corresponding to the edge given.
	 * @param a = Edge we are looking for in the list of edges with special cost. Cost must be updated if its higher than the one stored.
	 */
	
	private int ExistEdge(Edge a) {
		for(Edge aux : this.edges) {
			if(aux.equals(a)){
				if(aux.cost < a.cost){
					aux.UpdateEdge(a.cost);
				}
				return aux.cost;
			}
		}
		return 0;
	}

	/**
	 * Method to calculate the cost of a given path received as input. For each set of two Points, checks if the edge formed by them is present on the ArrayList of edges.
	 * @param a - List of Points that form the path of which we want to know the cost
	 * @return the value of the cost of the path
	 */
	public int calculateCost(ArrayList<Point> a) {
		int cost=0;
		int i=0;
		Integer plus=0;
		while(i<a.size()-1){
			plus=0;
			Edge e = new Edge(0,a.get(i).getX(), a.get(i).getY(),a.get(i+1).getX(),a.get(i+1).getY());
			if(edges!= null) {
				if(edges.contains(e)) { //Only needs to get the cost if it exists in the list of edges.
					plus = ExistEdge(e);
					if(plus!=0) {
						cost+=plus;
					}
				}else {
					cost++;
				}
			}
			i++;
		}
		return cost;
	}
	

	/**
	 * Checks if the given point is an obstacle, by checking if the obstacle list contains that Point.
	 * Returns true if it does, else returns false.
	 * * @param a- Point to test
	 */
	
	boolean isObstacle(Point a) {
			if(obstacles.contains(a)) {
				return true;
			}
		return false;
	}
	
	/**
	 * Method to decide which point should the individual follow next, based on the generation of a random number between 0 and 1. Checks what possible moves it can
	 * make (based on the size of the grid and the obstacles that exist), in the following order:UP,RIGHT,DOWN,LEFT. The possible moves are stored in an ArrayList
	 * from which a position is chosen based on the random number generated and the amount of options available. 
	 * Returns the point to where the individual is going to move.
	 * @param a - Actual point of the individual
	 * @return Point - Next point of the individual
	 */
	
	
	public Point nextPoint(Point a) {
		double div;
		int n;
		Point Pleft, Pdown, Pup,Pright;		
		Random random = new Random();
		double prob = random.nextDouble();
		
		ArrayList<Point> possibleMoves = new ArrayList<Point>();
		
		if(a.posy + 1 <= rowsnb && !isObstacle(Pup = new Point(a.posx, a.posy +1))){
			possibleMoves.add(Pup);
		}
		if(a.posy - 1 >0 && !isObstacle(Pdown = new Point(a.posx, a.posy -1))){
			possibleMoves.add(Pdown);
		}
		if(a.posx + 1 <= colsnb && !isObstacle(Pright = new Point(a.posx+1, a.posy))){
			possibleMoves.add(Pright);
		}
		if(a.posx - 1 > 0 && !isObstacle(Pleft = new Point(a.posx-1, a.posy))){
			possibleMoves.add(Pleft);
		}

		if(possibleMoves.size() > 1) {
			div = (double) 1/possibleMoves.size();
			for(n=0;n<possibleMoves.size();n++){
				if(prob>n*div && prob <=(n+1)*div) {
					return possibleMoves.get(n);
				}
			}
		}
		
		return possibleMoves.get(0);
	}
	
	/**
	 * For testing and checking purposes.
	 */
	
	@Override
	public String toString() {
		return "Grid [maxcost =" + maxcost + ";\n colsnb=" + colsnb + ", rowsnb=" + rowsnb + ", obstacles=" + obstacles + ", Edges"
				+ edges + "]";
	}
	
	
}
