package tech.bison.trainee2021.SoccerTableStats.structure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Matcher matcher = Pattern.compile("(.*?)\\s(\\d+):(\\d+)\\s(.*)").matcher(gameResult);
		matcher.find();
		return new GameResult(new Team(matcher.group(1)), new Team(matcher.group(4)),
				Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
	}

	private static int digitToInt(char digit) {
		return digit - '0';
	}
}
