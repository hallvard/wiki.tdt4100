package kont2014;

/*
 * @startuml
object "Account" as account0 {
		balance = 0
}
object "Account" as account1 {
		balance = 100
}
account0 ..> account1: deposit(100)
account1 ..> account1: getBalance() => 100
 
object "Account" as account2 {
		balance = -50
}
account1 ..> account2: withdraw(150)
account2 ..> account2: withdraw(-50)
 * @enduml
 */

public class Account {

	private final String accountId;

	private void checkAccountId(String accountId) {
		for (int i = 0; i < accountId.length(); i++) {
			char c = accountId.charAt(i);
			if (! Character.isDigit(c)) {
				throw new IllegalArgumentException(c + " is not a valid account id character");
			}
		}
	}
	
	public Account(String accountId) {
		checkAccountId(accountId);
		this.accountId = accountId;
	}
	
	public String getAccountId() {
		return accountId;
	}

	private int balance = 0;
	
	public int getBalance() {
		return balance;
	}

	protected void checkAmount(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount cannot be negative, but was " + amount);
		}
	}

	public void deposit(int amount) {
		checkAmount(amount);
		balance += amount;
	}
	
	private int withdrawLimit = 0;
	
	public void setWithdrawLimit(int withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	protected void checkWithdrawAmount(int amount) {
		if (withdrawLimit > 0 && amount > withdrawLimit) {
			throw new IllegalArgumentException("Withdraw amount cannot be above " + withdrawLimit + ", but was " + amount);
		}
	}
	
	public void withdraw(int amount) {
		checkAmount(amount);
		checkWithdrawAmount(amount);
		balance -= amount;
	}
}
