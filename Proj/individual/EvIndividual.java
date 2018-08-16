
package individual;

import pec.Event;
/**
 * Abstract class for events that are related to the individuals, needing always a reference to that individual.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
abstract class EvIndividual extends Event {
	
	/**Every event related with a specific individual needs a reference to it.**/
	protected AIndividual individuo;
	
	/**
	 * EvIndividual constructor.
	 * @param time - time of the event
	 * @param individual - Individual of corresponding event
	 */
	public EvIndividual(double time, AIndividual individual) {
		super(time);
		this.individuo = individual;
	}
	
	protected void AddNext() {	
	}
	
}
