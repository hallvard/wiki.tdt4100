package ord2012.weeklyplan;

public class TimeSlot implements Comparable<TimeSlot> {

	private static int asHours  (int minutes) { return minutes / 60;}
	private static int asMinutes(int minutes) { return minutes % 60;}
	
	private String description;
	private int startTime, endTime;
	
	public TimeSlot(String description, int hours, int minutes, int duration) {
		this.description = description;
		this.startTime = hours * 60 + minutes;
		this.endTime = this.startTime + duration;
	}

	private String twoDigits(int i) {
		return (i < 10 ? "0" : "") + i;
	}	
	public String toString() {
		return description + "@" + twoDigits(asHours(startTime)) + ":" + twoDigits(asMinutes(startTime)) + "-" + twoDigits(asHours(endTime)) + ":" + twoDigits(asMinutes(endTime));
	}

	public DayTime getStartTime() { return new DayTime(asHours(startTime), asMinutes(startTime));}
	public DayTime getEndTime() { return new DayTime(asHours(endTime), asMinutes(endTime));}
	public int getDuration() { return endTime - startTime;}

	public String getDescription() {
		return description;
	}
	
	public int compareTo(TimeSlot other) {
		int diff = startTime - other.startTime;
		if (diff == 0) {
			diff = endTime - other.endTime;
		}
		return diff;
	}
	
	public boolean contains(int hours, int minutes) {
		minutes = hours * 60 + minutes;
		return startTime <= minutes && endTime > minutes;
	}
	
	public boolean overlaps(TimeSlot other) {
		return startTime < other.endTime && endTime > other.startTime;
	}
}
