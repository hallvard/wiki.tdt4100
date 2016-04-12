package kont2014;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Bank {

	private List<Account> accounts = new ArrayList<Account>();

	protected Account getAccount(String accountId) {
		for (Account account : accounts) {
			if (accountId.equals(account.getAccountId())) {
				return account;
			}
		}
		return null;
	}

	protected boolean exists(String accountId) {
		return getAccount(accountId) != null;
	}
	
	public void addAccount(Account account) {
		if (! exists(account.getAccountId())) {
			accounts.add(account);
		}
	}

	private int nextAccountId = 1;
	
	public Account createAccount() {
		while (exists(String.valueOf(nextAccountId))) {
			nextAccountId++;
		}
		Account account = new Account(String.valueOf(nextAccountId));
		addAccount(account);
		return account;
	}
	
	protected List<Transaction> transactions = new ArrayList<Transaction>();
	
	protected void checkTransaction(Transaction transaction) {
	}
	
	public void transfer(Account source, Account target, int amount) throws Exception {
		if (! (accounts.contains(source) && accounts.contains(target))) {
			throw new IllegalArgumentException("A bank can only handle registered accounts");
		}
		Transaction transaction = new Transaction(source, target, amount);
		checkTransaction(transaction);
		source.withdraw(amount);
		try {
			target.deposit(amount);
		}
		catch (Exception e) {
			source.deposit(amount);
			throw e;
		}
		transactions.add(transaction);
	}
	
	protected int getTransferSum(Account account, int year, int month) {
		int sum = 0;
		for (Transaction transaction : transactions) {
			if (transaction.source == account) {
				Date date = transaction.date;
				if (date.getYear() == year && date.getMonth() == month) {
					sum += transaction.amount;
				}
			}
		}
		return sum;
	}

	public void doTransactions(InputStream input) {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int dashPos = line.indexOf('-'), colonPos = line.indexOf(':');
			try {
				Account source = getAccount(line.substring(0, dashPos));
				Account target = getAccount(line.substring(dashPos + 1, colonPos));
				int amount = Integer.valueOf(line.substring(colonPos + 1));
				if (source == null || target == null) {
					continue;
				}
				transfer(source, target, amount);
			} catch (Exception e) {
			}
		}
		scanner.close();
	}
}

