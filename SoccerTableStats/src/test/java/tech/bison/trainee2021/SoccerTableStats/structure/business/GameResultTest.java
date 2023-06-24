package tech.bison.trainee2021.SoccerTableStats.structure.business;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult.parseGameResult;
import static tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult.parseGameResults;
import static tech.bison.trainee2021.SoccerTableStats.util.StreamUtils.mapToList;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tech.bison.trainee2021.SoccerTableStats.InputFormat;
import tech.bison.trainee2021.SoccerTableStats.structure.data.GameResultDto;;

class GameResultTest {

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
	void newGameResultWitDifferentTeamsAndScores_getTeamsAndScores_isCorrect() {
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

	@ParameterizedTest(name = "gameResult{1}_{6}GameResult_isCorrect")
	@MethodSource("provideValuesForParsingSingleGameResult")
	void gameResultString_parseGameResult_isCorrect(String gameResultString, InputFormat inputFormat,
			Team expectedHomeTeam, Team expectedAwayTeam, int expectedHomeTeamScore, int expectedAwayTeamScore,
			String parsingDesignation) {
		GameResult gameResult = parseGameResult(gameResultString, inputFormat);

		Team homeTeamResult = gameResult.getHomeTeam();
		Team awayTeamResult = gameResult.getAwayTeam();
		int homeTeamScoreResult = gameResult.getHomeTeamScore();
		int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(expectedHomeTeam);
		assertThat(awayTeamResult).isEqualTo(expectedAwayTeam);
		assertThat(homeTeamScoreResult).isEqualTo(expectedHomeTeamScore);
		assertThat(awayTeamScoreResult).isEqualTo(expectedAwayTeamScore);
	}

	private static Stream<Arguments> provideValuesForParsingSingleGameResult() {
		return Stream.of(
				Arguments.of("Hertha BSC 4:3 1. FC Köln", InputFormat.TEXT, new Team("Hertha BSC"),
						new Team("1. FC Köln"), 4, 3, "parse"),
				Arguments.of("VfL Wolfsburg 2:1 FC Augsburg", InputFormat.TEXT, new Team("VfL Wolfsburg"),
						new Team("FC Augsburg"), 2, 1, "parseDifferent"),
				Arguments.of(
						"{\"homeTeam\":\"Hertha BSC\",\"awayTeam\":\"1. FC Köln\",\"homeGoals\":4,\"awayGoals\":3}",
						InputFormat.JSON, new Team("Hertha BSC"), new Team("1. FC Köln"), 4, 3, "automaticallyParse"));
	}

	@ParameterizedTest(name = "gameResults{1}_{6}GameResults_isCorrect")
	@MethodSource("provideValuesForParsingMultipleGameResults")
	void gameResultsString_parseGameResults_isCorrect(String gameResultsString, InputFormat inputFormat,
			List<Team> expectedHomeTeams, List<Team> expectedAwayTeams, List<Integer> expectedHomeTeamScores,
			List<Integer> expectedAwayTeamScores, String parsingDesignation) {
		List<GameResult> gameResults = parseGameResults(gameResultsString, inputFormat);

		List<Team> homeTeamResults = mapToList(gameResults, GameResult::getHomeTeam);
		List<Team> awayTeamResults = mapToList(gameResults, GameResult::getAwayTeam);
		List<Integer> homeTeamScoreResults = mapToList(gameResults, GameResult::getHomeTeamScore);
		List<Integer> awayTeamScoreResults = mapToList(gameResults, GameResult::getAwayTeamScore);

		assertThat(homeTeamResults).containsExactlyElementsOf(expectedHomeTeams);
		assertThat(awayTeamResults).containsExactlyElementsOf(expectedAwayTeams);
		assertThat(homeTeamScoreResults).containsExactlyElementsOf(expectedHomeTeamScores);
		assertThat(awayTeamScoreResults).containsExactlyElementsOf(expectedAwayTeamScores);
	}

	private static Stream<Arguments> provideValuesForParsingMultipleGameResults() {
		return Stream.of(Arguments.of(
				"Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n" + "FC Schalke 04 2:2 TSG Hoffenheim\n"
						+ "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n" + "RB Leipzig 0:4 1. FSV Mainz 05\n"
						+ "SV Werder Bremen 0:2 VfB Stuttgart\n" + "Borussia Dortmund 3:2 Eintracht Frankfurt",
				InputFormat.TEXT,
				List.of(new Team("Hertha BSC"), new Team("VfL Wolfsburg"), new Team("FC Schalke 04"),
						new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"), new Team("SV Werder Bremen"),
						new Team("Borussia Dortmund")),
				List.of(new Team("1. FC Köln"), new Team("FC Augsburg"), new Team("TSG Hoffenheim"),
						new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"), new Team("VfB Stuttgart"),
						new Team("Eintracht Frankfurt")),
				List.of(4, 2, 2, 0, 0, 0, 3), List.of(3, 1, 2, 2, 4, 2, 2), "parse"),
				Arguments.of(
						"Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n"
								+ "FC Schalke 04 2:2 TSG Hoffenheim\n"
								+ "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n"
								+ "RB Leipzig 0:4 1. FSV Mainz 05\n" + "SV Werder Bremen 0:2 VfB Stuttgart",
						InputFormat.TEXT,
						List.of(new Team("Hertha BSC"), new Team("VfL Wolfsburg"), new Team("FC Schalke 04"),
								new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
								new Team("SV Werder Bremen")),
						List.of(new Team("1. FC Köln"), new Team("FC Augsburg"), new Team("TSG Hoffenheim"),
								new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
								new Team("VfB Stuttgart")),
						List.of(4, 2, 2, 0, 0, 0), List.of(3, 1, 2, 2, 4, 2), "parseDifferent"),
				Arguments.of(
						"[{\"homeTeam\":\"Hertha BSC\",\"awayTeam\":\"1. FC Köln\",\"homeGoals\":4,\"awayGoals\":3},"
								+ "{\"homeTeam\":\"VfL Wolfsburg\",\"awayTeam\":\"FC Augsburg\",\"homeGoals\":2,\"awayGoals\":1},"
								+ "{\"homeTeam\":\"FC Schalke 04\",\"awayTeam\":\"TSG Hoffenheim\",\"homeGoals\":2,\"awayGoals\":2},"
								+ "{\"homeTeam\":\"Borussia Mönchengladbach\",\"awayTeam\":\"Sport-Club Freiburg\",\"homeGoals\":0,\"awayGoals\":2},"
								+ "{\"homeTeam\":\"RB Leipzig\",\"awayTeam\":\"1. FSV Mainz 05\",\"homeGoals\":0,\"awayGoals\":4},"
								+ "{\"homeTeam\":\"SV Werder Bremen\",\"awayTeam\":\"VfB Stuttgart\",\"homeGoals\":0,\"awayGoals\":2},"
								+ "{\"homeTeam\":\"Borussia Dortmund\",\"awayTeam\":\"Eintracht Frankfurt\",\"homeGoals\":3,\"awayGoals\":2}]",
						InputFormat.JSON,
						List.of(new Team("Hertha BSC"), new Team("VfL Wolfsburg"), new Team("FC Schalke 04"),
								new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
								new Team("SV Werder Bremen"), new Team("Borussia Dortmund")),
						List.of(new Team("1. FC Köln"), new Team("FC Augsburg"), new Team("TSG Hoffenheim"),
								new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"), new Team("VfB Stuttgart"),
								new Team("Eintracht Frankfurt")),
						List.of(4, 2, 2, 0, 0, 0, 3), List.of(3, 1, 2, 2, 4, 2, 2), "automaticallyParse"));
	}

	@Test
	void gameResultJson_parseGameResult_dtoIsCorrect() {
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
	void gameResultsJson_parseGameResults_dtoIsCorrect() {
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

}
