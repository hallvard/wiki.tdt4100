package ord2012.weeklyplan;


public class WeekPlan {
	
	private DayPlan[] dayPlans = new DayPlan[5];
	
	public WeekPlan() {
		for (int i = 0; i < WeekDay.values().length; i++) {
			dayPlans[i] = new DayPlan();
		}
	}

	public WeekPlan(WeekPlan regularPlan) {
		for (int i = 0; i < WeekDay.values().length; i++) {
			DelegatingDayPlan delegatingDayPlan = new DelegatingDayPlan(regularPlan.dayPlans[i]);
			dayPlans[i] = delegatingDayPlan;
		}
	}
	
	//
	
	public DayPlan getDayPlan(WeekDay weekDay) {
		return dayPlans[weekDay.ordinal()];
	}
}
