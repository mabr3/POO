package simulation;
/**
 * Point, contains the coordinates of a given Point.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class Point {
	
	 /**X coordinate of the point**/
	 int posx;
	 /**Y coordinate of the point**/
	 int posy;
	
	/**
	 * Constructor of the Point.
	 * 
	 * @param posx - X coordinate of the Point to be constructed
	 * @param posy - Y coordinate of the Point to be constructed 
	 */
	
	public Point(int posx, int posy){
		this.posx=posx;
		this.posy=posy;
	}
	
	public int getX() {
		return posx;
	}
	
	public int getY() {
		return posy;
	}
	
	/**
	 * Print Point in the (x,y) format.
	 */

	@Override
	public String toString() {
		return "(" + posx + "," + posy + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posx;
		result = prime * result + posy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (posx != other.posx)
			return false;
		if (posy != other.posy)
			return false;
		return true;
	}
	
	

}
