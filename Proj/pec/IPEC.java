

package pec;

import java.util.ArrayList;

/**
 * Interface for the PEC, has the methods needed to be implemented to correctly implement a PEC for this
 * program.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public interface IPEC {
	
	/** Create a new PEC*/
	void newPEC();
	/**Add an Event to the PEC*/
	void addEvent(Event evento);
	/**Get the next event in the pec*/
	Event nextEvent(Event evento);
	/**Remove an event from the PEC*/
	void removeEvent(Event evento);
	/**Start running the PEC*/
	void Start();
	/**Get the number of events present in the PEC*/
	int getLength();
	/**Add a new set of events to the PEC*/
	void addNewEvents(ArrayList<Event> new_events);
}
