package bankaccountsim;

import java.util.Random;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create();
		//Creating an actor of BankAccount class
		ActorRef bankaccount = system.actorOf(Props.create(BankAccount.class));
		//Sending message to BankAccount actor
		bankaccount.tell("Start", ActorRef.noSender());
		Random random = new Random();
		for(int i=0; i<10; i++) {
			//Generate random numbers between -1000 and 1000
			int Amount = random.nextInt(2001) - 1000; 
			if(Amount>0) {
				bankaccount.tell(new Deposit(Amount), ActorRef.noSender());
			}
			else {
				bankaccount.tell(new Withdrawal(Amount), ActorRef.noSender());
			}
		}
	}
}
