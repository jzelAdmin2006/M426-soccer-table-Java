package tech.bison.trainee2021.SoccerTableStats.util;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.bison.trainee2021.SoccerTableStats.util.StreamUtils.mapToList;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StreamUtilsTest {
	@Test
	void collectionOfNumbers_doubleEachWithMapToList_isCorrect() {
		Collection<Integer> numbers = List.of(1, 2, 3, 4, 5);

		List<Integer> result = mapToList(numbers, (n) -> n * 2);

		assertThat(result).containsExactly(2, 4, 6, 8, 10);
	}

	@Test
	void listOfStrings_toLowerEachWithMapToList_isCorrect() {
		List<String> strings = List.of("Foo", "Bar", "FooBar", "AHDFasdfBDFAFDAAqwertTZ834T");

		List<String> result = mapToList(strings, String::toLowerCase);

		assertThat(result).containsExactly("foo", "bar", "foobar", "ahdfasdfbdfafdaaqwerttz834t");
	}
}
