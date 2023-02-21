package tech.bison.trainee2021.SoccerTableStats.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class GameResultTest {
	@Test
	void newGameResultWithTeamsAndScores_getTeamsAndScores_isCorrect() {
		GameResult gameResult = new GameResult(new Team("HomeTeam"), new Team("AwayTeam"), 0, 1);

		Team homeTeamResult = gameResult.getHomeTeam();
		Team awayTeamResult = gameResult.getAwayTeam();
		int homeTeamScoreResult = gameResult.getHomeTeamScore();
		int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(new Team("HomeTeam"));
		assertThat(awayTeamResult).isEqualTo(new Team("AwayTeam"));
		assertThat(homeTeamScoreResult).isZero();
		assertThat(awayTeamScoreResult).isOne();
	}

	@Test
	void newGameResultWitDifferenthTeamsAndScores_getTeamsAndScores_isCorrect() {
		GameResult gameResult = new GameResult(new Team("HomeTeam2"), new Team("AwayTeam2"), 1, 2);

		Team homeTeamResult = gameResult.getHomeTeam();
		Team awayTeamResult = gameResult.getAwayTeam();
		int homeTeamScoreResult = gameResult.getHomeTeamScore();
		int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(new Team("HomeTeam2"));
		assertThat(awayTeamResult).isEqualTo(new Team("AwayTeam2"));
		assertThat(homeTeamScoreResult).isOne();
		assertThat(awayTeamScoreResult).isEqualTo(2);
	}

	@Test
	void gameResultString_parseGameResult_isCorrect() {
		GameResult gameResult = GameResult.parseGameResult("Hertha BSC 4:3 1. FC Köln");

		Team homeTeamResult = gameResult.getHomeTeam();
		Team awayTeamResult = gameResult.getAwayTeam();
		int homeTeamScoreResult = gameResult.getHomeTeamScore();
		int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(new Team("Hertha BSC"));
		assertThat(awayTeamResult).isEqualTo(new Team("1. FC Köln"));
		assertThat(homeTeamScoreResult).isEqualTo(4);
		assertThat(awayTeamScoreResult).isEqualTo(3);
	}

	@Test
	void gameResultString_parseDifferentGameResult_isCorrect() {
		GameResult gameResult = GameResult.parseGameResult("VfL Wolfsburg 2:1 FC Augsburg");

		Team homeTeamResult = gameResult.getHomeTeam();
		Team awayTeamResult = gameResult.getAwayTeam();
		int homeTeamScoreResult = gameResult.getHomeTeamScore();
		int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(new Team("VfL Wolfsburg"));
		assertThat(awayTeamResult).isEqualTo(new Team("FC Augsburg"));
		assertThat(homeTeamScoreResult).isEqualTo(2);
		assertThat(awayTeamScoreResult).isEqualTo(1);
	}

	@Test
	void gameResultsString_parseGameResults_isCorrect() {
		List<GameResult> gameResults = GameResult.parseGameResults(
				"Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n" + "FC Schalke 04 2:2 TSG Hoffenheim\n"
						+ "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n" + "RB Leipzig 0:4 1. FSV Mainz 05\n"
						+ "SV Werder Bremen 0:2 VfB Stuttgart\n" + "Borussia Dortmund 3:2 Eintracht Frankfurt");

		List<Team> homeTeamResults = gameResults.stream().map(GameResult::getHomeTeam).collect(Collectors.toList());
		List<Team> awayTeamResults = gameResults.stream().map(GameResult::getAwayTeam).collect(Collectors.toList());
		List<Integer> homeTeamScoreResults = gameResults.stream().map(GameResult::getHomeTeamScore)
				.collect(Collectors.toList());
		List<Integer> awayTeamScoreResults = gameResults.stream().map(GameResult::getAwayTeamScore)
				.collect(Collectors.toList());

		assertThat(homeTeamResults).containsExactly(new Team("Hertha BSC"), new Team("VfL Wolfsburg"),
				new Team("FC Schalke 04"), new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
				new Team("SV Werder Bremen"), new Team("Borussia Dortmund"));
		assertThat(awayTeamResults).containsExactly(new Team("1. FC Köln"), new Team("FC Augsburg"),
				new Team("TSG Hoffenheim"), new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
				new Team("VfB Stuttgart"), new Team("Eintracht Frankfurt"));
		assertThat(homeTeamScoreResults).containsExactly(4, 2, 2, 0, 0, 0, 3);
		assertThat(awayTeamScoreResults).containsExactly(3, 1, 2, 2, 4, 2, 2);
	}

	@Test
	void gameResultsString_parseDifferentGameResults_isCorrect() {
		List<GameResult> gameResults = GameResult
				.parseGameResults("Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n"
						+ "FC Schalke 04 2:2 TSG Hoffenheim\n" + "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n"
						+ "RB Leipzig 0:4 1. FSV Mainz 05\n" + "SV Werder Bremen 0:2 VfB Stuttgart");

		List<Team> homeTeamResults = gameResults.stream().map(GameResult::getHomeTeam).collect(Collectors.toList());
		List<Team> awayTeamResults = gameResults.stream().map(GameResult::getAwayTeam).collect(Collectors.toList());
		List<Integer> homeTeamScoreResults = gameResults.stream().map(GameResult::getHomeTeamScore)
				.collect(Collectors.toList());
		List<Integer> awayTeamScoreResults = gameResults.stream().map(GameResult::getAwayTeamScore)
				.collect(Collectors.toList());

		assertThat(homeTeamResults).containsExactly(new Team("Hertha BSC"), new Team("VfL Wolfsburg"),
				new Team("FC Schalke 04"), new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
				new Team("SV Werder Bremen"));
		assertThat(awayTeamResults).containsExactly(new Team("1. FC Köln"), new Team("FC Augsburg"),
				new Team("TSG Hoffenheim"), new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
				new Team("VfB Stuttgart"));
		assertThat(homeTeamScoreResults).containsExactly(4, 2, 2, 0, 0, 0);
		assertThat(awayTeamScoreResults).containsExactly(3, 1, 2, 2, 4, 2);
	}
}
