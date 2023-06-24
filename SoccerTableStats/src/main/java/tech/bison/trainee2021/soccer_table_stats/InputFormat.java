package tech.bison.trainee2021.soccer_table_stats;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum InputFormat {
	JSON(".json"), TEXT("");

	private String fileExtension;

	private InputFormat(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public static InputFormat fromFileName(String fileName) {
		return stream(InputFormat.values()).filter(format -> fileName.toLowerCase().endsWith(format.fileExtension))
				.findFirst()
				// should never happen
				.orElseThrow(() -> new UnsupportedOperationException(
						format("An input format for the file name \"%s\" isn't implemented.", fileName)));
	}
}