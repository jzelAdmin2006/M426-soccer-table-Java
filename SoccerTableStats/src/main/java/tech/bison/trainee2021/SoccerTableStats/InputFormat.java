package tech.bison.trainee2021.SoccerTableStats;

import java.util.Arrays;

public enum InputFormat {
	JSON(".json"), TEXT("");

	private String fileExtension;

	private InputFormat(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public static InputFormat fromFileName(String fileName) {
		return Arrays.stream(InputFormat.values())
				.filter(format -> fileName.toLowerCase().endsWith(format.fileExtension)).findFirst()
				// should never happen
				.orElseThrow(() -> new UnsupportedOperationException(
						String.format("An input format for the file name \"%s\" isn't implemented.", fileName)));
	}
}