package ord2011.part2;

public class MatchResult {

	private String participant1, participant2;
	private int points1, points2;

	public MatchResult(String participant1, String participant2) {
		super();
		this.participant1 = participant1;
		this.participant2 = participant2;
	}

	public String getParticipant1() {
		return participant1;
	}

	public String getParticipant2() {
		return participant2;
	}

	public int getPoeng1() {
		return points1;
	}

	public void setPoeng1(int points) {
		this.points1 = points;
	}

	public int getPoeng2() {
		return points2;
	}
	
	public void setPoeng2(int points) {
		this.points2 = points;
	}
	
	public boolean isParticipant(String participant) {
		return participant1.equals(participant) || participant2.equals(participant);
	}
	
	public boolean isDraw() {
		return points1 == points2;
	}
	
	public boolean isWinner(String participant) {
		if (participant1.equals(participant)) {
			return points1 > points2;
		} else if (participant2.equals(participant)) {
			return points2 > points1;
		}
		return false;
	}
}
