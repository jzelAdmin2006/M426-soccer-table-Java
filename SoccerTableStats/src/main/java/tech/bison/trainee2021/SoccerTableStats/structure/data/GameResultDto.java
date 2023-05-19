package tech.bison.trainee2021.SoccerTableStats.structure.data;

import java.util.Objects;

public class GameResultDto {
  private String homeTeam;
  private String awayTeam;
  private int homeGoals;
  private int awayGoals;

  public GameResultDto() {
    homeTeam = "";
    awayTeam = "";
    homeGoals = 0;
    awayGoals = 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(awayGoals, awayTeam, homeGoals, homeTeam);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GameResultDto other = (GameResultDto) obj;
    return awayGoals == other.awayGoals && Objects.equals(awayTeam, other.awayTeam) && homeGoals == other.homeGoals
        && Objects.equals(homeTeam, other.homeTeam);
  }

  public GameResultDto(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeGoals = homeGoals;
    this.awayGoals = awayGoals;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getHomeGoals() {
    return homeGoals;
  }

  public void setHomeGoals(int homeGoals) {
    this.homeGoals = homeGoals;
  }

  public int getAwayGoals() {
    return awayGoals;
  }

  public void setAwayGoals(int awayGoals) {
    this.awayGoals = awayGoals;
  }
}
