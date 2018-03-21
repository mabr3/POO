package main;

import lab3.Complex;

public class Main {

	public static void main(String[] args) {
		
		Complex A = new Complex(2,4);
		Complex B = new Complex(3,5);
		
		System.out.println("Complex A is: "+A);
		System.out.println("Complex B is: "+B);
		
		
		
		Complex sum;
		sum =A.add(B);
		Complex sub;
		sub = Complex.sub(A,B);
		Complex mult;
		mult = Complex.mult(A,B);
		
		//Ver^
		
		System.out.println("Complex sum is: "+sum);
		System.out.println("Complex sub is: "+sub);
		System.out.println("Complex mult is: "+mult);
		
		Complex eq1 = new Complex(2,2);
		Complex eq2 = new Complex(2,2);
		
		System.out.println("eq1 == eq2 ="+ (eq1==eq2));
		System.out.println("eq1 equals eq2 = "+ (eq1.equals(eq2)));
		//Dá falso porque isto vai comparar o hash code e é diferente.
		//Override da função de hash code e equals
		
		//==-> reference comparison, aponta para a mesma memória?
		//equals compara os valores nos objectos, supostamente

	}

}
