package kont2014;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	private Account account;
	
	protected void setUp() throws Exception {
		account = new Account("1");
	}

	public void testObjectStateDiagram() {
		assertEquals(0, account.getBalance());
		account.deposit(100);
		assertEquals(100, account.getBalance());
		assertEquals(100, account.getBalance());
		account.withdraw(150);
		assertEquals(-50, account.getBalance());
		try {
			account.withdraw(-50);
		} catch (Exception e) {
		}
		assertEquals(-50, account.getBalance());
	}
}
