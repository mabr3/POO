package stockmarket;

public class Corporation extends Stockowner {
	
	private float value;
	
	/**
	 * Constructor for corp. has +arams of constructor for stockowner aswell
	 */
	
	public Corporation(String a, float b, float c) {
		super(a, b);
		value = c;
	}
	
	/**
	 * creates new shares of shit Corporation, owned by this Corporation
	 */

	public Share createShares (int q) {
		Share share = new Share(this, this,q);
		this.addShares(share);
		return share;
	}
	
	/**
	 * returns value of each stock
	 */
	
	public float getValue() {
		return value;
	}
}
