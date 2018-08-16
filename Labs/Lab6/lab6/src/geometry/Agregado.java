package geometry;

import java.util.ArrayList;
import java.util.Iterator;

public class Agregado extends Form {
	
	private ArrayList<Form> formas = new ArrayList<Form>();
	
	public Agregado(int pos_x, int pos_y) {
		super(pos_x,pos_y);
	}
	
	public void  addForm(Form f){
		formas.add(f);
	}
	
	public int[] intersection(int y) {
		int[] pontos=new int[0];
		int[] novos_pontos;
		
		Form f;
		Iterator<Form> it = formas.iterator();
		while(it.hasNext()){
			f=(Form)it.next();
			novos_pontos=f.intersection(y-pos_y);
			pontos=junta(pontos, novos_pontos);
		}
		for(int i=0; i<pontos.length;i++)
			pontos[i]+=pos_x;
		
		return pontos;
	}
	
	private int[] junta(int v1[], int v2[]){
		int[] r = new int[v1.length+v2.length];
		for (int i=0; i<v1.length; i++)
			r[i]=v1[i];
		for(int i=v1.length; i< v1.length+v2.length;i++)
			r[i]=v2[i-v1.length];
		return r;
	}
}
