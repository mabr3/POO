package main;

import stockmarket.Corporation;
import stockmarket.Stockmarket;
import stockmarket.Stockowner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stockmarket sm_lisbon = new Stockmarket();
		Corporation galp = new Corporation("Galp",50000,20),
				novabase = new Corporation("Novabase", 50000, 10),
				pararede = new Corporation("Pararede", 50000, 1);
		sm_lisbon.putInMarket(galp.createShares(100));
		sm_lisbon.putInMarket(novabase.createShares(100));
		sm_lisbon.putInMarket(pararede.createShares(100));
		
		System.out.println("new market : " +sm_lisbon);
		
		Stockowner p1 = new Stockowner("p1",500),
				p2 = new Stockowner("p2",5000),
				p3 = new Stockowner("p3",500);
		
		sm_lisbon.buy(p1, galp, 10);
		sm_lisbon.buy(p1, novabase, 10);
		sm_lisbon.buy(p1, pararede, 10);
		
		System.out.println(p1.toString());
		System.out.println("market : " +sm_lisbon);
		
		sm_lisbon.putInMarket(p1,galp,5);
		sm_lisbon.buy(p1, pararede, 10);
		
		System.out.println(p1.toString());
		System.out.println("market : " +sm_lisbon);
		
		sm_lisbon.putInMarket(p1, pararede, 15);
		
		sm_lisbon.buy(p2, galp, 200); //there is only 100!
		sm_lisbon.buy(p2, novabase, 200); //there is only 90!
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("market " +sm_lisbon);
		
		sm_lisbon.buy(p3, pararede, 100);
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println("market " +sm_lisbon);
		

	}

}
