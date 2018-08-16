package simulation;

import individual.AIndividualList;
import pec.Event;
/**
 * Observation event class, used to do scheduled observations that print the required contentes observed.
 * Extends Event.
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public class EvObservation extends Event{

	private AIndividualList ilist;
	
	/**
	 * EvObservation constructor.
	 * @param time - time of the event
	 * @param ilist - list of individuals
	 */
	
	public EvObservation(double time, AIndividualList ilist) {
		super(time);
		this.ilist = ilist;
	}

	/**
	 * 	Print the observation parameters
	 */
	protected void DoEvent(){
		System.out.println(this.ilist);
		AddNext();
	}
	
	public void AddNext() {
		double t=this.time + (double) this.ilist.getPec().getFinalinst()/20;
		if(t<=this.ilist.getPec().getFinalinst())
			ilist.getPec().addEvent(new EvObservation(t,this.ilist));
	}
}
