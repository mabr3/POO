package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Image {
	
	/** 
	 * @uml.property name="forms"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="imagem:asciiart.geom.Form"
	 */
	private ArrayList<Form> formas = new ArrayList<Form>();
	/**
	 * @uml.property  name="height"
	 */
	private int height;
	/**
	 * @uml.property  name="width"
	 */
	private int width;
	
	public Image(int height, int width) {
		this.height=height;
		this.width=width;
	}
	
	public void addForm(Form f) {
		formas.add(f);
	}
	
	public String line(int y) {
		char[] linha = new	char[width];
		Arrays.fill(linha,' ');
		int[] aux;
		Iterator<Form> it = formas.iterator();
		Form f;
		
		while(it.hasNext()) {
			f= (Form)it.next();
			aux=f.intersection(y);
			if(aux==null) continue;
			
			for(int i=0; i<aux.length;i++) {
				if(aux[i]>=0 && aux[i]<width) {
					linha[aux[i]] = '*';
				}
			}
		}
		return new String(linha);
	}

	@Override
	public String toString() {
		String s = new String();
		for(int i=0;i<height;i++) {
			s+=line(i)+"\n";
		}
		return s;
	}
	
	


}
