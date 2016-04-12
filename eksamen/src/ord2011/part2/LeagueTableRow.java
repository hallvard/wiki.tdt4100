package ord2011.part2;

public class LeagueTableRow implements Comparable<LeagueTableRow> {

	private String participant;
	private int points;
	private int goalsFor, goalsAgainst;
	
	public LeagueTableRow(String participant) {
		this.participant = participant;
	}

	public String getParticipant() {
		return participant;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(int poeng) {
		this.points += poeng;
	}

	public int compareTo(LeagueTableRow other) {
		int diff = other.points - points;
		if (diff == 0) {
			diff = (other.goalsFor - other.goalsAgainst) - (goalsFor - goalsAgainst);
		}
		if (diff == 0) {
			diff = other.goalsFor - goalsFor;
		}
		return diff;
	}
}
