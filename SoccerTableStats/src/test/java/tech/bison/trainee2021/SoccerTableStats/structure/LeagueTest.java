package tech.bison.trainee2021.SoccerTableStats.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class LeagueTest {
	private static final String TEST_BUNDESLIGA = "Hertha BSC 4:3 1. FC Köln\n" + "VfL Wolfsburg 2:1 FC Augsburg\n"
			+ "FC Schalke 04 2:2 TSG Hoffenheim\n" + "Borussia Mönchengladbach 0:2 Sport-Club Freiburg\n"
			+ "RB Leipzig 0:4 1. FSV Mainz 05\n" + "SV Werder Bremen 0:2 VfB Stuttgart\n"
			+ "Borussia Dortmund 3:2 Eintracht Frankfurt\n" + "Bayer 04 Leverkusen 5:1 VfL Bochum 1848\n"
			+ "FC Bayern München 1:1 1. FC Union Berlin\n" + "Borussia Mönchengladbach 1:1 1. FC Union Berlin\n"
			+ "FC Schalke 04 5:0 RB Leipzig\n" + "Eintracht Frankfurt 2:1 SV Werder Bremen\n"
			+ "1. FC Köln 4:0 VfB Stuttgart\n" + "Sport-Club Freiburg 2:1 Borussia Dortmund\n"
			+ "Bayer 04 Leverkusen 0:3 VfL Wolfsburg\n" + "Hertha BSC 0:1 1. FSV Mainz 05\n"
			+ "FC Augsburg 2:2 FC Bayern München\n" + "VfL Bochum 1848 1:1 TSG Hoffenheim\n"
			+ "Bayer 04 Leverkusen 1:5 Hertha BSC\n" + "VfB Stuttgart 1:5 1. FC Köln\n"
			+ "Borussia Dortmund 2:3 RB Leipzig\n" + "TSG Hoffenheim 1:1 VfL Bochum 1848\n"
			+ "Eintracht Frankfurt 0:2 1. FC Union Berlin\n" + "Sport-Club Freiburg 2:1 FC Schalke 04\n"
			+ "VfL Wolfsburg 0:2 SV Werder Bremen\n" + "Borussia Mönchengladbach 5:1 FC Augsburg\n"
			+ "1. FSV Mainz 05 5:5 FC Bayern München\n" + "Borussia Mönchengladbach 1:1 VfL Bochum 1848\n"
			+ "RB Leipzig 1:5 VfL Wolfsburg\n" + "SV Werder Bremen 5:5 Borussia Dortmund\n"
			+ "Bayer 04 Leverkusen 1:2 Sport-Club Freiburg\n" + "1. FC Union Berlin 2:2 FC Bayern München\n"
			+ "1. FSV Mainz 05 1:0 1. FC Köln\n" + "Eintracht Frankfurt 3:0 FC Schalke 04\n"
			+ "VfB Stuttgart 2:1 FC Augsburg\n" + "TSG Hoffenheim 4:0 Hertha BSC\n" + "VfL Wolfsburg 4:1 RB Leipzig\n"
			+ "FC Augsburg 1:1 1. FC Köln\n" + "FC Bayern München 3:0 FC Schalke 04\n"
			+ "Borussia Dortmund 3:0 TSG Hoffenheim\n" + "VfB Stuttgart 3:2 Borussia Mönchengladbach\n"
			+ "Hertha BSC 1:2 Bayer 04 Leverkusen\n" + "VfL Bochum 1848 2:0 1. FSV Mainz 05\n"
			+ "Sport-Club Freiburg 2:2 SV Werder Bremen\n" + "1. FC Union Berlin 0:4 Eintracht Frankfurt\n"
			+ "FC Schalke 04 0:0 VfB Stuttgart\n" + "1. FC Union Berlin 0:0 Hertha BSC\n"
			+ "Eintracht Frankfurt 4:1 FC Bayern München\n" + "Sport-Club Freiburg 4:2 1. FC Köln\n"
			+ "VfL Bochum 1848 1:1 Bayer 04 Leverkusen\n" + "1. FSV Mainz 05 3:1 RB Leipzig\n"
			+ "FC Augsburg 0:0 TSG Hoffenheim\n" + "SV Werder Bremen 0:2 Borussia Mönchengladbach\n"
			+ "VfL Wolfsburg 2:1 Borussia Dortmund\n" + "VfL Bochum 1848 2:3 Borussia Mönchengladbach\n"
			+ "SV Werder Bremen 0:5 1. FC Köln\n" + "VfB Stuttgart 0:2 Borussia Dortmund\n"
			+ "Eintracht Frankfurt 2:1 Sport-Club Freiburg\n" + "1. FC Union Berlin 2:3 Bayer 04 Leverkusen\n"
			+ "FC Schalke 04 4:0 FC Bayern München\n" + "RB Leipzig 2:2 Hertha BSC\n"
			+ "1. FSV Mainz 05 3:2 FC Augsburg\n" + "VfL Wolfsburg 3:3 TSG Hoffenheim\n" + "RB Leipzig 1:1 1. FC Köln\n"
			+ "SV Werder Bremen 0:0 VfL Wolfsburg\n" + "FC Bayern München 4:0 1. FSV Mainz 05\n"
			+ "VfB Stuttgart 3:3 FC Schalke 04\n" + "Hertha BSC 0:1 VfL Bochum 1848\n"
			+ "1. FC Union Berlin 5:1 TSG Hoffenheim\n" + "Sport-Club Freiburg 3:1 Eintracht Frankfurt\n"
			+ "FC Augsburg 0:2 Borussia Mönchengladbach\n" + "Bayer 04 Leverkusen 1:1 Borussia Dortmund\n"
			+ "FC Augsburg 1:1 SV Werder Bremen\n" + "VfB Stuttgart 2:3 1. FC Union Berlin\n"
			+ "Eintracht Frankfurt 5:1 1. FSV Mainz 05\n" + "Borussia Dortmund 1:1 VfL Wolfsburg\n"
			+ "FC Bayern München 4:4 Sport-Club Freiburg\n" + "Bayer 04 Leverkusen 2:1 FC Schalke 04\n"
			+ "1. FC Köln 1:1 RB Leipzig\n" + "Hertha BSC 0:2 TSG Hoffenheim\n"
			+ "1. FC Köln 5:3 Borussia Mönchengladbach\n" + "VfB Stuttgart 1:0 Bayer 04 Leverkusen\n"
			+ "Sport-Club Freiburg 0:5 FC Bayern München\n" + "1. FSV Mainz 05 1:2 Borussia Dortmund\n"
			+ "RB Leipzig 2:2 Eintracht Frankfurt\n" + "SV Werder Bremen 0:4 TSG Hoffenheim\n"
			+ "VfL Wolfsburg 2:0 FC Schalke 04\n" + "VfL Bochum 1848 5:0 Hertha BSC\n"
			+ "1. FC Union Berlin 4:2 FC Augsburg\n" + "TSG Hoffenheim 5:5 1. FC Union Berlin\n"
			+ "1. FC Köln 0:1 Sport-Club Freiburg\n" + "Eintracht Frankfurt 2:1 Borussia Dortmund\n"
			+ "SV Werder Bremen 1:1 Hertha BSC\n" + "Borussia Mönchengladbach 5:2 Bayer 04 Leverkusen\n"
			+ "FC Bayern München 0:0 FC Augsburg\n" + "1. FSV Mainz 05 0:1 FC Schalke 04\n"
			+ "RB Leipzig 2:2 VfL Bochum 1848\n" + "VfL Wolfsburg 0:0 VfB Stuttgart\n"
			+ "Borussia Dortmund 0:4 Bayer 04 Leverkusen\n" + "VfB Stuttgart 2:3 Eintracht Frankfurt\n"
			+ "RB Leipzig 1:4 1. FC Union Berlin\n" + "TSG Hoffenheim 4:4 VfL Wolfsburg\n"
			+ "SV Werder Bremen 1:1 FC Schalke 04\n" + "FC Augsburg 4:5 Sport-Club Freiburg\n"
			+ "FC Bayern München 0:1 Hertha BSC\n" + "Borussia Mönchengladbach 2:0 1. FSV Mainz 05\n"
			+ "VfL Bochum 1848 3:4 1. FC Köln\n" + "VfL Bochum 1848 2:2 RB Leipzig\n"
			+ "VfB Stuttgart 1:0 SV Werder Bremen\n" + "TSG Hoffenheim 3:1 Borussia Mönchengladbach\n"
			+ "FC Schalke 04 1:0 1. FSV Mainz 05\n" + "VfL Wolfsburg 4:3 FC Bayern München\n"
			+ "Sport-Club Freiburg 2:2 Bayer 04 Leverkusen\n" + "Eintracht Frankfurt 5:1 1. FC Köln\n"
			+ "FC Augsburg 0:0 1. FC Union Berlin\n" + "Borussia Dortmund 1:3 Hertha BSC\n"
			+ "FC Schalke 04 1:2 Hertha BSC\n" + "Sport-Club Freiburg 0:5 RB Leipzig\n"
			+ "1. FC Köln 2:0 FC Bayern München\n" + "Bayer 04 Leverkusen 2:5 VfB Stuttgart\n"
			+ "SV Werder Bremen 2:0 VfL Bochum 1848\n" + "Eintracht Frankfurt 0:0 FC Augsburg\n"
			+ "Borussia Dortmund 0:1 1. FC Union Berlin\n" + "Borussia Mönchengladbach 1:1 TSG Hoffenheim\n"
			+ "1. FSV Mainz 05 0:1 VfL Wolfsburg\n" + "Hertha BSC 1:5 1. FC Union Berlin\n"
			+ "FC Augsburg 4:1 RB Leipzig\n" + "FC Bayern München 2:1 Bayer 04 Leverkusen\n"
			+ "Sport-Club Freiburg 1:0 Borussia Mönchengladbach\n" + "1. FSV Mainz 05 5:0 VfL Bochum 1848\n"
			+ "VfB Stuttgart 2:1 VfL Wolfsburg\n" + "FC Schalke 04 1:1 Borussia Dortmund\n"
			+ "1. FC Köln 3:2 Eintracht Frankfurt\n" + "TSG Hoffenheim 0:3 SV Werder Bremen\n"
			+ "FC Schalke 04 0:2 Bayer 04 Leverkusen\n" + "FC Bayern München 1:0 Eintracht Frankfurt\n"
			+ "FC Augsburg 1:2 Borussia Dortmund\n" + "1. FC Union Berlin 0:4 Sport-Club Freiburg\n"
			+ "TSG Hoffenheim 0:4 1. FC Köln\n" + "VfL Bochum 1848 2:1 VfL Wolfsburg\n"
			+ "RB Leipzig 5:1 VfB Stuttgart\n" + "Borussia Mönchengladbach 0:0 Hertha BSC\n"
			+ "SV Werder Bremen 1:0 1. FSV Mainz 05\n" + "Sport-Club Freiburg 3:4 VfB Stuttgart\n"
			+ "RB Leipzig 0:0 Bayer 04 Leverkusen\n" + "VfL Bochum 1848 1:1 Eintracht Frankfurt\n"
			+ "Hertha BSC 1:2 VfL Wolfsburg\n" + "1. FC Union Berlin 0:0 Borussia Dortmund\n"
			+ "TSG Hoffenheim 1:1 FC Augsburg\n" + "Borussia Mönchengladbach 1:2 FC Schalke 04\n"
			+ "1. FC Köln 0:1 SV Werder Bremen\n" + "1. FC Köln 2:0 Hertha BSC\n" + "FC Augsburg 2:1 VfL Wolfsburg\n"
			+ "TSG Hoffenheim 1:1 FC Schalke 04\n" + "Sport-Club Freiburg 1:0 Borussia Mönchengladbach\n"
			+ "1. FSV Mainz 05 1:1 RB Leipzig\n" + "VfB Stuttgart 2:0 SV Werder Bremen\n"
			+ "Eintracht Frankfurt 2:0 Borussia Dortmund\n" + "VfL Bochum 1848 1:5 Bayer 04 Leverkusen\n"
			+ "1. FC Union Berlin 2:5 FC Bayern München\n" + "1. FC Union Berlin 0:2 Borussia Mönchengladbach\n"
			+ "RB Leipzig 1:3 FC Schalke 04\n" + "SV Werder Bremen 4:1 Eintracht Frankfurt\n"
			+ "VfB Stuttgart 4:0 1. FC Köln\n" + "Borussia Dortmund 3:5 Sport-Club Freiburg\n"
			+ "VfL Wolfsburg 0:2 Bayer 04 Leverkusen\n" + "1. FSV Mainz 05 1:1 Hertha BSC\n"
			+ "FC Bayern München 2:2 FC Augsburg\n" + "TSG Hoffenheim 2:1 VfL Bochum 1848\n"
			+ "Hertha BSC 1:2 Bayer 04 Leverkusen\n" + "1. FC Köln 1:0 VfB Stuttgart\n"
			+ "RB Leipzig 4:2 Borussia Dortmund\n" + "VfL Bochum 1848 2:4 TSG Hoffenheim\n"
			+ "1. FC Union Berlin 2:3 Eintracht Frankfurt\n" + "FC Schalke 04 2:2 Sport-Club Freiburg\n"
			+ "SV Werder Bremen 0:5 VfL Wolfsburg\n" + "FC Augsburg 2:1 Borussia Mönchengladbach\n"
			+ "FC Bayern München 1:4 1. FSV Mainz 05\n" + "VfL Bochum 1848 3:3 Borussia Mönchengladbach\n"
			+ "VfL Wolfsburg 0:5 RB Leipzig\n" + "Borussia Dortmund 2:4 SV Werder Bremen\n"
			+ "Sport-Club Freiburg 1:0 Bayer 04 Leverkusen\n" + "FC Bayern München 0:1 1. FC Union Berlin\n"
			+ "1. FC Köln 4:3 1. FSV Mainz 05\n" + "FC Schalke 04 0:2 Eintracht Frankfurt\n"
			+ "FC Augsburg 1:0 VfB Stuttgart\n" + "Hertha BSC 2:0 TSG Hoffenheim\n" + "RB Leipzig 1:5 VfL Wolfsburg\n"
			+ "1. FC Köln 2:4 FC Augsburg\n" + "FC Schalke 04 1:0 FC Bayern München\n"
			+ "TSG Hoffenheim 1:0 Borussia Dortmund\n" + "Borussia Mönchengladbach 1:1 VfB Stuttgart\n"
			+ "Bayer 04 Leverkusen 2:0 Hertha BSC\n" + "1. FSV Mainz 05 0:2 VfL Bochum 1848\n"
			+ "SV Werder Bremen 4:5 Sport-Club Freiburg\n" + "Eintracht Frankfurt 0:2 1. FC Union Berlin\n"
			+ "VfB Stuttgart 1:1 FC Schalke 04\n" + "Hertha BSC 2:2 1. FC Union Berlin\n"
			+ "FC Bayern München 1:1 Eintracht Frankfurt\n" + "1. FC Köln 2:1 Sport-Club Freiburg\n"
			+ "Bayer 04 Leverkusen 2:0 VfL Bochum 1848\n" + "RB Leipzig 1:1 1. FSV Mainz 05\n"
			+ "TSG Hoffenheim 3:0 FC Augsburg\n" + "Borussia Mönchengladbach 4:1 SV Werder Bremen\n"
			+ "Borussia Dortmund 4:5 VfL Wolfsburg\n" + "Borussia Mönchengladbach 2:0 VfL Bochum 1848\n"
			+ "1. FC Köln 3:3 SV Werder Bremen\n" + "Borussia Dortmund 0:2 VfB Stuttgart\n"
			+ "Sport-Club Freiburg 2:0 Eintracht Frankfurt\n" + "Bayer 04 Leverkusen 5:2 1. FC Union Berlin\n"
			+ "FC Bayern München 5:2 FC Schalke 04\n" + "Hertha BSC 3:0 RB Leipzig\n"
			+ "FC Augsburg 0:2 1. FSV Mainz 05\n" + "TSG Hoffenheim 5:2 VfL Wolfsburg\n" + "1. FC Köln 3:1 RB Leipzig\n"
			+ "VfL Wolfsburg 0:1 SV Werder Bremen\n" + "1. FSV Mainz 05 5:1 FC Bayern München\n"
			+ "FC Schalke 04 1:0 VfB Stuttgart\n" + "VfL Bochum 1848 3:5 Hertha BSC\n"
			+ "TSG Hoffenheim 0:1 1. FC Union Berlin\n" + "Eintracht Frankfurt 2:4 Sport-Club Freiburg\n"
			+ "Borussia Mönchengladbach 2:0 FC Augsburg\n" + "Borussia Dortmund 1:2 Bayer 04 Leverkusen\n"
			+ "SV Werder Bremen 0:4 FC Augsburg\n" + "1. FC Union Berlin 1:4 VfB Stuttgart\n"
			+ "1. FSV Mainz 05 5:5 Eintracht Frankfurt\n" + "VfL Wolfsburg 0:2 Borussia Dortmund\n"
			+ "Sport-Club Freiburg 3:5 FC Bayern München\n" + "FC Schalke 04 4:1 Bayer 04 Leverkusen\n"
			+ "RB Leipzig 0:1 1. FC Köln\n" + "TSG Hoffenheim 3:5 Hertha BSC\n"
			+ "Borussia Mönchengladbach 1:1 1. FC Köln\n" + "Bayer 04 Leverkusen 0:5 VfB Stuttgart\n"
			+ "FC Bayern München 0:2 Sport-Club Freiburg\n" + "Borussia Dortmund 1:1 1. FSV Mainz 05\n"
			+ "Eintracht Frankfurt 1:1 RB Leipzig\n" + "TSG Hoffenheim 1:1 SV Werder Bremen\n"
			+ "FC Schalke 04 2:2 VfL Wolfsburg\n" + "Hertha BSC 2:5 VfL Bochum 1848\n"
			+ "FC Augsburg 0:1 1. FC Union Berlin\n" + "1. FC Union Berlin 1:2 TSG Hoffenheim\n"
			+ "Sport-Club Freiburg 5:3 1. FC Köln\n" + "Borussia Dortmund 4:0 Eintracht Frankfurt\n"
			+ "Hertha BSC 4:0 SV Werder Bremen\n" + "Bayer 04 Leverkusen 2:2 Borussia Mönchengladbach\n"
			+ "FC Augsburg 2:0 FC Bayern München\n" + "FC Schalke 04 2:1 1. FSV Mainz 05\n"
			+ "VfL Bochum 1848 4:4 RB Leipzig\n" + "VfB Stuttgart 0:1 VfL Wolfsburg\n"
			+ "Bayer 04 Leverkusen 1:1 Borussia Dortmund\n" + "Eintracht Frankfurt 1:1 VfB Stuttgart\n"
			+ "1. FC Union Berlin 2:1 RB Leipzig\n" + "VfL Wolfsburg 3:1 TSG Hoffenheim\n"
			+ "FC Schalke 04 1:3 SV Werder Bremen\n" + "Sport-Club Freiburg 0:3 FC Augsburg\n"
			+ "Hertha BSC 5:4 FC Bayern München\n" + "1. FSV Mainz 05 2:3 Borussia Mönchengladbach\n"
			+ "1. FC Köln 1:0 VfL Bochum 1848\n" + "RB Leipzig 4:5 VfL Bochum 1848\n"
			+ "SV Werder Bremen 0:4 VfB Stuttgart\n" + "Borussia Mönchengladbach 4:1 TSG Hoffenheim\n"
			+ "1. FSV Mainz 05 2:0 FC Schalke 04\n" + "FC Bayern München 0:5 VfL Wolfsburg\n"
			+ "Bayer 04 Leverkusen 2:2 Sport-Club Freiburg\n" + "1. FC Köln 2:2 Eintracht Frankfurt\n"
			+ "1. FC Union Berlin 1:5 FC Augsburg\n" + "Hertha BSC 3:3 Borussia Dortmund\n"
			+ "Hertha BSC 4:1 FC Schalke 04\n" + "RB Leipzig 3:0 Sport-Club Freiburg\n"
			+ "FC Bayern München 2:1 1. FC Köln\n" + "VfB Stuttgart 0:1 Bayer 04 Leverkusen\n"
			+ "VfL Bochum 1848 5:2 SV Werder Bremen\n" + "FC Augsburg 0:0 Eintracht Frankfurt\n"
			+ "1. FC Union Berlin 1:1 Borussia Dortmund\n" + "TSG Hoffenheim 3:2 Borussia Mönchengladbach\n"
			+ "VfL Wolfsburg 1:4 1. FSV Mainz 05\n" + "1. FC Union Berlin 0:4 Hertha BSC\n"
			+ "RB Leipzig 1:1 FC Augsburg\n" + "Bayer 04 Leverkusen 1:1 FC Bayern München\n"
			+ "Borussia Mönchengladbach 5:0 Sport-Club Freiburg\n" + "VfL Bochum 1848 5:2 1. FSV Mainz 05\n"
			+ "VfL Wolfsburg 0:1 VfB Stuttgart\n" + "Borussia Dortmund 1:5 FC Schalke 04\n"
			+ "Eintracht Frankfurt 1:2 1. FC Köln\n" + "SV Werder Bremen 2:1 TSG Hoffenheim\n"
			+ "Bayer 04 Leverkusen 5:0 FC Schalke 04\n" + "Eintracht Frankfurt 2:1 FC Bayern München\n"
			+ "Borussia Dortmund 1:0 FC Augsburg\n" + "Sport-Club Freiburg 3:1 1. FC Union Berlin\n"
			+ "1. FC Köln 0:3 TSG Hoffenheim\n" + "VfL Wolfsburg 0:1 VfL Bochum 1848\n"
			+ "VfB Stuttgart 0:3 RB Leipzig\n" + "Hertha BSC 5:2 Borussia Mönchengladbach\n"
			+ "1. FSV Mainz 05 1:1 SV Werder Bremen\n" + "VfB Stuttgart 0:2 Sport-Club Freiburg\n"
			+ "Bayer 04 Leverkusen 2:5 RB Leipzig\n" + "Eintracht Frankfurt 1:2 VfL Bochum 1848\n"
			+ "VfL Wolfsburg 4:4 Hertha BSC\n" + "Borussia Dortmund 4:0 1. FC Union Berlin\n"
			+ "FC Augsburg 3:1 TSG Hoffenheim\n" + "FC Schalke 04 1:2 Borussia Mönchengladbach\n"
			+ "SV Werder Bremen 2:1 1. FC Köln";

	@Test
	void finishedBundesliga_getResultsTable_isCorrectForFirstThreeTeams() {
		League league = new League();
		league.addGameResults(GameResult.parseGameResults(TEST_BUNDESLIGA));

		Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = league.getStatisticsTable();

		assertThat(statisticsTable.get(new Team("Hertha BSC")).values()).containsExactly(14, 8, 12, 71, 65, 6, 50);
		assertThat(statisticsTable.get(new Team("1. FC Köln")).values()).containsExactly(16, 6, 12, 70, 60, 10, 54);
		assertThat(statisticsTable.get(new Team("VfL Wolfsburg")).values()).containsExactly(15, 7, 12, 69, 57, 12, 52);
	}

	@Test
	void finishedBundesliga_getResultsTable_teamsAreCorrectlySorted() {
		League league = new League();
		league.addGameResults(GameResult.parseGameResults(TEST_BUNDESLIGA));

		Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = league.getStatisticsTable();

		assertThat(new ArrayList<>(statisticsTable.keySet())).containsExactly(new Team("Sport-Club Freiburg"),
				new Team("1. FC Köln"), new Team("Bayer 04 Leverkusen"), new Team("VfL Wolfsburg"),
				new Team("VfB Stuttgart"), new Team("Borussia Mönchengladbach"), new Team("Hertha BSC"),
				new Team("Eintracht Frankfurt"), new Team("TSG Hoffenheim"), new Team("1. FC Union Berlin"),
				new Team("VfL Bochum 1848"), new Team("FC Schalke 04"), new Team("SV Werder Bremen"),
				new Team("FC Augsburg"), new Team("1. FSV Mainz 05"), new Team("FC Bayern München"),
				new Team("Borussia Dortmund"), new Team("RB Leipzig"));
	}
}
