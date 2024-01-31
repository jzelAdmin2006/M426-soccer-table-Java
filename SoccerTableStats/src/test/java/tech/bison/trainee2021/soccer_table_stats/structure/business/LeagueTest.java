package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.bison.trainee2021.soccer_table_stats.structure.business.GameResult.parseGameResults;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import tech.bison.trainee2021.soccer_table_stats.InputFormat;

class LeagueTest {
	private static final String TEST_BUNDESLIGA = """
			Hertha BSC 4:3 1. FC Küln
			VfL Wolfsburg 2:1 FC Augsburg
			FC Schalke 04 2:2 TSG Hoffenheim
			Borussia Münchengladbach 0:2 Sport-Club Freiburg
			RB Leipzig 0:4 1. FSV Mainz 05
			SV Werder Bremen 0:2 VfB Stuttgart
			Borussia Dortmund 3:2 Eintracht Frankfurt
			Bayer 04 Leverkusen 5:1 VfL Bochum 1848
			FC Bayern München 1:1 1. FC Union Berlin
			Borussia Münchengladbach 1:1 1. FC Union Berlin
			FC Schalke 04 5:0 RB Leipzig
			Eintracht Frankfurt 2:1 SV Werder Bremen
			1. FC Küln 4:0 VfB Stuttgart
			Sport-Club Freiburg 2:1 Borussia Dortmund
			Bayer 04 Leverkusen 0:3 VfL Wolfsburg
			Hertha BSC 0:1 1. FSV Mainz 05
			FC Augsburg 2:2 FC Bayern München
			VfL Bochum 1848 1:1 TSG Hoffenheim
			Bayer 04 Leverkusen 1:5 Hertha BSC
			VfB Stuttgart 1:5 1. FC Küln
			Borussia Dortmund 2:3 RB Leipzig
			TSG Hoffenheim 1:1 VfL Bochum 1848
			Eintracht Frankfurt 0:2 1. FC Union Berlin
			Sport-Club Freiburg 2:1 FC Schalke 04
			VfL Wolfsburg 0:2 SV Werder Bremen
			Borussia Münchengladbach 5:1 FC Augsburg
			1. FSV Mainz 05 5:5 FC Bayern München
			Borussia Münchengladbach 1:1 VfL Bochum 1848
			RB Leipzig 1:5 VfL Wolfsburg
			SV Werder Bremen 5:5 Borussia Dortmund
			Bayer 04 Leverkusen 1:2 Sport-Club Freiburg
			1. FC Union Berlin 2:2 FC Bayern München
			1. FSV Mainz 05 1:0 1. FC Küln
			Eintracht Frankfurt 3:0 FC Schalke 04
			VfB Stuttgart 2:1 FC Augsburg
			TSG Hoffenheim 4:0 Hertha BSC
			VfL Wolfsburg 4:1 RB Leipzig
			FC Augsburg 1:1 1. FC Küln
			FC Bayern München 3:0 FC Schalke 04
			Borussia Dortmund 3:0 TSG Hoffenheim
			VfB Stuttgart 3:2 Borussia Münchengladbach
			Hertha BSC 1:2 Bayer 04 Leverkusen
			VfL Bochum 1848 2:0 1. FSV Mainz 05
			Sport-Club Freiburg 2:2 SV Werder Bremen
			1. FC Union Berlin 0:4 Eintracht Frankfurt
			FC Schalke 04 0:0 VfB Stuttgart
			1. FC Union Berlin 0:0 Hertha BSC
			Eintracht Frankfurt 4:1 FC Bayern München
			Sport-Club Freiburg 4:2 1. FC Küln
			VfL Bochum 1848 1:1 Bayer 04 Leverkusen
			1. FSV Mainz 05 3:1 RB Leipzig
			FC Augsburg 0:0 TSG Hoffenheim
			SV Werder Bremen 0:2 Borussia Münchengladbach
			VfL Wolfsburg 2:1 Borussia Dortmund
			VfL Bochum 1848 2:3 Borussia Münchengladbach
			SV Werder Bremen 0:5 1. FC Küln
			VfB Stuttgart 0:2 Borussia Dortmund
			Eintracht Frankfurt 2:1 Sport-Club Freiburg
			1. FC Union Berlin 2:3 Bayer 04 Leverkusen
			FC Schalke 04 4:0 FC Bayern München
			RB Leipzig 2:2 Hertha BSC
			1. FSV Mainz 05 3:2 FC Augsburg
			VfL Wolfsburg 3:3 TSG Hoffenheim
			RB Leipzig 1:1 1. FC Küln
			SV Werder Bremen 0:0 VfL Wolfsburg
			FC Bayern München 4:0 1. FSV Mainz 05
			VfB Stuttgart 3:3 FC Schalke 04
			Hertha BSC 0:1 VfL Bochum 1848
			1. FC Union Berlin 5:1 TSG Hoffenheim
			Sport-Club Freiburg 3:1 Eintracht Frankfurt
			FC Augsburg 0:2 Borussia Münchengladbach
			Bayer 04 Leverkusen 1:1 Borussia Dortmund
			FC Augsburg 1:1 SV Werder Bremen
			VfB Stuttgart 2:3 1. FC Union Berlin
			Eintracht Frankfurt 5:1 1. FSV Mainz 05
			Borussia Dortmund 1:1 VfL Wolfsburg
			FC Bayern München 4:4 Sport-Club Freiburg
			Bayer 04 Leverkusen 2:1 FC Schalke 04
			1. FC Küln 1:1 RB Leipzig
			Hertha BSC 0:2 TSG Hoffenheim
			1. FC Küln 5:3 Borussia Münchengladbach
			VfB Stuttgart 1:0 Bayer 04 Leverkusen
			Sport-Club Freiburg 0:5 FC Bayern München
			1. FSV Mainz 05 1:2 Borussia Dortmund
			RB Leipzig 2:2 Eintracht Frankfurt
			SV Werder Bremen 0:4 TSG Hoffenheim
			VfL Wolfsburg 2:0 FC Schalke 04
			VfL Bochum 1848 5:0 Hertha BSC
			1. FC Union Berlin 4:2 FC Augsburg
			TSG Hoffenheim 5:5 1. FC Union Berlin
			1. FC Küln 0:1 Sport-Club Freiburg
			Eintracht Frankfurt 2:1 Borussia Dortmund
			SV Werder Bremen 1:1 Hertha BSC
			Borussia Münchengladbach 5:2 Bayer 04 Leverkusen
			FC Bayern München 0:0 FC Augsburg
			1. FSV Mainz 05 0:1 FC Schalke 04
			RB Leipzig 2:2 VfL Bochum 1848
			VfL Wolfsburg 0:0 VfB Stuttgart
			Borussia Dortmund 0:4 Bayer 04 Leverkusen
			VfB Stuttgart 2:3 Eintracht Frankfurt
			RB Leipzig 1:4 1. FC Union Berlin
			TSG Hoffenheim 4:4 VfL Wolfsburg
			SV Werder Bremen 1:1 FC Schalke 04
			FC Augsburg 4:5 Sport-Club Freiburg
			FC Bayern München 0:1 Hertha BSC
			Borussia Münchengladbach 2:0 1. FSV Mainz 05
			VfL Bochum 1848 3:4 1. FC Küln
			VfL Bochum 1848 2:2 RB Leipzig
			VfB Stuttgart 1:0 SV Werder Bremen
			TSG Hoffenheim 3:1 Borussia Münchengladbach
			FC Schalke 04 1:0 1. FSV Mainz 05
			VfL Wolfsburg 4:3 FC Bayern München
			Sport-Club Freiburg 2:2 Bayer 04 Leverkusen
			Eintracht Frankfurt 5:1 1. FC Küln
			FC Augsburg 0:0 1. FC Union Berlin
			Borussia Dortmund 1:3 Hertha BSC
			FC Schalke 04 1:2 Hertha BSC
			Sport-Club Freiburg 0:5 RB Leipzig
			1. FC Küln 2:0 FC Bayern München
			Bayer 04 Leverkusen 2:5 VfB Stuttgart
			SV Werder Bremen 2:0 VfL Bochum 1848
			Eintracht Frankfurt 0:0 FC Augsburg
			Borussia Dortmund 0:1 1. FC Union Berlin
			Borussia Münchengladbach 1:1 TSG Hoffenheim
			1. FSV Mainz 05 0:1 VfL Wolfsburg
			Hertha BSC 1:5 1. FC Union Berlin
			FC Augsburg 4:1 RB Leipzig
			FC Bayern München 2:1 Bayer 04 Leverkusen
			Sport-Club Freiburg 1:0 Borussia Münchengladbach
			1. FSV Mainz 05 5:0 VfL Bochum 1848
			VfB Stuttgart 2:1 VfL Wolfsburg
			FC Schalke 04 1:1 Borussia Dortmund
			1. FC Küln 3:2 Eintracht Frankfurt
			TSG Hoffenheim 0:3 SV Werder Bremen
			FC Schalke 04 0:2 Bayer 04 Leverkusen
			FC Bayern München 1:0 Eintracht Frankfurt
			FC Augsburg 1:2 Borussia Dortmund
			1. FC Union Berlin 0:4 Sport-Club Freiburg
			TSG Hoffenheim 0:4 1. FC Küln
			VfL Bochum 1848 2:1 VfL Wolfsburg
			RB Leipzig 5:1 VfB Stuttgart
			Borussia Münchengladbach 0:0 Hertha BSC
			SV Werder Bremen 1:0 1. FSV Mainz 05
			Sport-Club Freiburg 3:4 VfB Stuttgart
			RB Leipzig 0:0 Bayer 04 Leverkusen
			VfL Bochum 1848 1:1 Eintracht Frankfurt
			Hertha BSC 1:2 VfL Wolfsburg
			1. FC Union Berlin 0:0 Borussia Dortmund
			TSG Hoffenheim 1:1 FC Augsburg
			Borussia Münchengladbach 1:2 FC Schalke 04
			1. FC Küln 0:1 SV Werder Bremen
			1. FC Küln 2:0 Hertha BSC
			FC Augsburg 2:1 VfL Wolfsburg
			TSG Hoffenheim 1:1 FC Schalke 04
			Sport-Club Freiburg 1:0 Borussia Münchengladbach
			1. FSV Mainz 05 1:1 RB Leipzig
			VfB Stuttgart 2:0 SV Werder Bremen
			Eintracht Frankfurt 2:0 Borussia Dortmund
			VfL Bochum 1848 1:5 Bayer 04 Leverkusen
			1. FC Union Berlin 2:5 FC Bayern München
			1. FC Union Berlin 0:2 Borussia Münchengladbach
			RB Leipzig 1:3 FC Schalke 04
			SV Werder Bremen 4:1 Eintracht Frankfurt
			VfB Stuttgart 4:0 1. FC Küln
			Borussia Dortmund 3:5 Sport-Club Freiburg
			VfL Wolfsburg 0:2 Bayer 04 Leverkusen
			1. FSV Mainz 05 1:1 Hertha BSC
			FC Bayern München 2:2 FC Augsburg
			TSG Hoffenheim 2:1 VfL Bochum 1848
			Hertha BSC 1:2 Bayer 04 Leverkusen
			1. FC Küln 1:0 VfB Stuttgart
			RB Leipzig 4:2 Borussia Dortmund
			VfL Bochum 1848 2:4 TSG Hoffenheim
			1. FC Union Berlin 2:3 Eintracht Frankfurt
			FC Schalke 04 2:2 Sport-Club Freiburg
			SV Werder Bremen 0:5 VfL Wolfsburg
			FC Augsburg 2:1 Borussia Münchengladbach
			FC Bayern München 1:4 1. FSV Mainz 05
			VfL Bochum 1848 3:3 Borussia Münchengladbach
			VfL Wolfsburg 0:5 RB Leipzig
			Borussia Dortmund 2:4 SV Werder Bremen
			Sport-Club Freiburg 1:0 Bayer 04 Leverkusen
			FC Bayern München 0:1 1. FC Union Berlin
			1. FC Küln 4:3 1. FSV Mainz 05
			FC Schalke 04 0:2 Eintracht Frankfurt
			FC Augsburg 1:0 VfB Stuttgart
			Hertha BSC 2:0 TSG Hoffenheim
			RB Leipzig 1:5 VfL Wolfsburg
			1. FC Küln 2:4 FC Augsburg
			FC Schalke 04 1:0 FC Bayern München
			TSG Hoffenheim 1:0 Borussia Dortmund
			Borussia Münchengladbach 1:1 VfB Stuttgart
			Bayer 04 Leverkusen 2:0 Hertha BSC
			1. FSV Mainz 05 0:2 VfL Bochum 1848
			SV Werder Bremen 4:5 Sport-Club Freiburg
			Eintracht Frankfurt 0:2 1. FC Union Berlin
			VfB Stuttgart 1:1 FC Schalke 04
			Hertha BSC 2:2 1. FC Union Berlin
			FC Bayern München 1:1 Eintracht Frankfurt
			1. FC Küln 2:1 Sport-Club Freiburg
			Bayer 04 Leverkusen 2:0 VfL Bochum 1848
			RB Leipzig 1:1 1. FSV Mainz 05
			TSG Hoffenheim 3:0 FC Augsburg
			Borussia Münchengladbach 4:1 SV Werder Bremen
			Borussia Dortmund 4:5 VfL Wolfsburg
			Borussia Münchengladbach 2:0 VfL Bochum 1848
			1. FC Küln 3:3 SV Werder Bremen
			Borussia Dortmund 0:2 VfB Stuttgart
			Sport-Club Freiburg 2:0 Eintracht Frankfurt
			Bayer 04 Leverkusen 5:2 1. FC Union Berlin
			FC Bayern München 5:2 FC Schalke 04
			Hertha BSC 3:0 RB Leipzig
			FC Augsburg 0:2 1. FSV Mainz 05
			TSG Hoffenheim 5:2 VfL Wolfsburg
			1. FC Küln 3:1 RB Leipzig
			VfL Wolfsburg 0:1 SV Werder Bremen
			1. FSV Mainz 05 5:1 FC Bayern München
			FC Schalke 04 1:0 VfB Stuttgart
			VfL Bochum 1848 3:5 Hertha BSC
			TSG Hoffenheim 0:1 1. FC Union Berlin
			Eintracht Frankfurt 2:4 Sport-Club Freiburg
			Borussia Münchengladbach 2:0 FC Augsburg
			Borussia Dortmund 1:2 Bayer 04 Leverkusen
			SV Werder Bremen 0:4 FC Augsburg
			1. FC Union Berlin 1:4 VfB Stuttgart
			1. FSV Mainz 05 5:5 Eintracht Frankfurt
			VfL Wolfsburg 0:2 Borussia Dortmund
			Sport-Club Freiburg 3:5 FC Bayern München
			FC Schalke 04 4:1 Bayer 04 Leverkusen
			RB Leipzig 0:1 1. FC Küln
			TSG Hoffenheim 3:5 Hertha BSC
			Borussia Münchengladbach 1:1 1. FC Küln
			Bayer 04 Leverkusen 0:5 VfB Stuttgart
			FC Bayern München 0:2 Sport-Club Freiburg
			Borussia Dortmund 1:1 1. FSV Mainz 05
			Eintracht Frankfurt 1:1 RB Leipzig
			TSG Hoffenheim 1:1 SV Werder Bremen
			FC Schalke 04 2:2 VfL Wolfsburg
			Hertha BSC 2:5 VfL Bochum 1848
			FC Augsburg 0:1 1. FC Union Berlin
			1. FC Union Berlin 1:2 TSG Hoffenheim
			Sport-Club Freiburg 5:3 1. FC Küln
			Borussia Dortmund 4:0 Eintracht Frankfurt
			Hertha BSC 4:0 SV Werder Bremen
			Bayer 04 Leverkusen 2:2 Borussia Münchengladbach
			FC Augsburg 2:0 FC Bayern München
			FC Schalke 04 2:1 1. FSV Mainz 05
			VfL Bochum 1848 4:4 RB Leipzig
			VfB Stuttgart 0:1 VfL Wolfsburg
			Bayer 04 Leverkusen 1:1 Borussia Dortmund
			Eintracht Frankfurt 1:1 VfB Stuttgart
			1. FC Union Berlin 2:1 RB Leipzig
			VfL Wolfsburg 3:1 TSG Hoffenheim
			FC Schalke 04 1:3 SV Werder Bremen
			Sport-Club Freiburg 0:3 FC Augsburg
			Hertha BSC 5:4 FC Bayern München
			1. FSV Mainz 05 2:3 Borussia Münchengladbach
			1. FC Küln 1:0 VfL Bochum 1848
			RB Leipzig 4:5 VfL Bochum 1848
			SV Werder Bremen 0:4 VfB Stuttgart
			Borussia Münchengladbach 4:1 TSG Hoffenheim
			1. FSV Mainz 05 2:0 FC Schalke 04
			FC Bayern München 0:5 VfL Wolfsburg
			Bayer 04 Leverkusen 2:2 Sport-Club Freiburg
			1. FC Küln 2:2 Eintracht Frankfurt
			1. FC Union Berlin 1:5 FC Augsburg
			Hertha BSC 3:3 Borussia Dortmund
			Hertha BSC 4:1 FC Schalke 04
			RB Leipzig 3:0 Sport-Club Freiburg
			FC Bayern München 2:1 1. FC Küln
			VfB Stuttgart 0:1 Bayer 04 Leverkusen
			VfL Bochum 1848 5:2 SV Werder Bremen
			FC Augsburg 0:0 Eintracht Frankfurt
			1. FC Union Berlin 1:1 Borussia Dortmund
			TSG Hoffenheim 3:2 Borussia Münchengladbach
			VfL Wolfsburg 1:4 1. FSV Mainz 05
			1. FC Union Berlin 0:4 Hertha BSC
			RB Leipzig 1:1 FC Augsburg
			Bayer 04 Leverkusen 1:1 FC Bayern München
			Borussia Münchengladbach 5:0 Sport-Club Freiburg
			VfL Bochum 1848 5:2 1. FSV Mainz 05
			VfL Wolfsburg 0:1 VfB Stuttgart
			Borussia Dortmund 1:5 FC Schalke 04
			Eintracht Frankfurt 1:2 1. FC Küln
			SV Werder Bremen 2:1 TSG Hoffenheim
			Bayer 04 Leverkusen 5:0 FC Schalke 04
			Eintracht Frankfurt 2:1 FC Bayern München
			Borussia Dortmund 1:0 FC Augsburg
			Sport-Club Freiburg 3:1 1. FC Union Berlin
			1. FC Küln 0:3 TSG Hoffenheim
			VfL Wolfsburg 0:1 VfL Bochum 1848
			VfB Stuttgart 0:3 RB Leipzig
			Hertha BSC 5:2 Borussia Münchengladbach
			1. FSV Mainz 05 1:1 SV Werder Bremen
			VfB Stuttgart 0:2 Sport-Club Freiburg
			Bayer 04 Leverkusen 2:5 RB Leipzig
			Eintracht Frankfurt 1:2 VfL Bochum 1848
			VfL Wolfsburg 4:4 Hertha BSC
			Borussia Dortmund 4:0 1. FC Union Berlin
			FC Augsburg 3:1 TSG Hoffenheim
			FC Schalke 04 1:2 Borussia Münchengladbach
			SV Werder Bremen 2:1 1. FC Küln\
			""";

	private static final String TEST_BUNDESLIGA_EQUAL = """
			Hertha BSC 0:0 1. FC Küln
			VfL Wolfsburg 0:0 FC Augsburg
			FC Schalke 04 0:0 TSG Hoffenheim
			Borussia Münchengladbach 0:0 Sport-Club Freiburg
			RB Leipzig 0:0 1. FSV Mainz 05
			SV Werder Bremen 0:0 VfB Stuttgart
			Borussia Dortmund 0:0 Eintracht Frankfurt
			Bayer 04 Leverkusen 0:0 VfL Bochum 1848
			FC Bayern München 0:0 1. FC Union Berlin
			Borussia Münchengladbach 0:0 1. FC Union Berlin
			FC Schalke 04 0:0 RB Leipzig
			Eintracht Frankfurt 0:0 SV Werder Bremen
			1. FC Küln 0:0 VfB Stuttgart
			Sport-Club Freiburg 0:0 Borussia Dortmund
			Bayer 04 Leverkusen 0:0 VfL Wolfsburg
			Hertha BSC 0:0 1. FSV Mainz 05
			FC Augsburg 0:0 FC Bayern München
			VfL Bochum 1848 0:0 TSG Hoffenheim
			Bayer 04 Leverkusen 0:0 Hertha BSC
			VfB Stuttgart 0:0 1. FC Küln
			Borussia Dortmund 0:0 RB Leipzig
			TSG Hoffenheim 0:0 VfL Bochum 1848
			Eintracht Frankfurt 0:0 1. FC Union Berlin
			Sport-Club Freiburg 0:0 FC Schalke 04
			VfL Wolfsburg 0:0 SV Werder Bremen
			Borussia Münchengladbach 0:0 FC Augsburg
			1. FSV Mainz 05 0:0 FC Bayern München
			Borussia Münchengladbach 0:0 VfL Bochum 1848
			RB Leipzig 0:0 VfL Wolfsburg
			SV Werder Bremen 0:0 Borussia Dortmund
			Bayer 04 Leverkusen 0:0 Sport-Club Freiburg
			1. FC Union Berlin 0:0 FC Bayern München
			1. FSV Mainz 05 0:0 1. FC Küln
			Eintracht Frankfurt 0:0 FC Schalke 04
			VfB Stuttgart 0:0 FC Augsburg
			TSG Hoffenheim 0:0 Hertha BSC
			VfL Wolfsburg 0:0 RB Leipzig
			FC Augsburg 0:0 1. FC Küln
			FC Bayern München 0:0 FC Schalke 04
			Borussia Dortmund 0:0 TSG Hoffenheim
			VfB Stuttgart 0:0 Borussia Münchengladbach
			Hertha BSC 0:0 Bayer 04 Leverkusen
			VfL Bochum 1848 0:0 1. FSV Mainz 05
			Sport-Club Freiburg 0:0 SV Werder Bremen
			1. FC Union Berlin 0:0 Eintracht Frankfurt
			FC Schalke 04 0:0 VfB Stuttgart
			1. FC Union Berlin 0:0 Hertha BSC
			Eintracht Frankfurt 0:0 FC Bayern München
			Sport-Club Freiburg 0:0 1. FC Küln
			VfL Bochum 1848 0:0 Bayer 04 Leverkusen
			1. FSV Mainz 05 0:0 RB Leipzig
			FC Augsburg 0:0 TSG Hoffenheim
			SV Werder Bremen 0:0 Borussia Münchengladbach
			VfL Wolfsburg 0:0 Borussia Dortmund
			VfL Bochum 1848 0:0 Borussia Münchengladbach
			SV Werder Bremen 0:0 1. FC Küln
			VfB Stuttgart 0:0 Borussia Dortmund
			Eintracht Frankfurt 0:0 Sport-Club Freiburg
			1. FC Union Berlin 0:0 Bayer 04 Leverkusen
			FC Schalke 04 0:0 FC Bayern München
			RB Leipzig 0:0 Hertha BSC
			1. FSV Mainz 05 0:0 FC Augsburg
			VfL Wolfsburg 0:0 TSG Hoffenheim
			RB Leipzig 0:0 1. FC Küln
			SV Werder Bremen 0:0 VfL Wolfsburg
			FC Bayern München 0:0 1. FSV Mainz 05
			VfB Stuttgart 0:0 FC Schalke 04
			Hertha BSC 0:0 VfL Bochum 1848
			1. FC Union Berlin 0:0 TSG Hoffenheim
			Sport-Club Freiburg 0:0 Eintracht Frankfurt
			FC Augsburg 0:0 Borussia Münchengladbach
			Bayer 04 Leverkusen 0:0 Borussia Dortmund
			FC Augsburg 0:0 SV Werder Bremen
			VfB Stuttgart 0:0 1. FC Union Berlin
			Eintracht Frankfurt 0:0 1. FSV Mainz 05
			Borussia Dortmund 0:0 VfL Wolfsburg
			FC Bayern München 0:0 Sport-Club Freiburg
			Bayer 04 Leverkusen 0:0 FC Schalke 04
			1. FC Küln 0:0 RB Leipzig
			Hertha BSC 0:0 TSG Hoffenheim
			1. FC Küln 0:0 Borussia Münchengladbach
			VfB Stuttgart 0:0 Bayer 04 Leverkusen
			Sport-Club Freiburg 0:0 FC Bayern München
			1. FSV Mainz 05 0:0 Borussia Dortmund
			RB Leipzig 0:0 Eintracht Frankfurt
			SV Werder Bremen 0:0 TSG Hoffenheim
			VfL Wolfsburg 0:0 FC Schalke 04
			VfL Bochum 1848 0:0 Hertha BSC
			1. FC Union Berlin 0:0 FC Augsburg
			TSG Hoffenheim 0:0 1. FC Union Berlin
			1. FC Küln 0:0 Sport-Club Freiburg
			Eintracht Frankfurt 0:0 Borussia Dortmund
			SV Werder Bremen 0:0 Hertha BSC
			Borussia Münchengladbach 0:0 Bayer 04 Leverkusen
			FC Bayern München 0:0 FC Augsburg
			1. FSV Mainz 05 0:0 FC Schalke 04
			RB Leipzig 0:0 VfL Bochum 1848
			VfL Wolfsburg 0:0 VfB Stuttgart
			Borussia Dortmund 0:0 Bayer 04 Leverkusen
			VfB Stuttgart 0:0 Eintracht Frankfurt
			RB Leipzig 0:0 1. FC Union Berlin
			TSG Hoffenheim 0:0 VfL Wolfsburg
			SV Werder Bremen 0:0 FC Schalke 04
			FC Augsburg 0:0 Sport-Club Freiburg
			FC Bayern München 0:0 Hertha BSC
			Borussia Münchengladbach 0:0 1. FSV Mainz 05
			VfL Bochum 1848 0:0 1. FC Küln
			VfL Bochum 1848 0:0 RB Leipzig
			VfB Stuttgart 0:0 SV Werder Bremen
			TSG Hoffenheim 0:0 Borussia Münchengladbach
			FC Schalke 04 0:0 1. FSV Mainz 05
			VfL Wolfsburg 0:0 FC Bayern München
			Sport-Club Freiburg 0:0 Bayer 04 Leverkusen
			Eintracht Frankfurt 0:0 1. FC Küln
			FC Augsburg 0:0 1. FC Union Berlin
			Borussia Dortmund 0:0 Hertha BSC
			FC Schalke 04 0:0 Hertha BSC
			Sport-Club Freiburg 0:0 RB Leipzig
			1. FC Küln 0:0 FC Bayern München
			Bayer 04 Leverkusen 0:0 VfB Stuttgart
			SV Werder Bremen 0:0 VfL Bochum 1848
			Eintracht Frankfurt 0:0 FC Augsburg
			Borussia Dortmund 0:0 1. FC Union Berlin
			Borussia Münchengladbach 0:0 TSG Hoffenheim
			1. FSV Mainz 05 0:0 VfL Wolfsburg
			Hertha BSC 0:0 1. FC Union Berlin
			FC Augsburg 0:0 RB Leipzig
			FC Bayern München 0:0 Bayer 04 Leverkusen
			Sport-Club Freiburg 0:0 Borussia Münchengladbach
			1. FSV Mainz 05 0:0 VfL Bochum 1848
			VfB Stuttgart 0:0 VfL Wolfsburg
			FC Schalke 04 0:0 Borussia Dortmund
			1. FC Küln 0:0 Eintracht Frankfurt
			TSG Hoffenheim 0:0 SV Werder Bremen
			FC Schalke 04 0:0 Bayer 04 Leverkusen
			FC Bayern München 0:0 Eintracht Frankfurt
			FC Augsburg 0:0 Borussia Dortmund
			1. FC Union Berlin 0:0 Sport-Club Freiburg
			TSG Hoffenheim 0:0 1. FC Küln
			VfL Bochum 1848 0:0 VfL Wolfsburg
			RB Leipzig 0:0 VfB Stuttgart
			Borussia Münchengladbach 0:0 Hertha BSC
			SV Werder Bremen 0:0 1. FSV Mainz 05
			Sport-Club Freiburg 0:0 VfB Stuttgart
			RB Leipzig 0:0 Bayer 04 Leverkusen
			VfL Bochum 1848 0:0 Eintracht Frankfurt
			Hertha BSC 0:0 VfL Wolfsburg
			1. FC Union Berlin 0:0 Borussia Dortmund
			TSG Hoffenheim 0:0 FC Augsburg
			Borussia Münchengladbach 0:0 FC Schalke 04
			1. FC Küln 0:0 SV Werder Bremen
			1. FC Küln 0:0 Hertha BSC
			FC Augsburg 0:0 VfL Wolfsburg
			TSG Hoffenheim 0:0 FC Schalke 04
			Sport-Club Freiburg 0:0 Borussia Münchengladbach
			1. FSV Mainz 05 0:0 RB Leipzig
			VfB Stuttgart 0:0 SV Werder Bremen
			Eintracht Frankfurt 0:0 Borussia Dortmund
			VfL Bochum 1848 0:0 Bayer 04 Leverkusen
			1. FC Union Berlin 0:0 FC Bayern München
			1. FC Union Berlin 0:0 Borussia Münchengladbach
			RB Leipzig 0:0 FC Schalke 04
			SV Werder Bremen 0:0 Eintracht Frankfurt
			VfB Stuttgart 0:0 1. FC Küln
			Borussia Dortmund 0:0 Sport-Club Freiburg
			VfL Wolfsburg 0:0 Bayer 04 Leverkusen
			1. FSV Mainz 05 0:0 Hertha BSC
			FC Bayern München 0:0 FC Augsburg
			TSG Hoffenheim 0:0 VfL Bochum 1848
			Hertha BSC 0:0 Bayer 04 Leverkusen
			1. FC Küln 0:0 VfB Stuttgart
			RB Leipzig 0:0 Borussia Dortmund
			VfL Bochum 1848 0:0 TSG Hoffenheim
			1. FC Union Berlin 0:0 Eintracht Frankfurt
			FC Schalke 04 0:0 Sport-Club Freiburg
			SV Werder Bremen 0:0 VfL Wolfsburg
			FC Augsburg 0:0 Borussia Münchengladbach
			FC Bayern München 0:0 1. FSV Mainz 05
			VfL Bochum 1848 0:0 Borussia Münchengladbach
			VfL Wolfsburg 0:0 RB Leipzig
			Borussia Dortmund 0:0 SV Werder Bremen
			Sport-Club Freiburg 0:0 Bayer 04 Leverkusen
			FC Bayern München 0:0 1. FC Union Berlin
			1. FC Küln 0:0 1. FSV Mainz 05
			FC Schalke 04 0:0 Eintracht Frankfurt
			FC Augsburg 0:0 VfB Stuttgart
			Hertha BSC 0:0 TSG Hoffenheim
			RB Leipzig 0:0 VfL Wolfsburg
			1. FC Küln 0:0 FC Augsburg
			FC Schalke 04 0:0 FC Bayern München
			TSG Hoffenheim 0:0 Borussia Dortmund
			Borussia Münchengladbach 0:0 VfB Stuttgart
			Bayer 04 Leverkusen 0:0 Hertha BSC
			1. FSV Mainz 05 0:0 VfL Bochum 1848
			SV Werder Bremen 0:0 Sport-Club Freiburg
			Eintracht Frankfurt 0:0 1. FC Union Berlin
			VfB Stuttgart 0:0 FC Schalke 04
			Hertha BSC 0:0 1. FC Union Berlin
			FC Bayern München 0:0 Eintracht Frankfurt
			1. FC Küln 0:0 Sport-Club Freiburg
			Bayer 04 Leverkusen 0:0 VfL Bochum 1848
			RB Leipzig 0:0 1. FSV Mainz 05
			TSG Hoffenheim 0:0 FC Augsburg
			Borussia Münchengladbach 0:0 SV Werder Bremen
			Borussia Dortmund 0:0 VfL Wolfsburg
			Borussia Münchengladbach 0:0 VfL Bochum 1848
			1. FC Küln 0:0 SV Werder Bremen
			Borussia Dortmund 0:0 VfB Stuttgart
			Sport-Club Freiburg 0:0 Eintracht Frankfurt
			Bayer 04 Leverkusen 0:0 1. FC Union Berlin
			FC Bayern München 0:0 FC Schalke 04
			Hertha BSC 0:0 RB Leipzig
			FC Augsburg 0:0 1. FSV Mainz 05
			TSG Hoffenheim 0:0 VfL Wolfsburg
			1. FC Küln 0:0 RB Leipzig
			VfL Wolfsburg 0:0 SV Werder Bremen
			1. FSV Mainz 05 0:0 FC Bayern München
			FC Schalke 04 0:0 VfB Stuttgart
			VfL Bochum 1848 0:0 Hertha BSC
			TSG Hoffenheim 0:0 1. FC Union Berlin
			Eintracht Frankfurt 0:0 Sport-Club Freiburg
			Borussia Münchengladbach 0:0 FC Augsburg
			Borussia Dortmund 0:0 Bayer 04 Leverkusen
			SV Werder Bremen 0:0 FC Augsburg
			1. FC Union Berlin 0:0 VfB Stuttgart
			1. FSV Mainz 05 0:0 Eintracht Frankfurt
			VfL Wolfsburg 0:0 Borussia Dortmund
			Sport-Club Freiburg 0:0 FC Bayern München
			FC Schalke 04 0:0 Bayer 04 Leverkusen
			RB Leipzig 0:0 1. FC Küln
			TSG Hoffenheim 0:0 Hertha BSC
			Borussia Münchengladbach 0:0 1. FC Küln
			Bayer 04 Leverkusen 0:0 VfB Stuttgart
			FC Bayern München 0:0 Sport-Club Freiburg
			Borussia Dortmund 0:0 1. FSV Mainz 05
			Eintracht Frankfurt 0:0 RB Leipzig
			TSG Hoffenheim 0:0 SV Werder Bremen
			FC Schalke 04 0:0 VfL Wolfsburg
			Hertha BSC 0:0 VfL Bochum 1848
			FC Augsburg 0:0 1. FC Union Berlin
			1. FC Union Berlin 0:0 TSG Hoffenheim
			Sport-Club Freiburg 0:0 1. FC Küln
			Borussia Dortmund 0:0 Eintracht Frankfurt
			Hertha BSC 0:0 SV Werder Bremen
			Bayer 04 Leverkusen 0:0 Borussia Münchengladbach
			FC Augsburg 0:0 FC Bayern München
			FC Schalke 04 0:0 1. FSV Mainz 05
			VfL Bochum 1848 0:0 RB Leipzig
			VfB Stuttgart 0:0 VfL Wolfsburg
			Bayer 04 Leverkusen 0:0 Borussia Dortmund
			Eintracht Frankfurt 0:0 VfB Stuttgart
			1. FC Union Berlin 0:0 RB Leipzig
			VfL Wolfsburg 0:0 TSG Hoffenheim
			FC Schalke 04 0:0 SV Werder Bremen
			Sport-Club Freiburg 0:0 FC Augsburg
			Hertha BSC 0:0 FC Bayern München
			1. FSV Mainz 05 0:0 Borussia Münchengladbach
			1. FC Küln 0:0 VfL Bochum 1848
			RB Leipzig 0:0 VfL Bochum 1848
			SV Werder Bremen 0:0 VfB Stuttgart
			Borussia Münchengladbach 0:0 TSG Hoffenheim
			1. FSV Mainz 05 0:0 FC Schalke 04
			FC Bayern München 0:0 VfL Wolfsburg
			Bayer 04 Leverkusen 0:0 Sport-Club Freiburg
			1. FC Küln 0:0 Eintracht Frankfurt
			1. FC Union Berlin 0:0 FC Augsburg
			Hertha BSC 0:0 Borussia Dortmund
			Hertha BSC 0:0 FC Schalke 04
			RB Leipzig 0:0 Sport-Club Freiburg
			FC Bayern München 0:0 1. FC Küln
			VfB Stuttgart 0:0 Bayer 04 Leverkusen
			VfL Bochum 1848 0:0 SV Werder Bremen
			FC Augsburg 0:0 Eintracht Frankfurt
			1. FC Union Berlin 0:0 Borussia Dortmund
			TSG Hoffenheim 0:0 Borussia Münchengladbach
			VfL Wolfsburg 0:0 1. FSV Mainz 05
			1. FC Union Berlin 0:0 Hertha BSC
			RB Leipzig 0:0 FC Augsburg
			Bayer 04 Leverkusen 0:0 FC Bayern München
			Borussia Münchengladbach 0:0 Sport-Club Freiburg
			VfL Bochum 1848 0:0 1. FSV Mainz 05
			VfL Wolfsburg 0:0 VfB Stuttgart
			Borussia Dortmund 0:0 FC Schalke 04
			Eintracht Frankfurt 0:0 1. FC Küln
			SV Werder Bremen 0:0 TSG Hoffenheim
			Bayer 04 Leverkusen 0:0 FC Schalke 04
			Eintracht Frankfurt 0:0 FC Bayern München
			Borussia Dortmund 0:0 FC Augsburg
			Sport-Club Freiburg 0:0 1. FC Union Berlin
			1. FC Küln 0:0 TSG Hoffenheim
			VfL Wolfsburg 0:0 VfL Bochum 1848
			VfB Stuttgart 0:0 RB Leipzig
			Hertha BSC 0:0 Borussia Münchengladbach
			1. FSV Mainz 05 0:0 SV Werder Bremen
			VfB Stuttgart 0:0 Sport-Club Freiburg
			Bayer 04 Leverkusen 0:0 RB Leipzig
			Eintracht Frankfurt 0:0 VfL Bochum 1848
			VfL Wolfsburg 0:0 Hertha BSC
			Borussia Dortmund 0:0 1. FC Union Berlin
			FC Augsburg 0:0 TSG Hoffenheim
			FC Schalke 04 0:0 Borussia Münchengladbach
			SV Werder Bremen 0:0 1. FC Küln
			1. FSV Mainz 05 0:0 Borussia Münchengladbach
			Borussia Münchengladbach 0:0 FC Bayern München
			FC Bayern München 0:0 VfL Bochum 1848
			VfL Bochum 1848 0:0 1. FSV Mainz 05\
			""";

	@Test
	void finishedBundesliga_generateStatistigsTable_isCorrectForFirstThreeTeams() {
		final League league = new League();
		league.addGameResults(parseGameResults(TEST_BUNDESLIGA, InputFormat.TEXT));

		final Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = league.generateStatisticsTable();

		assertThat(statisticsTable.get(new Team("Hertha BSC")).values()).containsExactly(14, 8, 12, 71, 65, 6, 50);
		assertThat(statisticsTable.get(new Team("1. FC Küln")).values()).containsExactly(16, 6, 12, 70, 60, 10, 54);
		assertThat(statisticsTable.get(new Team("VfL Wolfsburg")).values()).containsExactly(15, 7, 12, 69, 57, 12, 52);
	}

	@Test
	void finishedBundesliga_getResultsTable_teamsAreCorrectlySorted() {
		final League league = new League();
		league.addGameResults(parseGameResults(TEST_BUNDESLIGA, InputFormat.TEXT));

		final Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = league.generateStatisticsTable();

		assertThat(new ArrayList<>(statisticsTable.keySet())).containsExactly(new Team("Sport-Club Freiburg"),
				new Team("1. FC Küln"), new Team("Bayer 04 Leverkusen"), new Team("VfL Wolfsburg"),
				new Team("VfB Stuttgart"), new Team("Borussia Münchengladbach"), new Team("Hertha BSC"),
				new Team("Eintracht Frankfurt"), new Team("TSG Hoffenheim"), new Team("1. FC Union Berlin"),
				new Team("VfL Bochum 1848"), new Team("FC Schalke 04"), new Team("SV Werder Bremen"),
				new Team("FC Augsburg"), new Team("1. FSV Mainz 05"), new Team("FC Bayern München"),
				new Team("Borussia Dortmund"), new Team("RB Leipzig"));
	}

	@Test
	void finishedBundesliga_toFormattedTable_formattingIsCorrect() {
		final League league = new League();
		league.addGameResults(parseGameResults(TEST_BUNDESLIGA, InputFormat.TEXT));

		final String result = league.toFormattedTable();

		assertThat(result).isEqualTo(String.format("  #                           Team   W   T   L   +   -   =   P%n"
				+ "--------------------------------------------------------------%n"
				+ "  1            Sport-Club Freiburg  20   5   9  76  69   7  65%n"
				+ "  2                     1. FC Küln  16   6  12  70  60  10  54%n"
				+ "  3            Bayer 04 Leverkusen  15   8  11  64  58   6  53%n"
				+ "  4                  VfL Wolfsburg  15   7  12  69  57  12  52%n"
				+ "  5                  VfB Stuttgart  15   6  13  54  48   6  51%n"
				+ "  6       Borussia Münchengladbach  14   8  10  65  47  18  50%n"
				+ "  7                     Hertha BSC  14   8  12  71  65   6  50%n"
				+ "  8            Eintracht Frankfurt  13   9  12  62  56   6  48%n"
				+ "  9                 TSG Hoffenheim  12  11  11  64  64   0  47%n"
				+ " 10             1. FC Union Berlin  12   9  13  55  68 -13  45%n"
				+ " 11                VfL Bochum 1848  11   9  12  64  68  -4  42%n"
				+ " 12                  FC Schalke 04  11   9  14  50  57  -7  42%n"
				+ " 13               SV Werder Bremen  11   9  14  48  70 -22  42%n"
				+ " 14                    FC Augsburg  10  11  13  50  49   1  41%n"
				+ " 15                1. FSV Mainz 05  11   7  14  59  56   3  40%n"
				+ " 16              FC Bayern München   9   9  14  61  69  -8  36%n"
				+ " 17              Borussia Dortmund   9   9  16  56  65  -9  36%n"
				+ " 18                     RB Leipzig   8  12  14  64  76 -12  36"));
	}

	@Test
	void teamsWithSamePointsGoalDifferenceWins_getResultsTable_teamsAreSortedByAlphabet() {
		final League league = new League();
		league.addGameResults(parseGameResults(TEST_BUNDESLIGA_EQUAL, InputFormat.TEXT));

		final Map<Team, Map<StatisticsTableColumn, Integer>> statisticsTable = league.generateStatisticsTable();

		assertThat(new ArrayList<>(statisticsTable.keySet())).containsExactly(new Team("1. FC Küln"),
				new Team("1. FC Union Berlin"), new Team("1. FSV Mainz 05"), new Team("Bayer 04 Leverkusen"),
				new Team("Borussia Dortmund"), new Team("Borussia Münchengladbach"), new Team("Eintracht Frankfurt"),
				new Team("FC Augsburg"), new Team("FC Bayern München"), new Team("FC Schalke 04"),
				new Team("Hertha BSC"), new Team("RB Leipzig"), new Team("Sport-Club Freiburg"),
				new Team("SV Werder Bremen"), new Team("TSG Hoffenheim"), new Team("VfB Stuttgart"),
				new Team("VfL Bochum 1848"), new Team("VfL Wolfsburg"));
	}

}
