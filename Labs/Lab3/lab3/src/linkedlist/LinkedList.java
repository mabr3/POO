package linkedlist;

import linkedlist.Node;

public class LinkedList {
	
	//fields
	Node head;
	
	public LinkedList() {
		head = null;
	}
	
	public void add(int xx) {
		Node node = new Node(xx, head);
		this.head = node;
		
	}
	
	public void remove() {
		this.head = head.next;
	}

	@Override
	public String toString() {
		return " "+ head;
	}

	
}
