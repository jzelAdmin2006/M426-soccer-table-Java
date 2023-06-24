package tech.bison.trainee2021.SoccerTableStats.structure.business;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult.parseGameResult;
import static tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult.parseGameResults;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tech.bison.trainee2021.SoccerTableStats.InputFormat;
import tech.bison.trainee2021.SoccerTableStats.structure.data.GameResultDto;

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
	void gameResultText_parseGameResult_isCorrect() {
		GameResult gameResult = parseGameResult("Hertha BSC 4:3 1. FC Köln", InputFormat.TEXT);

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
	void gameResultText_parseDifferentGameResult_isCorrect() {
		GameResult gameResult = parseGameResult("VfL Wolfsburg 2:1 FC Augsburg", InputFormat.TEXT);

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
	void gameResultsText_parseGameResults_isCorrect() {
		List<GameResult> gameResults = parseGameResults(
				"Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n" + "FC Schalke 04 2:2 TSG Hoffenheim\n"
						+ "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n" + "RB Leipzig 0:4 1. FSV Mainz 05\n"
						+ "SV Werder Bremen 0:2 VfB Stuttgart\n" + "Borussia Dortmund 3:2 Eintracht Frankfurt",
				InputFormat.TEXT);

		List<Team> homeTeamResults = gameResults.stream().map(GameResult::getHomeTeam).collect(toList());
		List<Team> awayTeamResults = gameResults.stream().map(GameResult::getAwayTeam).collect(toList());
		List<Integer> homeTeamScoreResults = gameResults.stream().map(GameResult::getHomeTeamScore).collect(toList());
		List<Integer> awayTeamScoreResults = gameResults.stream().map(GameResult::getAwayTeamScore).collect(toList());

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
	void gameResultsText_parseDifferentGameResults_isCorrect() {
		List<GameResult> gameResults = GameResult
				.parseGameResults("Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n"
						+ "FC Schalke 04 2:2 TSG Hoffenheim\n" + "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n"
						+ "RB Leipzig 0:4 1. FSV Mainz 05\n" + "SV Werder Bremen 0:2 VfB Stuttgart", InputFormat.TEXT);

		List<Team> homeTeamResults = gameResults.stream().map(GameResult::getHomeTeam).collect(toList());
		List<Team> awayTeamResults = gameResults.stream().map(GameResult::getAwayTeam).collect(toList());
		List<Integer> homeTeamScoreResults = gameResults.stream().map(GameResult::getHomeTeamScore).collect(toList());
		List<Integer> awayTeamScoreResults = gameResults.stream().map(GameResult::getAwayTeamScore).collect(toList());

		assertThat(homeTeamResults).containsExactly(new Team("Hertha BSC"), new Team("VfL Wolfsburg"),
				new Team("FC Schalke 04"), new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
				new Team("SV Werder Bremen"));
		assertThat(awayTeamResults).containsExactly(new Team("1. FC Köln"), new Team("FC Augsburg"),
				new Team("TSG Hoffenheim"), new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
				new Team("VfB Stuttgart"));
		assertThat(homeTeamScoreResults).containsExactly(4, 2, 2, 0, 0, 0);
		assertThat(awayTeamScoreResults).containsExactly(3, 1, 2, 2, 4, 2);
	}

	@Test
	void gameResultJson_parseGameResult_isCorrect() {
		Gson gson = new Gson();

		GameResultDto gameResult = gson.fromJson(
				"{\"homeTeam\":\"FC Wil 1900\",\"awayTeam\":\"SC Kriens\",\"homeGoals\":1,\"awayGoals\":4}",
				GameResultDto.class);

		assertThat(gameResult.getHomeTeam()).isEqualTo("FC Wil 1900");
		assertThat(gameResult.getAwayTeam()).isEqualTo("SC Kriens");
		assertThat(gameResult.getHomeGoals()).isEqualTo(1);
		assertThat(gameResult.getAwayGoals()).isEqualTo(4);
	}

	@Test
	void gameResultsJson_parseGameResults_isCorrect() {
		Gson gson = new Gson();

		Type gameResultListType = new TypeToken<List<GameResultDto>>() {
		}.getType();
		List<GameResultDto> gameResults = gson.fromJson(
				"[{\"homeTeam\":\"FC Vaduz\",\"awayTeam\":\"FC Winterthur\",\"homeGoals\":3,\"awayGoals\":0},"
						+ "{\"homeTeam\":\"FC Schaffhausen\",\"awayTeam\":\"FC Winterthur\",\"homeGoals\":0,\"awayGoals\":0}"
						+ ",{\"homeTeam\":\"FC Thun\",\"awayTeam\":\"FC Winterthur\",\"homeGoals\":3,\"awayGoals\":1}]",
				gameResultListType);

		assertThat(gameResults).containsExactly(new GameResultDto("FC Vaduz", "FC Winterthur", 3, 0),
				new GameResultDto("FC Schaffhausen", "FC Winterthur", 0, 0),
				new GameResultDto("FC Thun", "FC Winterthur", 3, 1));
	}

	@Test
	void gameResultJson_automaticallyParseGameResult_isCorrect() {
		GameResult gameResult = parseGameResult(
				"{\"homeTeam\":\"Hertha BSC\",\"awayTeam\":\"1. FC Köln\",\"homeGoals\":4,\"awayGoals\":3}",
				InputFormat.JSON);

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
	void gameResultsText_automaticallyParseGameResults_isCorrect() {
		List<GameResult> gameResults = parseGameResults(
				"[{\"homeTeam\":\"Hertha BSC\",\"awayTeam\":\"1. FC Köln\",\"homeGoals\":4,\"awayGoals\":3},"
						+ "{\"homeTeam\":\"VfL Wolfsburg\",\"awayTeam\":\"FC Augsburg\",\"homeGoals\":2,\"awayGoals\":1},"
						+ "{\"homeTeam\":\"FC Schalke 04\",\"awayTeam\":\"TSG Hoffenheim\",\"homeGoals\":2,\"awayGoals\":2},"
						+ "{\"homeTeam\":\"Borussia Mönchengladbach\",\"awayTeam\":\"Sport-Club Freiburg\",\"homeGoals\":0,\"awayGoals\":2},"
						+ "{\"homeTeam\":\"RB Leipzig\",\"awayTeam\":\"1. FSV Mainz 05\",\"homeGoals\":0,\"awayGoals\":4},"
						+ "{\"homeTeam\":\"SV Werder Bremen\",\"awayTeam\":\"VfB Stuttgart\",\"homeGoals\":0,\"awayGoals\":2},"
						+ "{\"homeTeam\":\"Borussia Dortmund\",\"awayTeam\":\"Eintracht Frankfurt\",\"homeGoals\":3,\"awayGoals\":2}]",
				InputFormat.JSON);

		List<Team> homeTeamResults = gameResults.stream().map(GameResult::getHomeTeam).collect(toList());
		List<Team> awayTeamResults = gameResults.stream().map(GameResult::getAwayTeam).collect(toList());
		List<Integer> homeTeamScoreResults = gameResults.stream().map(GameResult::getHomeTeamScore).collect(toList());
		List<Integer> awayTeamScoreResults = gameResults.stream().map(GameResult::getAwayTeamScore).collect(toList());

		assertThat(homeTeamResults).containsExactly(new Team("Hertha BSC"), new Team("VfL Wolfsburg"),
				new Team("FC Schalke 04"), new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
				new Team("SV Werder Bremen"), new Team("Borussia Dortmund"));
		assertThat(awayTeamResults).containsExactly(new Team("1. FC Köln"), new Team("FC Augsburg"),
				new Team("TSG Hoffenheim"), new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
				new Team("VfB Stuttgart"), new Team("Eintracht Frankfurt"));
		assertThat(homeTeamScoreResults).containsExactly(4, 2, 2, 0, 0, 0, 3);
		assertThat(awayTeamScoreResults).containsExactly(3, 1, 2, 2, 4, 2, 2);
	}
}
