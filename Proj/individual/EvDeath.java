package individual;

/**
 * Death event class, used to do death events, extends EvIndividual.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class EvDeath extends EvIndividual{
	
	/** AIndividualList reference needed to know from which list to remove the individual**/ 
	private AIndividualList indivList;
	
	/**
	 * EvDeath constructor, gets the time, the individual calling and its respective list.
	 * @param time - time of the event
	 * @param individual - Individual of corresponding event
	 * @param List - List of individuals
	 */
	public EvDeath(double time, AIndividual individual, AIndividualList List) {
		super(time, individual);
		this.indivList = List;	
	}

	/**
	 * 	Performs the specific action of the death event: removing the event from the list.
	 */
	
	protected void DoEvent() {
		indivList.removeIndividual(this.individuo);
	}
	
	
}
