package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;

public class PaystationController 
		implements IPaystationController {
	
	private IPaystationUI ui;	
	private ICarpark carpark;

	private IAdhocTicket  adhocTicket = null;
	private float charge;
	
	

	public PaystationController(ICarpark carpark, IPaystationUI ui) {
		//TODO Implement constructor
	}



	@Override
	public void ticketInserted(String ticketstr) {
		
	if (state == STATE.WAITING) {
		if adhocTicket = carpark.getAdhocTicket (ticketstr);
		exitTime = System.currentTimeMillis ();
		if (adhocTicket != null && adhocTicket.isPaid()) {
			setState(STATE.PROCESSED);
		}
		else {
			ui.beep ();
			setState(STATE.REJECTED);
		}
	}
		else if (carpark.isSeasonTicketValid(ticketStr) &&
			 carpark.isSeasonTicketInUse (ticketStr)){
			seasonTicketId = ticketStr;
			setState(STATE.PROCESSED);
		}
	else {
		ui.beep();
		setState(STATE.REJECTED);
	}
	}
	else {
		ui.beep ();
		ui.discardTicket();
		log("ticketInserted: called while in incorrect state");
		setState(STATE.REJECTED);
	}
	}


	@Override
	public void ticketPaid() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		
		
		if (state == STATE.PROCESSED) {
			exitGate.raise();
			setState(STATE.TAKEN);
	}
	else if (state == STATE.REJECTED) {
		setState(STATE.WAITING) ;
	}
	else {
		ui.beep();
		log("ticketTaken: called while in incorrect state");
	}
	}

	
	
}
