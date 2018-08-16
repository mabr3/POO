package stockmarket;

import java.util.ArrayList;
import java.util.List;


public class Stockowner {
	
	protected String name;
	protected float money;
	
	protected List<Share> shares;
	
	/** 
	 * Constructs a Stockowner. params name, money
	 */

	public Stockowner(String s, float d) {
		name = s;
		money = d;
		shares = new ArrayList<Share>();
	}
	
	/*make a credit to the stockwoner's money*/
	
	void credit(float q) {
		money += q;
	}
	
	/*make a debit from the stockwoner's money*/
	
	void debit(float q) {
		money -= q;
	}
	
	/*remove shares*/
	
	void removeShare(Share a) {
		shares.remove(a);
	}
	
	/*add shares - need to confirm if already has any from that company*/
	void addShares(Share a) {
		Share aux = getShare(a.corporation);
		if(aux==null) {
			shares.add(a);
		}else {
			aux.quantity += a.quantity;
		}	
	}
	
	/**
	 * Returns Share of Corporation e owned by this Stockwoner. returns the share or null if it has none
	 */
	
	Share getShare(Corporation e) {
		for(Share aux : shares) {
			if(aux.corporation.equals(e)) return aux;
		}
		return null;
	}

	@Override
	public String toString() {
		return name + "->" + money +"->" + shares.toString();
	}
	
	
}

