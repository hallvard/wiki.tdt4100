package ord2011.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeagueTable {

	private List<MatchResult> matchResults = new ArrayList<MatchResult>();
	private List<LeagueTableRow> leagueTableRows = new ArrayList<LeagueTableRow>();

	public LeagueTable(String[] teams) {
		for (int i = 0; i < teams.length; i++) {
			leagueTableRows.add(new LeagueTableRow(teams[i]));
		}
	}
	
	public int getParticipantPoints(MatchResult matchResult, String participant) {
		if (matchResult.isDraw()) {
			return 1;
		} else if (matchResult.isWinner(participant)) {
			return 3;
		}
		return 0;
	}

	public void addMatchResult(MatchResult matchResult) {
		matchResults.add(matchResult);
		LeagueTableRow row1 = findLeagueTableRow(matchResult.getParticipant1());
		LeagueTableRow row2 = findLeagueTableRow(matchResult.getParticipant2());
		if (row1 != null && row2 != null) {
			row1.addPoints(getParticipantPoints(matchResult, row1.getParticipant()));
			row2.addPoints(getParticipantPoints(matchResult, row2.getParticipant()));
			Collections.sort(leagueTableRows);
		}
	}
	
	private LeagueTableRow findLeagueTableRow(String team) {
		for (LeagueTableRow leagueTableRow : leagueTableRows) {
			if (leagueTableRow.getParticipant().equals(team)) {
				return leagueTableRow;
			}
		}
		return null;
	}
}
