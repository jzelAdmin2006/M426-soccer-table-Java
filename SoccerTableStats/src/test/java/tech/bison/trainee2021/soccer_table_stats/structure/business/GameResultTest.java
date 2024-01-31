package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.bison.trainee2021.soccer_table_stats.structure.business.GameResult.parseGameResult;
import static tech.bison.trainee2021.soccer_table_stats.structure.business.GameResult.parseGameResults;
import static tech.bison.trainee2021.soccer_table_stats.util.StreamUtils.mapToList;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tech.bison.trainee2021.soccer_table_stats.InputFormat;
import tech.bison.trainee2021.soccer_table_stats.structure.data.GameResultDto;;

class GameResultTest {

	@Test
	void newGameResultWithTeamsAndScores_getTeamsAndScores_isCorrect() {
		final GameResult gameResult = new GameResult(new Team("HomeTeam"), new Team("AwayTeam"), 0, 1);

		final Team homeTeamResult = gameResult.getHomeTeam();
		final Team awayTeamResult = gameResult.getAwayTeam();
		final int homeTeamScoreResult = gameResult.getHomeTeamScore();
		final int awayTeamScoreResult = gameResult.getAwayTeamScore();

		assertThat(homeTeamResult).isEqualTo(new Team("HomeTeam"));
		assertThat(awayTeamResult).isEqualTo(new Team("AwayTeam"));
		assertThat(homeTeamScoreResult).isZero();
		assertThat(awayTeamScoreResult).isOne();
	}

	@Test
	void newGameResultWitDifferentTeamsAndScores_getTeamsAndScores_isCorrect() {
		final GameResult gameResult = new GameResult(new Team("HomeTeam2"), new Team("AwayTeam2"), 1, 2);

		final Team homeTeamResult = gameResult.getHomeTeam();
		final Team awayTeamResult = gameResult.getAwayTeam();
		final int homeTeamScoreResult = gameResult.getHomeTeamScore();
		final int awayTeamScoreResult = gameResult.getAwayTeamScore();

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
		final GameResult gameResult = parseGameResult(gameResultString, inputFormat);

		final Team homeTeamResult = gameResult.getHomeTeam();
		final Team awayTeamResult = gameResult.getAwayTeam();
		final int homeTeamScoreResult = gameResult.getHomeTeamScore();
		final int awayTeamScoreResult = gameResult.getAwayTeamScore();

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
				Arguments.of("""
						{
							"awayGoals": 3,
							"awayTeam": "1. FC Köln",
							"homeGoals": 4,
							"homeTeam": "Hertha BSC"
						}\
						""", InputFormat.JSON, new Team("Hertha BSC"), new Team("1. FC Köln"), 4, 3,
						"automaticallyParse"));
	}

	@ParameterizedTest(name = "gameResults{1}_{6}GameResults_isCorrect")
	@MethodSource("provideValuesForParsingMultipleGameResults")
	void gameResultsString_parseGameResults_isCorrect(String gameResultsString, InputFormat inputFormat,
			List<Team> expectedHomeTeams, List<Team> expectedAwayTeams, List<Integer> expectedHomeTeamScores,
			List<Integer> expectedAwayTeamScores, String parsingDesignation) {
		final List<GameResult> gameResults = parseGameResults(gameResultsString, inputFormat);

		final List<Team> homeTeamResults = mapToList(gameResults, GameResult::getHomeTeam);
		final List<Team> awayTeamResults = mapToList(gameResults, GameResult::getAwayTeam);
		final List<Integer> homeTeamScoreResults = mapToList(gameResults, GameResult::getHomeTeamScore);
		final List<Integer> awayTeamScoreResults = mapToList(gameResults, GameResult::getAwayTeamScore);

		assertThat(homeTeamResults).containsExactlyElementsOf(expectedHomeTeams);
		assertThat(awayTeamResults).containsExactlyElementsOf(expectedAwayTeams);
		assertThat(homeTeamScoreResults).containsExactlyElementsOf(expectedHomeTeamScores);
		assertThat(awayTeamScoreResults).containsExactlyElementsOf(expectedAwayTeamScores);
	}

	private static Stream<Arguments> provideValuesForParsingMultipleGameResults() {
		return Stream.of(
				Arguments.of("""
						Hertha BSC 4:3 1. FC Köln
						VfL Wolfsburg 2:1 FC Augsburg
						FC Schalke 04 2:2 TSG Hoffenheim
						Borussia Mönchengladbach 0:2 Sport-Club Freiburg
						RB Leipzig 0:4 1. FSV Mainz 05
						SV Werder Bremen 0:2 VfB Stuttgart
						Borussia Dortmund 3:2 Eintracht Frankfurt\
						""", InputFormat.TEXT,
						List.of(new Team("Hertha BSC"), new Team("VfL Wolfsburg"), new Team("FC Schalke 04"),
								new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
								new Team("SV Werder Bremen"), new Team("Borussia Dortmund")),
						List.of(new Team("1. FC Köln"), new Team("FC Augsburg"), new Team("TSG Hoffenheim"),
								new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"), new Team("VfB Stuttgart"),
								new Team("Eintracht Frankfurt")),
						List.of(4, 2, 2, 0, 0, 0, 3), List.of(3, 1, 2, 2, 4, 2, 2), "parse"),
				Arguments.of("""
						Hertha BSC 4:3 1. FC Köln
						VfL Wolfsburg 2:1 FC Augsburg
						FC Schalke 04 2:2 TSG Hoffenheim
						Borussia Mönchengladbach 0:2 Sport-Club Freiburg
						RB Leipzig 0:4 1. FSV Mainz 05
						SV Werder Bremen 0:2 VfB Stuttgart\
						""", InputFormat.TEXT,
						List.of(new Team("Hertha BSC"), new Team("VfL Wolfsburg"), new Team("FC Schalke 04"),
								new Team("Borussia Mönchengladbach"), new Team("RB Leipzig"),
								new Team("SV Werder Bremen")),
						List.of(new Team("1. FC Köln"), new Team("FC Augsburg"), new Team("TSG Hoffenheim"),
								new Team("Sport-Club Freiburg"), new Team("1. FSV Mainz 05"),
								new Team("VfB Stuttgart")),
						List.of(4, 2, 2, 0, 0, 0), List.of(3, 1, 2, 2, 4, 2), "parseDifferent"),
				Arguments.of("""
						[
						    {
							    "awayGoals": 3,
							    "awayTeam": "1. FC Köln",
							    "homeGoals": 4,
							    "homeTeam": "Hertha BSC"
						    },
						    {
							    "awayGoals": 1,
							    "awayTeam": "FC Augsburg",
							    "homeGoals": 2,
							    "homeTeam": "VfL Wolfsburg"
						    },
						    {
							    "awayGoals": 2,
							    "awayTeam": "TSG Hoffenheim",
							    "homeGoals": 2,
							    "homeTeam": "FC Schalke 04"
						    },
						    {
							    "awayGoals": 2,
							    "awayTeam": "Sport-Club Freiburg",
							    "homeGoals": 0,
							    "homeTeam": "Borussia Mönchengladbach"
						    },
						    {
							    "awayGoals": 4,
							    "awayTeam": "1. FSV Mainz 05",
							    "homeGoals": 0,
							    "homeTeam": "RB Leipzig"
						    },
						    {
							    "awayGoals": 2,
							    "awayTeam": "VfB Stuttgart",
							    "homeGoals": 0,
							    "homeTeam": "SV Werder Bremen"
						    },
						    {
							    "awayGoals": 2,
							    "awayTeam": "Eintracht Frankfurt",
							    "homeGoals": 3,
							    "homeTeam": "Borussia Dortmund"
						    }
						]\
						""", InputFormat.JSON,
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
		final Gson gson = new Gson();

		final GameResultDto gameResult = gson.fromJson("""
				{
					"awayGoals": 4,
					"awayTeam": "SC Kriens",
					"homeGoals": 1,
					"homeTeam": "FC Wil 1900"
				}\
				""", GameResultDto.class);

		assertThat(gameResult.getHomeTeam()).isEqualTo("FC Wil 1900");
		assertThat(gameResult.getAwayTeam()).isEqualTo("SC Kriens");
		assertThat(gameResult.getHomeGoals()).isEqualTo(1);
		assertThat(gameResult.getAwayGoals()).isEqualTo(4);
	}

	@Test
	void gameResultsJson_parseGameResults_dtoIsCorrect() {
		final Gson gson = new Gson();

		final Type gameResultListType = new TypeToken<List<GameResultDto>>() {
		}.getType();
		final List<GameResultDto> gameResults = gson.fromJson("""
				[
				    {
					    "awayGoals": 0,
					    "awayTeam": "FC Winterthur",
					    "homeGoals": 3,
					    "homeTeam": "FC Vaduz"
				    },
				    {
					    "awayGoals": 0,
					    "awayTeam": "FC Winterthur",
					    "homeGoals": 0,
					    "homeTeam": "FC Schaffhausen"
				    },
				    {
					    "awayGoals": 1,
					    "awayTeam": "FC Winterthur",
					    "homeGoals": 3,
					    "homeTeam": "FC Thun"
				    }
				]\
				""", gameResultListType);

		assertThat(gameResults).containsExactly(new GameResultDto("FC Vaduz", "FC Winterthur", 3, 0),
				new GameResultDto("FC Schaffhausen", "FC Winterthur", 0, 0),
				new GameResultDto("FC Thun", "FC Winterthur", 3, 1));
	}

	@Test
	void nullInputFormat_parseGameResults_isIllegalArgument() {
		final InputFormat inputFormat = null;

		final ThrowingCallable shouldRaiseThrowable = () -> GameResult
				.parseGameResults("""
						{
							"awayGoals": 4,
							"awayTeam": "SC Kriens",
							"homeGoals": 1,
							"homeTeam": "FC Wil 1900"
						}\
						""", inputFormat);

		assertThatThrownBy(shouldRaiseThrowable).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("cannot be null");
	}

}
