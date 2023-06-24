package tech.bison.trainee2021.SoccerTableStats;

import static java.lang.System.getenv;
import static tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult.parseGameResults;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import tech.bison.trainee2021.SoccerTableStats.structure.business.League;

public class SoccerTableStats {
	private static final String INPUT_FILES_LOCATION = validateGetEnv("INPUT_FILES_LOCATION");
	private static final String OUTPUT_FILES_LOCATION = validateGetEnv("OUTPUT_FILES_LOCATION");
	public static final String SOCCER_TABLE_STATS_FILE_EXTENSION = ".scrts";

	public static void main(String[] args) {
		new SoccerTableStats().writeOutputByInput();
	}

	private static String validateGetEnv(String variable) throws EnvironmentVariableNotSetException {
		String value = getenv(variable);
		if (value == null) {
			throw new EnvironmentVariableNotSetException(variable);
		}
		return value;
	}

	private void writeOutputByInput() {
		File[] inputFiles = new File(INPUT_FILES_LOCATION).listFiles();
		for (File file : inputFiles) {
			try {
				League league = new League();
				Path path = Path.of(file.getAbsolutePath());
				league.addGameResults(parseGameResults(Files.readString(path),
						InputFormat.fromFileName(path.getFileName().toString())));
				FileOutputStream outputStream = new FileOutputStream(
						Path.of(OUTPUT_FILES_LOCATION).resolve(file.getName()).toString()
								+ SOCCER_TABLE_STATS_FILE_EXTENSION);
				outputStream.write(league.toFormattedTable().getBytes());
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
