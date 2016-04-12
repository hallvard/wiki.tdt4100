package kont2014;

import java.util.Date;

public class Transaction {

	public final Account source;
	public final Account target;
	
	public final int amount;

	public final Date date = new Date();
	
	public Transaction(Account source, Account target, int amount) {
		this.source = source;
		this.target = target;
		this.amount = amount;
	}
}
