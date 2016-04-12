package kont2014;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SafeBank extends Bank {
	
	private Map<Account, Integer> monthlyTransferLimits = new HashMap<Account, Integer>();
	
	public void setMonthlyTransferLimit(Account account, int limit) {
		monthlyTransferLimits.put(account, limit);
	}
	
	@Override
	protected void checkTransaction(Transaction transaction) {
		Account source = transaction.source;
		int amount = transaction.amount;
		Date date = transaction.date;
		Integer transferLimit = monthlyTransferLimits.get(source);
		if (transferLimit != null) {
			int transferSum = getTransferSum(source, date.getYear(), date.getMonth());
			if (transferSum + amount > transferLimit) {
				throw new IllegalStateException("Monthly transfer limit exceeded");
			}
		}
	}
}
