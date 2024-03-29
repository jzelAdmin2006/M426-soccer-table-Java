package tech.bison.trainee2021.soccer_table_stats.structure.business;

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
		final Map<Team, Map<StatisticsTableColumn, Integer>> statsTable = generateStatisticsTable();
		final int numOfTeamsDecPlaces = String.valueOf(statsTable.size()).length();
		final int longestTeamNamePlaces = findTeamWithLongestName(statsTable).getName().length();
		final int spacesBetweenRankAndGrid = longestTeamNamePlaces + SPACE_BETWEEN_LONGEST_NAME_AND_RANKINGNUMBER;
		final int spacesBetweenColumns = String.valueOf(findLargestValueAmountInTable(statsTable)).length() + 1;
		final String columnLabels = stream(StatisticsTableColumn.values())
				.map(column -> String.valueOf(column.translate())).collect(joining(toSpaces(spacesBetweenColumns)));
		final String firstLine = "%s#%s%s%s%s".formatted(toSpaces(numOfTeamsDecPlaces),
				toSpaces(spacesBetweenRankAndGrid - TEAM_DESIGNATION.length()), TEAM_DESIGNATION,
				toSpaces(spacesBetweenColumns), columnLabels);
		final String secondLine = "-".repeat(firstLine.length());
		return "%s%n%s%n%s".formatted(firstLine, secondLine,
				buildTableContent(statsTable, numOfTeamsDecPlaces, spacesBetweenRankAndGrid, spacesBetweenColumns));
	}

	private String buildTableContent(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable, int numOfTeamsDecPlaces,
			int spacesBetweenRankAndGrid, int spacesBetweenColumns) {
		final StringBuilder tableContent = new StringBuilder();
		final List<Team> teamsOrdered = new ArrayList<>(statsTable.keySet());
		final List<Map<StatisticsTableColumn, Integer>> gridOrdered = new ArrayList<>(statsTable.values());
		IntStream.range(0, statsTable.size()).forEach(i -> buildTableRow(numOfTeamsDecPlaces, spacesBetweenRankAndGrid,
				spacesBetweenColumns, tableContent, teamsOrdered, gridOrdered, i));
		return tableContent.toString().replaceAll("\\s+$", "");
	}

	private void buildTableRow(int numOfTeamsDecPlaces, int spacesBetweenRankAndGrid, int spacesBetweenColumns,
			StringBuilder tableContent, List<Team> teamsOrdered, List<Map<StatisticsTableColumn, Integer>> gridOrdered,
			int i) {
		final int counter = i + 1;
		final String spaceBefore = toSpaces(numOfTeamsDecPlaces + 1 - String.valueOf(counter).length());
		final String teamName = teamsOrdered.get(i).getName();
		final String spaceBetweenRankAndTeam = toSpaces(spacesBetweenRankAndGrid - teamName.length());
		final Collection<Integer> gridRowValues = gridOrdered.get(i).values();
		final StringBuilder gridRow = new StringBuilder();
		gridRowValues.stream()
				.map(gridRowValue -> toSpaces(spacesBetweenColumns + 1 - String.valueOf(gridRowValue).length())
						+ gridRowValue)
				.forEach(gridRow::append);
		tableContent.append("%s%s%s%s%s%n".formatted(spaceBefore, counter, spaceBetweenRankAndTeam, teamName, gridRow));
	}

	private String toSpaces(int numberOfSpaces) {
		return " ".repeat(numberOfSpaces);
	}

	private int findLargestValueAmountInTable(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable) {
		return statsTable.values().stream().flatMap(teamStat -> mapToList(teamStat.values(), Math::abs).stream())
				.max(Integer::compareTo).orElse(0);
	}

	private Team findTeamWithLongestName(Map<Team, Map<StatisticsTableColumn, Integer>> statsTable) {
		return statsTable.keySet().stream().max(Comparator.comparingInt(team -> team.getName().length()))
				.orElse(new Team(""));
	}

	public void addGameResults(List<GameResult> newGameResults) {
		gameResults.addAll(newGameResults);
	}

	public Map<Team, Map<StatisticsTableColumn, Integer>> generateStatisticsTable() {
		final Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = new HashMap<>();
		gameResults.forEach(gameResult -> {
			final Team homeTeam = gameResult.getHomeTeam();
			final Team awayTeam = gameResult.getAwayTeam();
			final int homeTeamScore = gameResult.getHomeTeamScore();
			final int awayTeamScore = gameResult.getAwayTeamScore();
			initializeTeamStatistics(statisticsTable, homeTeam);
			initializeTeamStatistics(statisticsTable, awayTeam);
			final Map<StatisticsTableColumn, Integer> homeTeamStatistics = statisticsTable.get(homeTeam);
			final Map<StatisticsTableColumn, Integer> awayTeamStatistics = statisticsTable.get(awayTeam);
			updateScoreStatistics(homeTeamScore, awayTeamScore, homeTeamStatistics);
			updateScoreStatistics(awayTeamScore, homeTeamScore, awayTeamStatistics);
			updateOutcomeStatistics(homeTeamScore, awayTeamScore, homeTeamStatistics, awayTeamStatistics);
		});
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
			final EnumMap<StatisticsTableColumn, Integer> initialTeamStatistics = new EnumMap<>(StatisticsTableColumn.class);
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
