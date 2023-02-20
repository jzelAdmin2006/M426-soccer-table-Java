package tech.bison.trainee2021.SoccerTableStats.structure;

import static org.assertj.core.api.Assertions.assertThat;

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
}
