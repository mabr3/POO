package stockmarket;

public class Share {
	
	int quantity;
	Stockowner owner;
	Corporation corporation;
	
	/**
	 * Constructs  share owned by a, of corporation c with x stocks
	 */
	
	Share(Corporation c, Stockowner a, int x){
		quantity = x;
		owner = a;
		corporation = c;
	}

	@Override
	public String toString() {
		return "{" + corporation.name + "," + quantity+"}";
	}
	
	

}
