package tech.bison.trainee2021.SoccerTableStats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import tech.bison.trainee2021.SoccerTableStats.structure.business.GameResult;
import tech.bison.trainee2021.SoccerTableStats.structure.business.League;

public class SoccerTableStats {
  private static final String INPUT_FILES_LOCATION = System.getenv("INPUT_FILES_LOCATION");
  private static final String OUTPUT_FILES_LOCATION = System.getenv("OUTPUT_FILES_LOCATION");

  public static void main(String[] args) {
    new SoccerTableStats().writeOutputByInput();
  }

  private void writeOutputByInput() {
    File[] inputFiles = new File(INPUT_FILES_LOCATION).listFiles();
    for (File file : inputFiles) {
      try {
        League league = new League();
        league.addGameResults(GameResult.parseGameResults(Files.readString(Path.of(file.getAbsolutePath()))));
        FileOutputStream outputStream = new FileOutputStream(
            Path.of(OUTPUT_FILES_LOCATION).resolve(file.getName()).toString());
        outputStream.write(league.toFormattedTable().getBytes());
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
