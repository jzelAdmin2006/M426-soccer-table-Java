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

	public static GameResult parseGameResult(String gameResult) {
		String[] splitGameResult = gameResult.split(":");
		String homeTeamResult = splitGameResult[0];
		String awayTeamResult = splitGameResult[1];
		int homeTeamResultLength = homeTeamResult.length();
		return new GameResult(new Team(homeTeamResult.substring(0, homeTeamResultLength - 2)),
				new Team(awayTeamResult.substring(2)), digitToInt(homeTeamResult.charAt(homeTeamResultLength - 1)),
				digitToInt(awayTeamResult.charAt(0)));
	}

	private static int digitToInt(char digit) {
		return digit - '0';
	}
}
