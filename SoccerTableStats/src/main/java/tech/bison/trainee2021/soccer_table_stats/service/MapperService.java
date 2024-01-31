package tech.bison.trainee2021.soccer_table_stats.service;

import tech.bison.trainee2021.soccer_table_stats.structure.business.GameResult;
import tech.bison.trainee2021.soccer_table_stats.structure.business.Team;
import tech.bison.trainee2021.soccer_table_stats.structure.data.GameResultDto;

public class MapperService {

	public GameResult fromDto(GameResultDto gameResult) {
		return new GameResult(new Team(gameResult.getHomeTeam()), new Team(gameResult.getAwayTeam()),
				gameResult.getHomeGoals(), gameResult.getAwayGoals());
	}
}
