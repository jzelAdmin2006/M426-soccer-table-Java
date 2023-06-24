package tech.bison.trainee2021.SoccerTableStats.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class StreamUtils {
	private StreamUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> mapper) {
		return collection.stream().map(mapper).toList();
	}
}
