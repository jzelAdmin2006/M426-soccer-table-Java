package tech.bison.trainee2021.SoccerTableStats.structure;

import java.util.ArrayList;
import java.util.List;
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

	public static List<GameResult> parseGameResults(String gameResultsToParse) {
		Matcher matcher = Pattern.compile("^(.*?)\\s(\\d+):(\\d+)\\s(.*)$", Pattern.MULTILINE)
				.matcher(gameResultsToParse);
		List<GameResult> gameResults = new ArrayList<>();
		while (matcher.find()) {
			gameResults.add(new GameResult(new Team(matcher.group(1)), new Team(matcher.group(4)),
					Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
		}
		return gameResults;
	}

	public static GameResult parseGameResult(String gameResult) {
		return parseGameResults(gameResult).get(0);
	}

	private static int digitToInt(char digit) {
		return digit - '0';
	}
}
