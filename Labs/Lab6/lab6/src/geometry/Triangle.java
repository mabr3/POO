package geometry;

public class Triangle extends Form {
	/**
	 * @uml.property name = "altura"
	 */
	private int altura;
	/**
	 * @uml.property name = "base"
	 */
	private int base;

	private float b;
	private float m;


	public Triangle(int pos_x, int pos_y,int base, int altura) {
		super(pos_x, pos_y);
		this.base = base;
		this.altura = altura;
		m = (float) altura/((float)base/(float)2);
		b = pos_y-m*pos_x;
	}

	public int[] intersection(int y) {
		int[] v;
		if(y==pos_y+altura-1){
			v=new int[base];
			for(int i=0;i<base;i++){
				v[i]=pos_x-base/2+i;
			}
		} else if(y>=pos_y && y<pos_y+altura-1){
			v=new int[2];
			v[0]=(int)((y-b)/m);
			float b2=pos_y+m*pos_x;
			v[1]=(int)((y-b2)/(-m));
		} else v=new int[0];

		return v;
	}

}
