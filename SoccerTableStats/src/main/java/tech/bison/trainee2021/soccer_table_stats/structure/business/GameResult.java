package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static com.google.gson.JsonParser.parseString;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.compile;
import static tech.bison.trainee2021.soccer_table_stats.util.StreamUtils.mapToList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import tech.bison.trainee2021.soccer_table_stats.InputFormat;
import tech.bison.trainee2021.soccer_table_stats.service.MapperService;
import tech.bison.trainee2021.soccer_table_stats.structure.data.GameResultDto;

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

	public static GameResult parseGameResult(String gameResult, InputFormat inputFormat) {
		return parseGameResults(gameResult, inputFormat).get(0);
	}

	public static List<GameResult> parseGameResults(String gameResultsToParse, InputFormat inputFormat) {
		switch (inputFormat) {
		case JSON:
			return parseFromJson(gameResultsToParse);
		case TEXT:
			return parseFromText(gameResultsToParse);
		}
		throw new UnsupportedOperationException(
				format("The game result parsing for the input format \"%s\" isn't implemented.", inputFormat));
	}

	private static List<GameResult> parseFromJson(String json) {
		JsonElement element = parseString(json);
		if (element.isJsonArray()) {
			return parseFromJsonArray(element);
		} else {
			return singletonList(parseFromJsonObject(element));
		}
	}

	private static List<GameResult> parseFromJsonArray(JsonElement element) {
		Type gameResultListType = new TypeToken<List<GameResultDto>>() {
		}.getType();
		List<GameResultDto> dtos = new Gson().fromJson(element, gameResultListType);
		return mapToList(dtos, new MapperService()::fromDto);
	}

	private static GameResult parseFromJsonObject(JsonElement element) {
		return new MapperService().fromDto(new Gson().fromJson(element, GameResultDto.class));
	}

	private static List<GameResult> parseFromText(String gameResultsToParse) {
		Matcher matcher = compile("^(.*?)\\s(\\d+):(\\d+)\\s(.*)$", MULTILINE).matcher(gameResultsToParse);
		List<GameResult> gameResults = new ArrayList<>();
		while (matcher.find()) {
			gameResults.add(new GameResult(new Team(matcher.group(1)), new Team(matcher.group(4)),
					parseInt(matcher.group(2)), parseInt(matcher.group(3))));
		}
		return gameResults;
	}

}
