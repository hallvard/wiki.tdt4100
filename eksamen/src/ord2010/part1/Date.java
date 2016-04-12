package ord2010.part1;

/*
 * @startuml
 * class Date {
 * -int day
 * -int month
 * -int year
 * +Date(int day, int month, int year)
 * -int numberOfDays(int month, int year)
 * -void check(int day, int month, int year)
 * +int getDay()
 * +void setDay(int)
 * +int getMonth()
 * +void setMonth(int)
 * +int getYear()
 * +void setYear(int)
 * +void nextDay()
 * +void previousDay()
 * }
 * @enduml
 */
public class Date {
	
	private int day, month, year;
	
	public Date(int day, int month, int year) {
		check(day, month, year);
		this.day = day;
		this.month = month;
		this.year = year;
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && (year % 400 == 0 || year % 100 != 0));
	}
	
	private int numberOfDays(int month, int year) {
		switch (month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;
		case 4: case 6: case 9: case 11: return 30;
		case 2: return (isLeapYear(year) ? 29 : 28);
		}
		return -1;
	}

	private void check(int day, int month, int year) throws IllegalArgumentException {
		if (day < 1 || day > numberOfDays(month, year)) {
			throw new IllegalArgumentException("day is illegal: " + day);
		}
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("month is illegal: " + day);
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		check(day, month, year);
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		check(day, month, year);
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		check(day, month, year);
		this.year = year;
	}
	
	public void nextDay() {
		day++;
		if (day > numberOfDays(month, year)) {
			day = 1;
			month++;
			if (month > 12) {
				month = 1;
				year++;
			}
		}
	}

	public void previousDay() {
		day--;
		if (day < 1) {
			month--;
			if (month < 1) {
				month = 12;
				year--;
			}
			day = numberOfDays(month, year);
		}
	}
}
