package bankaccountsim;

import akka.actor.AbstractActor;

public class BankAccount extends AbstractActor {
	
	private double balance;
    @Override 
    public Receive createReceive() {  // This method is called when  message is sent
        return receiveBuilder()
                 // Matching the message 
        		.matchEquals("Start", msg->{startup();})
                .matchAny(msg -> {System.out.println("BankAccount recieved message "+ msg); // Handle any other message
                })
                .build(); // Build is done only once.
    }
    
    //Method to initialize balance and display to output
    public void startup() {
    	System.out.println("Hello and Welcome to the Bank Simulation");
    	balance=100;
    }
}