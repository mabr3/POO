package main_linkedlist;

import linkedlist.LinkedList;
	

public class Main {

	public static void main(String[] args) {
		
		
		LinkedList Lista = new LinkedList();
		
		Lista.add(10);
		Lista.add(1);
		Lista.add(15);
		
		//Node tmp = new Node(0, Lista.head)
		
		System.out.println("-> "+Lista);
			
		Lista.remove();
		Lista.remove();

		System.out.println("-> "+Lista);	
	}

}
