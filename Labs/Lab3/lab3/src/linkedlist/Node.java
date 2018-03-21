package linkedlist;

class Node {
	
	int x;
	Node next;
	
	public Node(int xx, Node head) {
		x=xx;
		next=head;
	}

	@Override
	public String toString() {
		
		return "" + x + (next==null? "" : "," + next);
	}
	
	

}
