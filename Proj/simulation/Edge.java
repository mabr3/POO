
package simulation;
/**
 * Edge class, contains the information and methods on the edges with a higher cost than the normal one.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
class Edge {
	/**The cost of this edge**/
	int cost;
	/**The initial point of this edge*/
	private Point initialpoint;
	/**The final point of this edge*/
	private Point finalpoint;
	
	/**
	 * Edge constructor. Needs the information on its initial point,
	 * final point, and cost.
	 * 	
	 * @param cost - Cost for that given edge
	 * @param xini - X parameter for the initial point of the edge
	 * @param yini - Y parameter for the initial point of the edge
	 * @param xfin - X parameter for the final point of the edge
	 * @param yfin - Y parameter for the final point of the edge
	 */
	public Edge(int cost,int xini, int yini, int xfin, int yfin){
		initialpoint = new Point(xini,yini);
		finalpoint = new Point(xfin,yfin);
		this.cost=cost;
	}
	
	/**
	 * Updates the value of an edge because a higher value for this edge was found.
	 * @param a - The edge whose cost must be updated
	 */
	
	void  UpdateEdge(int a) {
		this.cost=a;
	}
	
	/**For testing and checking purposes
	 * 
	 */
	
	@Override
	public String toString() {
		return "\nEdge from " + initialpoint + "to " + finalpoint + "has cost " + cost + ".\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finalpoint == null) ? 0 : finalpoint.hashCode());
		result = prime * result + ((initialpoint == null) ? 0 : initialpoint.hashCode());
		return result;
	}

	/**
	 * Equals method overriden so that edges that have the same initial point and final point are equal, but also those who have the initial point of one equal to the final
	 * point of the other and the final point of one equal to the initial point of the other.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (finalpoint == null) {
			if (other.finalpoint != null)
				return false;
		}
		if (initialpoint == null) {
			if (other.initialpoint != null)
				return false;
		}
		if(!((finalpoint.equals(other.finalpoint) && initialpoint.equals(other.initialpoint)) || (finalpoint.equals(other.initialpoint) && initialpoint.equals(other.finalpoint))))
			return false;
		return true;
	}
}
