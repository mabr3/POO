
package pec;
/**
 * Abstract event class, contains the structure for the events and the variable they all must have: the time.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */

public abstract class Event implements Comparable<Event> {
	
	/**time needed to order in the PEC, all events must have**/
	protected double time;
	
	/** Each event performs different actions when called*/
	protected abstract void DoEvent();
	/**Each event adds a nest one of its type to the PEC, except the death*/
	protected abstract void AddNext();
	
	/**
	 * Event constructor, only needs the time.
	 * @param time - time of the event to happen
	 */
	public Event(double time) {
		this.time = time;
	}
	
	/**
	 * Compares the time of events to order in the PEC, which will be ordered in ascending order.
	 * @param evento - Other event to compare time 
	 * @return value that says if time is bigger, smaller or equal
	 */
	public int compareTo(Event evento) {
		if (this.time < evento.time) {
			return -1;
		}

		if (this.time > evento.time) {
			return 1;
		}

			return 0;
		}
	

	// Getter
	
	public double getTime() {
		return time;
	}
	
}

