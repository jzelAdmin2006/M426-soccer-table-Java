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
}
