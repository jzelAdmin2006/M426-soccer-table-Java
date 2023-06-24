package tech.bison.trainee2021.SoccerTableStats.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult;
import tech.bison.trainee2021.SoccerTableStats.structure.business.Team;
import tech.bison.trainee2021.SoccerTableStats.structure.data.GameResultDto;

class MapperServiceTest {
	@Test
	void newGameResultDto_fromDto_isCorrect() {
		GameResultDto gameResult = new GameResultDto("asdf", "qwert", 5, 8);

		GameResult result = new MapperService().fromDto(gameResult);

		assertThat(result.getHomeTeam()).isEqualTo(new Team("asdf"));
		assertThat(result.getAwayTeam()).isEqualTo(new Team("qwert"));
		assertThat(result.getHomeTeamScore()).isEqualTo(5);
		assertThat(result.getAwayTeamScore()).isEqualTo(8);
	}
}
