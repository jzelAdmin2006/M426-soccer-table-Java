package tech.bison.trainee2021.SoccerTableStats.structure.business;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import tech.bison.trainee2021.SoccerTableStats.InputFormat;
import tech.bison.trainee2021.SoccerTableStats.service.MapperService;
import tech.bison.trainee2021.SoccerTableStats.structure.data.GameResultDto;

public class GameResult {

	private final Team homeTeam;
	private final Team awayTeam;
	private final int homeTeamScore;
	private final int awayTeamScore;

	public GameResult(Team homeTeam, Team awayTeam, int homeTeamScore, int awayTeamScore) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public static List<GameResult> parseGameResults(String gameResultsToParse, InputFormat inputFormat) {
		switch (inputFormat) {
		case JSON:
			return parseFromJson(gameResultsToParse);
		case TEXT:
			return parseFromText(gameResultsToParse);
		}
		throw new UnsupportedOperationException(
				String.format("The game result parsing for the input format \"%s\" isn't implemented.", inputFormat));
	}

	public static GameResult parseGameResult(String gameResult, InputFormat inputFormat) {
		return parseGameResults(gameResult, inputFormat).get(0);
	}

	private static List<GameResult> parseFromText(String gameResultsToParse) {
		Matcher matcher = Pattern.compile("^(.*?)\\s(\\d+):(\\d+)\\s(.*)$", Pattern.MULTILINE)
				.matcher(gameResultsToParse);
		List<GameResult> gameResults = new ArrayList<>();
		while (matcher.find()) {
			gameResults.add(new GameResult(new Team(matcher.group(1)), new Team(matcher.group(4)),
					Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
		}
		return gameResults;
	}

	private static List<GameResult> parseFromJson(String gameResultsToParse) {
		JsonElement element = JsonParser.parseString(gameResultsToParse);
		if (element.isJsonArray()) {
			return parseFromJsonArray(element);
		} else {
			return Collections.singletonList(parseFromJsonObject(element));
		}
	}

	private static List<GameResult> parseFromJsonArray(JsonElement element) {
		Type gameResultListType = new TypeToken<List<GameResultDto>>() {
		}.getType();
		List<GameResultDto> dtos = new Gson().fromJson(element, gameResultListType);
		return dtos.stream().map(new MapperService()::fromDto).toList();
	}

	private static GameResult parseFromJsonObject(JsonElement element) {
		return new MapperService().fromDto(new Gson().fromJson(element, GameResultDto.class));
	}
}
