package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static tech.bison.trainee2021.soccer_table_stats.util.StreamUtils.mapToList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

public class League {
	private static final String TEAM_DESIGNATION = "Team";
	private static final int SPACE_BETWEEN_LONGEST_NAME_AND_RANKINGNUMBER = 7;
	private static final int WIN_POINTS = 3;
	private static final int TIE_POINTS = 1;
	private final List<GameResult> gameResults = new ArrayList<>();

	public String toFormattedTable() {
		Map<Team, Map<StatisticsTableColumn, Integer>> statsTable = generateStatisticsTable();
		int numOfTeamsDecPlaces = String.valueOf(statsTable.size()).length();
		int longestTeamNamePlaces = findTeamWithLongestName(statsTable).getName().length();
		int spacesBetweenRankAndGrid = longestTeamNamePlaces + SPACE_BETWEEN_LONGEST_NAME_AND_RANKINGNUMBER;
		int spacesBetweenColumns = String.valueOf(findLargestValueAmountInTable(statsTable)).length() + 1;
		String columnLabels = stream(StatisticsTableColumn.values()).map(column -> String.valueOf(column.translate()))
				.collect(joining(toSpaces(spacesBetweenColumns)));
		String firstLine = format("%s#%s%s%s%s", toSpaces(numOfTeamsDecPlaces),
				toSpaces(spacesBetweenRankAndGrid - TEAM_DESIGNATION.length()), TEAM_DESIGNATION,
				toSpaces(spacesBetweenColumns), columnLabels);
		String secondLine = "-".repeat(firstLine.length());
		return format("%s%n%s%n%s", firstLine, secondLine,
				buildTableContent(statsTable, numOfTeamsDecPlaces, spacesBetweenRankAndGrid, spacesBetweenColumns));
	}

	private String buildTableContent(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable, int numOfTeamsDecPlaces,
			int spacesBetweenRankAndGrid, int spacesBetweenColumns) {
		StringBuilder tableContent = new StringBuilder();
		List<Team> teamsOrdered = new ArrayList<>(statsTable.keySet());
		List<Map<StatisticsTableColumn, Integer>> gridOrdered = new ArrayList<>(statsTable.values());
		IntStream.range(0, statsTable.size()).forEach(i -> buildTableRow(numOfTeamsDecPlaces, spacesBetweenRankAndGrid,
				spacesBetweenColumns, tableContent, teamsOrdered, gridOrdered, i));
		return tableContent.toString().replaceAll("\\s+$", "");
	}

	private void buildTableRow(int numOfTeamsDecPlaces, int spacesBetweenRankAndGrid, int spacesBetweenColumns,
			StringBuilder tableContent, List<Team> teamsOrdered, List<Map<StatisticsTableColumn, Integer>> gridOrdered,
			int i) {
		int counter = i + 1;
		String spaceBefore = toSpaces(numOfTeamsDecPlaces + 1 - String.valueOf(counter).length());
		String teamName = teamsOrdered.get(i).getName();
		String spaceBetweenRankAndTeam = toSpaces(spacesBetweenRankAndGrid - teamName.length());
		Collection<Integer> gridRowValues = gridOrdered.get(i).values();
		StringBuilder gridRow = new StringBuilder();
		gridRowValues.stream()
				.map(gridRowValue -> toSpaces(spacesBetweenColumns + 1 - String.valueOf(gridRowValue).length())
						+ gridRowValue)
				.forEach(gridRow::append);
		tableContent.append(format("%s%s%s%s%s%n", spaceBefore, counter, spaceBetweenRankAndTeam, teamName, gridRow));
	}

	private String toSpaces(int numberOfSpaces) {
		return " ".repeat(numberOfSpaces);
	}

	private int findLargestValueAmountInTable(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable) {
		int largestValueAmount = 0;
		for (Map<StatisticsTableColumn, Integer> teamStat : statsTable.values()) {
			for (int amount : mapToList(teamStat.values(), Math::abs)) {
				if (amount > largestValueAmount) {
					largestValueAmount = amount;
				}
			}
		}
		return largestValueAmount;
	}

	private Team findTeamWithLongestName(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable) {
		Team teamWithLongestName = new Team("");
		for (Team team : statsTable.keySet()) {
			if (team.getName().length() > teamWithLongestName.getName().length()) {
				teamWithLongestName = team;
			}
		}
		return teamWithLongestName;
	}

	public void addGameResults(List<GameResult> newGameResults) {
		gameResults.addAll(newGameResults);
	}

	public Map<Team, Map<StatisticsTableColumn, Integer>> generateStatisticsTable() {
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
		return sortStats(statisticsTable, byPointsGoalDifferenceAlphabet());
	}

	private Comparator<Entry<Team, Map<StatisticsTableColumn, Integer>>> byPointsGoalDifferenceAlphabet() {
		return comparing((Entry<Team, Map<StatisticsTableColumn, Integer>> entry) -> entry.getValue()
				.get(StatisticsTableColumn.POINTS))
				.thenComparing(entry -> entry.getValue().get(StatisticsTableColumn.GOALDIFFERENCE))
				.thenComparing(entry -> entry.getValue().get(StatisticsTableColumn.WINS))
				.thenComparing(entry -> entry.getKey().getName().toLowerCase(), reverseOrder());
	}

	private Map<Team, Map<StatisticsTableColumn, Integer>> sortStats(
			Map<Team, Map<StatisticsTableColumn, Integer>> unsortedStats,
			Comparator<Entry<Team, Map<StatisticsTableColumn, Integer>>> byValues) {
		return unsortedStats.entrySet().stream().sorted(byValues.reversed()).collect(LinkedHashMap::new,
				(map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);
	}

	private void initializeTeamStatistics(Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable, Team team) {
		if (statisticsTable.get(team) == null) {
			EnumMap<StatisticsTableColumn, Integer> initialTeamStatistics = new EnumMap<>(StatisticsTableColumn.class);
			for (StatisticsTableColumn statisticsTableColumn : StatisticsTableColumn.values()) {
				initialTeamStatistics.put(statisticsTableColumn, 0);
			}
			statisticsTable.put(team, initialTeamStatistics);
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
