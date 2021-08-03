package es.mindata.heroes.adapters.out.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroModelTest {

	
	private HeroModel hero;
	
	@BeforeEach
	public void setUp() {
		hero = new HeroModel();
	}
	
	@Test
	void testGetId() {
		final Long id = 1000L;
		hero.setId(id);
		assertEquals(id, hero.getId(), "Incorrect Id");
	}
	

	@Test
	void testGetFullName() {
		final String fullName = "Saitama";
		hero.setFullName(fullName);
		assertEquals(fullName, hero.getFullName(), "Incorrect Full Name");
	}
	
	@Test
	void testGetAlterEgo() {
		final String alterEgo = "One Punch Man";
		hero.setAlterEgo(alterEgo);
		assertEquals(alterEgo, hero.getAlterEgo(), "Incorrect Alter Ego");
	}
	
	@Test
	void testGetSpecies() {
		final String species = "Human";
		hero.setSpecies(species);
		assertEquals(species, hero.getSpecies(), "Incorrect Species");
	}
	
	@Test
	void testGetUniverse() {
		final String universe = "Shonen";
		hero.setUniverse(universe);
		assertEquals(universe, hero.getUniverse(), "Incorrect Universe");
	}


}
