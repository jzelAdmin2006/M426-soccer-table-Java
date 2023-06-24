package tech.bison.trainee2021.soccer_table_stats.structure.business;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TeamTest {

	@Test
	void newClubWithName_getName_isTheSame() {
		Team club = new Team("Name");

		String result = club.getName();

		assertThat(result).isEqualTo("Name");
	}

	@Test
	void newClubWithDifferentName_getName_isTheSame() {
		Team club = new Team("Name2");

		String result = club.getName();

		assertThat(result).isEqualTo("Name2");
	}

}
