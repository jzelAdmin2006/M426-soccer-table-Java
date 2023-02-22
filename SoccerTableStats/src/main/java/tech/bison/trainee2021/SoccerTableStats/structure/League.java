package tech.bison.trainee2021.SoccerTableStats.structure;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class League {
	private static final int WIN_POINTS = 3;
	private static final int TIE_POINTS = 1;
	private final List<GameResult> gameResults = new ArrayList<>();

	public void addGameResults(List<GameResult> newGameResults) {
		gameResults.addAll(newGameResults);
	}

	public Map<Team, Map<StatisticsTableColumn, Integer>> getStatisticsTable() {
		Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = new HashMap<>();
		for (GameResult gameResult : gameResults) {
			Team homeTeam = gameResult.getHomeTeam();
			Team awayTeam = gameResult.getAwayTeam();
			int homeTeamScore = gameResult.getHomeTeamScore();
			int awayTeamScore = gameResult.getAwayTeamScore();
			initializeTeamStatistics(statisticsTable, homeTeam);
			initializeTeamStatistics(statisticsTable, awayTeam);
			Map<StatisticsTableColumn, Integer> homeTeamStatistics = statisticsTable.get(homeTeam);
			Map<StatisticsTableColumn, Integer> awayTeamStatistics = statisticsTable.get(awayTeam);
			updateScoreStatistics(homeTeamScore, awayTeamScore, homeTeamStatistics);
			updateScoreStatistics(awayTeamScore, homeTeamScore, awayTeamStatistics);
			updateOutcomeStatistics(homeTeamScore, awayTeamScore, homeTeamStatistics, awayTeamStatistics);
		}
		return sortStats(statisticsTable, byPointsAndGoalDifference());
	}

	private Comparator<Entry<Team, Map<StatisticsTableColumn, Integer>>> byPointsAndGoalDifference() {
		return Comparator
				.comparing((Map.Entry<Team, Map<StatisticsTableColumn, Integer>> entry) -> entry.getValue()
						.get(StatisticsTableColumn.POINTS))
				.thenComparing(entry -> entry.getValue().get(StatisticsTableColumn.GOALDIFFERENCE))
				.thenComparing(entry -> entry.getValue().get(StatisticsTableColumn.WINS));
	}

	private Map<Team, Map<StatisticsTableColumn, Integer>> sortStats(
			Map<Team, Map<StatisticsTableColumn, Integer>> unsortedStats,
			Comparator<Entry<Team, Map<StatisticsTableColumn, Integer>>> byValues) {
		return unsortedStats.entrySet().stream().sorted(byValues.reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, this::mergeMaps, LinkedHashMap::new));
	}

	private Map<StatisticsTableColumn, Integer> mergeMaps(Map<StatisticsTableColumn, Integer> firstMap,
			Map<StatisticsTableColumn, Integer> secondMap) {
		Map<StatisticsTableColumn, Integer> mergedMap = new LinkedHashMap<>(firstMap);
		secondMap.forEach(
				(key, value) -> mergedMap.merge(key, value, (firstValue, secondValue) -> firstValue + secondValue));
		return mergedMap;
	}

	private void initializeTeamStatistics(Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable, Team team) {
		if (statisticsTable.get(team) == null) {
			statisticsTable.put(team, new EnumMap<StatisticsTableColumn, Integer>(StatisticsTableColumn.class));
		}
	}

	private void updateScoreStatistics(int teamScore, int enemyTeamScore,
			Map<StatisticsTableColumn, Integer> teamStatistics) {
		add(teamStatistics, StatisticsTableColumn.GOALS, teamScore);
		add(teamStatistics, StatisticsTableColumn.COUNTERGOALS, enemyTeamScore);
		add(teamStatistics, StatisticsTableColumn.GOALDIFFERENCE, teamScore - enemyTeamScore);
	}

	private void updateOutcomeStatistics(int homeTeamScore, int awayTeamScore,
			Map<StatisticsTableColumn, Integer> homeTeamStatistics,
			Map<StatisticsTableColumn, Integer> awayTeamStatistics) {
		if (homeTeamScore == awayTeamScore) {
			updateTie(homeTeamStatistics);
			updateTie(awayTeamStatistics);
		} else if (homeTeamScore > awayTeamScore) {
			updateWinLoss(homeTeamStatistics, awayTeamStatistics);
		} else {
			updateWinLoss(awayTeamStatistics, homeTeamStatistics);
		}
	}

	private void updateWinLoss(Map<StatisticsTableColumn, Integer> winnerTeamStatistics,
			Map<StatisticsTableColumn, Integer> loserTeamStatistics) {
		increment(winnerTeamStatistics, StatisticsTableColumn.WINS);
		increment(loserTeamStatistics, StatisticsTableColumn.LOSSES);
		add(winnerTeamStatistics, StatisticsTableColumn.POINTS, WIN_POINTS);
	}

	private void updateTie(Map<StatisticsTableColumn, Integer> statistics) {
		increment(statistics, StatisticsTableColumn.TIES);
		add(statistics, StatisticsTableColumn.POINTS, TIE_POINTS);
	}

	private void add(Map<StatisticsTableColumn, Integer> statistics, StatisticsTableColumn column, int summand) {
		statistics.put(column, add(statistics.get(column), summand));
	}

	private Integer add(Integer value, int summand) {
		return ofNullable(value).map(times -> times + summand).orElse(summand);
	}

	private void increment(Map<StatisticsTableColumn, Integer> statistics, StatisticsTableColumn column) {
		add(statistics, column, 1);
	}
}
