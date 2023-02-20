package tech.bison.trainee2021.SoccerTableStats.structure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ClubTest {
	@Test
	void newClubWithName_getName_isTheSame() {
		Club club = new Club("Name");

		String result = club.getName();

		assertThat(result).isEqualTo("Name");
	}
}
