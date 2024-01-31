package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static java.util.Objects.hash;

import java.util.Objects;

public class Team {

	private final String name;

	public Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Team other = (Team) obj;
		return Objects.equals(name, other.name);
	}
}
