package main;

import geometry.Agregado;
import geometry.Image;
import geometry.Rectangle;
import geometry.Triangle;
import geometry.Circle;

public class Main{
	
	public static void main(String[] args){
		Image img=new Image(100,150);
		Agregado casa= new Agregado(0,0);
		casa.addForm(new Rectangle(0,5,15,15));
		casa.addForm(new Rectangle(5,15,5,5));
		casa.addForm(new Rectangle(2,7,4,3));
		casa.addForm(new Rectangle(9,7,4,3));
		casa.addForm(new Rectangle(2,11,4,3));
		casa.addForm(new Rectangle(9,11,4,3));
		casa.addForm(new Rectangle(5,15,5,5));
		casa.addForm(new Triangle(7,0,15,5));
		casa.addForm(new Circle(13,14,15));
		img.addForm(casa);
		System.out.println(img);
	}
}
