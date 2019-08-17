package kont2019.bike;

public class Person {

	private final String name;

	public Person(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * Withdraws the provided amount from this person's account.
	 * If the transaction couldn't be completed, i.e. no money was actually withdrawn,
	 * one of the indicated exceptions will be thrown.
	 * @param amount the amount to withdraw
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public void withdraw(final double amount) throws IllegalArgumentException, IllegalStateException {
		// ...
	}

	private PricePolicy pricePolicy;

	public PricePolicy getPricePolicy() {
		return pricePolicy;
	}

	public void setPricePolicy(final PricePolicy pricePolicy) {
		this.pricePolicy = pricePolicy;
	}
}
