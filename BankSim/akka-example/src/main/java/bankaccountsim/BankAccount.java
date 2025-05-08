package bankaccountsim;

import akka.actor.AbstractActor;

public class BankAccount extends AbstractActor {
	
	private double balance;
	private int transactionCount = 0;
    @Override 
    public Receive createReceive() {  // This method is called when  message is sent
        return receiveBuilder()
                 // Matching the message 
        		.matchEquals("Start", msg->{startup();})
        		.match(Deposit.class, deposit->{
        			System.out.println("The amount generated is £ "+deposit.getAmount());
        			balance += deposit.getAmount();
        			System.out.println("Deposited Amount £ " + deposit.getAmount() + ". New Balance: £" +balance);
        	    	System.out.println("-------------------");
        	    	checkIfFinished();
        		})
        		.match(Withdrawal.class, withdrawal->{
        			//Converting the balance to absolute
        			System.out.println("The amount generated is £ "+ Math.abs(withdrawal.getAmount()));
        			if(balance>=Math.abs(withdrawal.getAmount())) {
        				balance -= Math.abs(withdrawal.getAmount());
        				System.out.println("Withdrawn Amount £ " + Math.abs(withdrawal.getAmount()) + ". New Balance: £" +balance);
        				System.out.println("-------------------");        				
        			}
        			else {
        				System.out.println("Insufficient funds. Withdrawal amount of £ " + Math.abs(withdrawal.getAmount()) + " denied.");
        				System.out.println("-------------------");  
        			}
        			checkIfFinished();
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
    
    //Method to check end of program
    private void checkIfFinished() {
    	transactionCount++;
    	if (transactionCount == 10) {
    		System.out.println("End of program!!!!!!!");
    		getContext().getSystem().terminate(); 
    	}
    }
}