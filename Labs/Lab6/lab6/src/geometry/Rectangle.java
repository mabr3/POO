package geometry;

public class Rectangle extends Form {
	/**
	 * @uml.property name = "altura"
	 */
	private int altura;
	/**
	 * @uml.property name = "largura"
	 */
	private int largura;
	
	public Rectangle(int pos_x,int pos_y, int altura, int largura) {
		super(pos_x, pos_y);
		this.altura=altura;
		this.largura=largura;
	}
	
	public int[] intersection (int y) {
		if(y==pos_y || y== altura-1 + pos_y) {
			int[] v = new int[largura];
			for(int i=0;i<largura;i++) {
				v[i]=i+pos_x;
			}
			return v;
		}
		if(y>pos_y && y<altura - 1 + pos_y){
			int[] v = new int[2];
			v[0]= pos_x; v[1] = largura -1 +pos_x;
			return v;
		}
		return new int[0];
	}
	

}
