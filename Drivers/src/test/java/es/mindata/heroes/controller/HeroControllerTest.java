package es.mindata.heroes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.mindata.heroes.adapters.out.persistence.model.HeroModel;
import es.mindata.heroes.adapters.out.persistence.repository.HeroRepository;

@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {
	
	private HeroController controller = new HeroController();
	
	@Mock
	private HeroRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.controller = new HeroController();
		controller.setRepository(repository);
	}

	@Test
	public void testSearchByUniverse_FilterWorks() {
		// Arrange
		when(repository.findAll()).thenReturn(getDataForTest());
		// Act
		List<HeroModel> response = controller.searchByUniverse(Optional.of("DC"));
		// Assert
		assertEquals(3, response.size());
		assertEquals("Bruce Wayne", response.get(0).getFullName());
		assertEquals("Harley Quinn", response.get(1).getFullName());
		assertEquals("Kal-hel", response.get(2).getFullName());
	}

	private Iterable<HeroModel> getDataForTest() {
		final List<HeroModel> result = new LinkedList<>();
		result.add(HeroModel.builder().fullName("Peter Parker").universe("Marvel").build());
		result.add(HeroModel.builder().fullName("Tony Stark").universe("Marvel").build());
		result.add(HeroModel.builder().fullName("Thor Odinson").universe("Marvel").build());
		result.add(HeroModel.builder().fullName("Steve Rogers").universe("Marvel").build());
		result.add(HeroModel.builder().fullName("Bruce Wayne").universe("DC").build());
		result.add(HeroModel.builder().fullName("Harley Quinn").universe("DC").build());
		result.add(HeroModel.builder().fullName("Kal-hel").universe("DC").build());
		return result;
	}

}
