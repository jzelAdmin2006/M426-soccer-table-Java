package tech.bison.trainee2021.SoccerTableStats.util;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class StreamUtils {
	public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> mapper) {
		return collection.stream().map(mapper).collect(toList());
	}
}
