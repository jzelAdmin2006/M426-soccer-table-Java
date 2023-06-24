package tech.bison.trainee2021.SoccerTableStats;

class EnvironmentVariableNotSetException extends IllegalArgumentException {
	private static final long serialVersionUID = 8052329652437633248L;

	EnvironmentVariableNotSetException(String variable) {
		super(String.format("The environment variable \"%s\" was not set.", variable));
	}
}
