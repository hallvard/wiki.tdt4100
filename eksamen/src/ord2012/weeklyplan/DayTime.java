package ord2012.weeklyplan;

public class DayTime {
	
	public final int hours, minutes;

	public DayTime(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	public String toString() {
		return hours + ":" + minutes;
	}
}
