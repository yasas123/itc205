package bcccp.carpark.exit;

import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.IAdhocTicket;
import java.util.Scanner;

public class ExitController 
		implements ICarSensorResponder,
		           IExitController {
	
	private IGate exitGate;
	private ICarSensor insideSensor;
	private ICarSensor outsideSensor; 
	private IExitUI ui;
	
	private ICarpark carpark;
	private IAdhocTicket  adhocTicket = null;
	private long exitTime;
	private String seasonTicketId = null;
	
	

	public ExitController(Carpark carpark, IGate exitGate, 
			ICarSensor is,
			ICarSensor os, 
			IExitUI ui) {
		//TODO Implement constructor
		this.carpark = carpark;
		this.exitGate = exitGate;
		this.insideSensor = is;
		this.outsideSensor = os;
		this.ui = ui;
		
		os.registerResponder(this);
		is.registerResponder(this);
		ui.registerController(this);
		
		
	}



	@Override
	public void ticketInserted(String ticketStr) {
		// TODO Auto-generated method stub
		Scanner exitticket = new Scanner(System.in);
		System.out.println("please insert the ticket"); 
		
	}



	@Override
	public void ticketTaken() {
		if (state == STATE.PROCESSED) {
		exitGate.raise();
		setState(STATE.TAKEN);
		}
		else if (state == STATE.PROCESSED){
		setState(STATE.WAITING);
		}
		else{
		ui.beep();
		log("ticketTaken - called in incorrect state");
		}
		
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		log("carEventDetected- " + detectorId + ", car Detected: " + carDetected );
		switch (state){
			case BLOCKED:
				if (detectorId.equals(is.getId()) && !carDetected){
				setState(prevState);
				}
				break;
				case IDLE:
				log("eventDetected: IDLE");
				if (detectorId.equals(is.getId()) && carDetected){
					log("eventDetected: setting state to waiting");
					setState(STATE.WAITING);
				}
				else if (detectorId.equals(os.getId()) && carDetected){
					setState(STATE.BLOCKED);
				break;
			case WAITING:
			case PROCESSED:
				if (detectorId.equals(is.getId()) && !carDetected){
					setState(STATE.IDLE);
				}
				else if (detectorId.equals(os.getId()) && carDetected){
					setState(STATE.BLOCKED);
				}
				break;
			case TAKEN:
				if (detectorId.equals(is.getId()) && !carDetected){
					setState(STATE.IDLE);
				}
				else if (detectorId.equals(os.getId()) && carDetected){
					setState(STATE.EXITING);
				}
				break;
			case EXITING:
				if (detectorId.equals(is.getId()) && !carDetected){
					setState(STATE.IDLE);
				}
				else if (detectorId.equals(os.getId()) && !carDetected){
					setState(STATE.TAKEN);
				}
				break;
			case EXITED:
				if (detectorId.equals(is.getId()) && carDetected){
					setState(STATE.EXITING);
				}
				else if (detectorId.equals(os.getId()) && !carDetected){
					setState(STATE.IDLE);
				}
				break;
			
					
		}
		
	}

	
	
}
