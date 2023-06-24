package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static java.lang.String.format;

public enum StatisticsTableColumn {
	WINS, TIES, LOSSES, GOALS, COUNTERGOALS, GOALDIFFERENCE, POINTS;

	public char translate() {
		switch (this) {
		case COUNTERGOALS:
			return '-';
		case GOALDIFFERENCE:
			return '=';
		case GOALS:
			return '+';
		case LOSSES:
			return 'L';
		case POINTS:
			return 'P';
		case TIES:
			return 'T';
		case WINS:
			return 'W';
		}
		// should never happen
		throw new UnsupportedOperationException(
				format("The designation of the column \"%s\" isn't implemented.", this));
	}
}