package tech.bison.trainee2021.soccer_table_stats;

import static java.lang.String.format;

class EnvironmentVariableNotSetException extends IllegalArgumentException {
	private static final long serialVersionUID = 8052329652437633248L;

	EnvironmentVariableNotSetException(String variable) {
		super(format("The environment variable \"%s\" was not set.", variable));
	}
}
