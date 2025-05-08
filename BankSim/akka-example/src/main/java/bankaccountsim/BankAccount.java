package bankaccountsim;

import akka.actor.AbstractActor;

public class BankAccount extends AbstractActor {
	
	private double balance;
    @Override 
    public Receive createReceive() {  // This method is called when  message is sent
        return receiveBuilder()
                 // Matching the message 
        		.matchEquals("Start", msg->{startup();})
        		.match(Deposit.class, deposit->{
        			System.out.println("The amount generated is £ "+deposit.getAmount());
        			balance += deposit.getAmount();
        			System.out.println("Deposited Amount £ " + deposit.getAmount() + ". New Balance: " +balance);
        	    	System.out.println("-------------------");
        		})
        		.match(Withdrawal.class, withdrawal->{
        			System.out.println("The amount generated is £ "+withdrawal.getAmount());
        			balance += withdrawal.getAmount();
        			System.out.println("Withdrawn Amount £ " + withdrawal.getAmount() + ". New Balance: " +balance);
        	    	System.out.println("-------------------");
        		})
                .build(); // Build is done only once.
    }
    
    //Method to initialize balance and display to output
    public void startup() {
    	System.out.println("Hello and Welcome to the Bank Simulation");
    	balance=100;
    	System.out.println("Your current balance is £ " + balance);
    	System.out.println("-------------------");
    	System.out.println("-------------------");
    }
}