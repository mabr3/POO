package geometry;
import java.lang.Math;

public class Circle extends Form {

	/**
	 * @uml.property name="raio"	
	 */
	
	private int raio;
	
	public Circle(int pos_x, int pos_y, int raio) {
		super(pos_x,pos_y);
		this.raio=raio;
	}
	
	public int[] intersection (int y) {
		int[] v = new int[2];
		
		if(y<=pos_y+raio && y>=pos_y-raio) {
			v[0] = (int) Math.sqrt(raio*raio-(y-pos_y)*(y-pos_y))+pos_x;
			v[1] = -(int) Math.sqrt(raio*raio-(y-pos_y)*(y-pos_y))+pos_x;
			return v;
		}
		
		return new int[0];
	}
}
