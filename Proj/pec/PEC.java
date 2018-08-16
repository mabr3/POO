package pec;

import java.util.ArrayList;
import java.util.Collections;

import simulation.EvObservation;

/**
 * PEC class, implements the IPEC interface. It is from this class that the events are selected to run.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */

public class PEC implements IPEC {
	
	/**Number of observations made so far*/
	private int observations;
	/**Final instant for the simulation*/
	private int finalinst;
	/**List of Events of the PEC*/
	private ArrayList<Event> events = new ArrayList<Event>();
	/**Number of events that have occurred*/
	private int count_events;
	/**Current time of the simulation*/
	protected double current_time;
	
	//Getters and Setters
	public int getObservations() {
		return observations;
	}
	
	public int getFinalinst() {
		return finalinst;
	}

	public void setFinalinst(int finalinst) {
		this.finalinst = finalinst;
	}

	public int getCount_events() {
		return count_events;
	}

	public void setCount_events(int count_events) {
		this.count_events = count_events;
	}
	
	public double getTime() {
		return current_time;
	}
	
	//methods
	
	/**
	 * Add an event to the PEC and sort the event list by time
	 * @param evento - event to add to the PEC
	 */
	@Override
	public void addEvent(Event evento ) {
		if ( evento.getTime() <= finalinst ) {
			events.add(evento);
			Collections.sort(events);			
		}
	}
	
	/**
	 * Number of events in the PEC
	 * @return value with size of the PEC
	 */
	public int getLength() {
		return events.size();
	}

	/**
	 * Remove an event that happened and returns the next event to occur
	 * @param evento - event to remove
	 * @return Next event to do
	 */
	@Override
	public Event nextEvent(Event evento) {
		events.remove(evento);
		if(events.size()==0) {
			return null;
		}
		return events.get(0);		
	}

	/**
	 * Create a new PEC
	 */
	@Override
	public void newPEC() {
		events = new ArrayList<Event>();
		count_events = 0;
	}
	
	/**
	 * Start the PEC. Do the events!
	 */
	@Override
	public void Start() {
		
		Event current_event = events.get(0);
		
		count_events=0;
		
		while(this.current_time<=this.finalinst) {
			if(current_event instanceof EvObservation) {
				observations++;
			}else {
				count_events++;
			}
			this.current_time = current_event.time;
			current_event.DoEvent();
			current_event = this.nextEvent(current_event);
			if(current_event ==null) {
				break;
			}
			
			if(events.size()==1) {
				observations++;
				this.current_time = current_event.time;
				current_event.DoEvent();
				events.clear();
				//one last observation print before ending
				break;
			}	
		}
	}
	
	/**
	 * Removes the given event from the PEC events list.
	 */
	@Override
	public void removeEvent(Event event) {
		events.remove(event);
		return;
	}
	
	/**
	 * Method to add a new list of events. Useful for when an epidemics occur, adds a new list of events without the need to check for
	 * which existing events must be deleted.
	 * Then sorts them by time.
	 * @param new_events
	 */
	
	public void addNewEvents(ArrayList<Event> new_events) {
		events.clear();
		events=new_events;
		Collections.sort(events);
	}
}
