package individual;

import simulation.Point;
import simulation.AGrid;
/**
 * Move event class, used to do move events, extends EvIndividual.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class EvMove extends EvIndividual {
	
	/**
	 * EvMove constructor.
	 * @param time - time of the event
	 * @param individual - Individual of corresponding event
	 */
	public EvMove(double time, AIndividual individual) {
		super(time, individual);
	}

	/**
	 * Do the move event, i.e, get what the next point of the path will be, add it to the path and call the method to add the next move event
	 * for this individual.
	 */
	protected void DoEvent() {
		Point aux;
		AGrid grid = individuo.getList().getGrid();
		aux = grid.nextPoint(individuo.getLastPoint());
		individuo.addToPath(aux);
		AddNext();
		
	}
	
	/**
	 * Adds a new Move event to the PEC.
	 */
	
	protected void AddNext() {
		individuo.addMove();
	}
}
