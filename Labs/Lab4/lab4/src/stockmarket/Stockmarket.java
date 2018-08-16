package stockmarket;

import java.util.ArrayList;
import java.util.List;


public class Stockmarket {
	
	private List<Share> market;
	
	/**
	 * Creates empty Stockmarket
	 */

	public Stockmarket() {
		market = new ArrayList<Share>();
	}
	
	/**
	 * Puts a Share on the market
	 */
	public void putInMarket(Share a) {
		market.add(a);
	}
	
	/**
	 * Puts a Share owned by a of Corporation e with q stocks in the market
	 */
	
	public void putInMarket(Stockowner a, Corporation e, int q) {
		Share s = a.getShare(e);
		if(s==null) {
			System.out.println("Stockowner" + a.name + "does now have shares of corporation" +e.name+"!");
			return;
		}
		
		if(s.quantity >q) { //split share
			s.quantity -= q;
			s = new Share(e,a,q);
		}
		
		a.removeShare(s);//remove share, if there is one, from owner share list
		market.add(s); //add it to the market
		
		System.out.println("Stockowner " + a.name+ " put " + s.quantity +" stocks of the corporation " + e.name + " in the market");
	}
	
	private Share find(Corporation e) {
		for(Share aux: market)
			if(aux.corporation.equals(e)) return aux;
		return null;
	}
	
	/**
	 * Stockowner a goes to the market to buy q stocks of Corporation e
	 */
	
	public void buy(Stockowner a, Corporation e, int q) {
		if(a.money < q*e.getValue()) {
			System.out.println("Stockowner " + a.name + "cannot buy " +q+ "Stocks of corporation " + e.name +"!");
			return;
		}
		
		/*searches the market for stocks to sell*/
		
		int num =0;
		
		for(Share aux=find(e); aux!=null && q!=0;aux =find(e)) {
			if(aux.quantity>q) {//split share and pay former owner
				aux.quantity -= q;
				aux.owner.credit(q*e.getValue());
				num +=q;
				break;
			}else {//removes share and pays former owner
				q-=aux.quantity;
				aux.owner.credit(aux.quantity*e.getValue());
				num+=aux.quantity;
				market.remove(aux);
			}
		}
		
		if(num!=0) {//get money from the the buyer and add his new shares
			Share newShare = new Share(e,a,num);
			a.addShares(newShare);
			a.debit(num*e.getValue());
			return;
		}
	}

	@Override
	public String toString() {
		return market.toString();
	}
	
	
}

