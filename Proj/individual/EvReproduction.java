
package individual;
/**
 * Reproduction event class, used to do reproduction events, extends EvIndividual.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */

public class EvReproduction extends EvIndividual{

	
	/**
	 * EvReproduction constructor.
	 * @param time - time of the event
	 * @param individual - Individual of corresponding event
	 */
	public EvReproduction(double time, AIndividual individual) {
		super(time, individual);
	}

	/**
	 * 	Individual reproduces so add a new reproduction of this individual to the PEC and creates his son.
	 */
	protected void DoEvent() {
		AddNext();
		individuo.createSon();
	}
	/**
	 * Add next reproduction event.
	 */
	
	protected void AddNext() {
		individuo.addReproduction();
		
	}

}
