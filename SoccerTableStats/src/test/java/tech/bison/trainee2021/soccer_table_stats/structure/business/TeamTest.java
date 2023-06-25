package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TeamTest {

	@Test
	void newClubWithName_getName_isTheSame() {
		final Team club = new Team("Name");

		final String result = club.getName();

		assertThat(result).isEqualTo("Name");
	}

	@Test
	void newClubWithDifferentName_getName_isTheSame() {
		final Team club = new Team("Name2");

		final String result = club.getName();

		assertThat(result).isEqualTo("Name2");
	}

}
