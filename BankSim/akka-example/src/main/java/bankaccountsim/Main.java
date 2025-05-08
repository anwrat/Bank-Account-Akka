package bankaccountsim;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create();
		//Creating an actor of BankAccount class
		ActorRef bankaccount = system.actorOf(Props.create(BankAccount.class));
		//BankAccount actor sending message to itself
		bankaccount.tell("Start", bankaccount);
	}
}
