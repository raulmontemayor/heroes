package es.mindata.heroes;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
@WithMockUser
public class IntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	
	void testGetOneHero() throws Exception {
		log.info("testGetOneHero");
		mockMvc.perform(get("/hero/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName", is("Peter Parker")));
	}

	@Test
	@Order(2)
	void testGetAllHeros() throws Exception {
		log.info("testGetAllHeros");
		mockMvc.perform(get("/hero")).andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.hero[1].fullName", is("Tony Stark")))
				.andExpect(jsonPath("$.page.totalElements", is(28)));
	}
	
	@Test
	@Order(3)
	void testSearchHero() throws Exception {
		log.info("testSearchHero");
		mockMvc.perform(get("/hero/search/fullNameContains?fullName=rang")).andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.hero[0].fullName", is("Sthepen Strange")))
				.andExpect(jsonPath("$.page.totalElements", is(1)));
	}


	@Test
	@Order(4)
	void testInsertHero() throws Exception {
		log.info("testInsertHero");
		mockMvc.perform(
				post("/hero").content("{ \"fullName\" : \"Damian Wayne\", \"alterEgo\" : \"Robin\","
						+ " \"species\" : \"Human\", \"universe\": \"DC\" }"))
				.andExpect(status().isCreated());
		mockMvc.perform(get("/hero/29")).andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName", is("Damian Wayne")));
	}

	@Test
	@Order(5)
	void testUpdateHero() throws Exception {
		log.info("testUpdateHero");
		mockMvc.perform(patch("/hero/3").content("{ \"fullName\" : \"Dick Grayson\" }"))
				.andExpect(status().isNoContent());
		mockMvc.perform(get("/hero/3")).andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName", is("Dick Grayson")));
	}

	@Test
	@Order(6)
	void testDeleteHero() throws Exception {
		log.info("testDeleteHero");
		mockMvc.perform(delete("/hero/28")).andExpect(status().isNoContent());
		mockMvc.perform(get("/hero/28")).andExpect(status().isNotFound());
	}

}