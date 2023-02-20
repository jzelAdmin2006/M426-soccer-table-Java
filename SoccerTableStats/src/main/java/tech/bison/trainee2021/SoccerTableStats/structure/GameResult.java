package tech.bison.trainee2021.SoccerTableStats.structure;

public class GameResult {

	private final Team homeTeam;
	private final Team awayTeam;
	private final int homeTeamScore;
	private final int awayTeamScore;

	public GameResult(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}
}
