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
		
		
	}



	@Override
	public void ticketInserted(String ticketStr) {
		// TODO Auto-generated method stub
		Scanner exitticket = new Scanner(System.in);
		System.out.println("please insert the ticket"); 
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
