package tech.bison.trainee2021.soccer_table_stats;

import static java.lang.System.getenv;
import static tech.bison.trainee2021.soccer_table_stats.structure.business.GameResult.parseGameResults;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import tech.bison.trainee2021.soccer_table_stats.structure.business.League;

public class SoccerTableStats {
	public static final String SOCCER_TABLE_STATS_FILE_EXTENSION = ".scrts";
	private static final String INPUT_FILES_LOCATION = validateGetEnv("INPUT_FILES_LOCATION");
	private static final String OUTPUT_FILES_LOCATION = validateGetEnv("OUTPUT_FILES_LOCATION");
	private static final Logger LOGGER = Logger.getLogger(SoccerTableStats.class.getName());

	public static void main(String[] args) {
		new SoccerTableStats().writeOutputByInput();
	}

	private void writeOutputByInput() {
		final File[] inputFiles = new File(INPUT_FILES_LOCATION).listFiles();
		for (File file : inputFiles) {
			final String destinationPath = Path.of(OUTPUT_FILES_LOCATION).resolve(file.getName()).toString()
					+ SOCCER_TABLE_STATS_FILE_EXTENSION;
			try (FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
				final League league = new League();
				final Path path = Path.of(file.getAbsolutePath());
				league.addGameResults(parseGameResults(Files.readString(path),
						InputFormat.fromFileName(path.getFileName().toString())));
				outputStream.write(league.toFormattedTable().getBytes());
				LOGGER.log(Level.INFO, "Generated \"{0}\".", destinationPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String validateGetEnv(String variable) {
		final String value = getenv(variable);
		if (value == null) {
			throw new EnvironmentVariableNotSetException(variable);
		}
		return value;
	}

}
