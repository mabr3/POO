package geometry;

public abstract class Form {
	
	protected int pos_x;
	protected int pos_y;
	
	public Form(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
	
	public abstract int[] intersection(int y);
	
	

}
