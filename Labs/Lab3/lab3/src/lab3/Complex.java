package lab3;

public class Complex {
	
	//Fields
	final int a,b;
	
	//Constructors
	

	public Complex(int aa, int bb) {
		a=aa;
		b=bb;
	}
	
	public Complex add(Complex B) { //aplica o método sobre um objecto
		Complex C = new Complex((this.a +B.a),(this.b + B.b));
		return C;
	}
	
	public static Complex sub(Complex A, Complex B) { //método referente à classe
		
		Complex C = new Complex((A.a - B.a),(A.b - B.b));
		return C;
	}
	
	public static Complex mult(Complex A, Complex B) {
		
		Complex C = new Complex(((A.a * B.a)-(A.b - B.b)), ((A.b * B.a)+(A.a + B.b)));
		return C;
	}

	@Override
	public String toString() {
		return + a + "+" + b + "i";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
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
		Complex other = (Complex) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}
	
	
}
