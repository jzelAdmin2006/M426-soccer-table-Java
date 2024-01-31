package tech.bison.trainee2021.soccer_table_stats.util;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.bison.trainee2021.soccer_table_stats.util.StreamUtils.mapToList;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamUtilsTest {

	@Test
	void collectionOfNumbers_doubleEachWithMapToList_isCorrect() {
		final Collection<Integer> numbers = List.of(1, 2, 3, 4, 5);

		final List<Integer> result = mapToList(numbers, (n) -> n * 2);

		assertThat(result).containsExactly(2, 4, 6, 8, 10);
	}

	@Test
	void listOfStrings_toLowerEachWithMapToList_isCorrect() {
		final List<String> strings = List.of("Foo", "Bar", "FooBar", "AHDFasdfBDFAFDAAqwertTZ834T");

		final List<String> result = mapToList(strings, String::toLowerCase);

		assertThat(result).containsExactly("foo", "bar", "foobar", "ahdfasdfbdfafdaaqwerttz834t");
	}

}
