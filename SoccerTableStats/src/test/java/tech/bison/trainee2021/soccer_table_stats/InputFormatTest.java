package tech.bison.trainee2021.soccer_table_stats;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InputFormatTest {

	@ParameterizedTest(name = "{2}FileExtension_fromFileName_is{1}")
	@MethodSource("provideValuesForInputFormat")
	void fileWithExtension_fromFileName_isCorrect(String fileName, InputFormat expected, String inputName) {
		InputFormat result = InputFormat.fromFileName(fileName);

		assertThat(result).isEqualTo(expected);
	}

	private static Stream<Arguments> provideValuesForInputFormat() {
		return Stream.of(Arguments.of("foo.json", InputFormat.JSON, "json"),
				Arguments.of("bar.JSON", InputFormat.JSON, "capitalJson"),
				Arguments.of("bar.JsOn", InputFormat.JSON, "mixedCaseJson"),
				Arguments.of("foobar", InputFormat.TEXT, "no"),
				Arguments.of(
						"foobar." + Stream.generate(() -> randomAlphabetic(3, 6))
								.filter(s -> !s.equalsIgnoreCase("json")).findFirst().orElse("asdfqwert"),
						InputFormat.TEXT, "random"));
	}

}
