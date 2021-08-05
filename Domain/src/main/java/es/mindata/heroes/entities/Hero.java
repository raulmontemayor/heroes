package es.mindata.heroes.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Hero {
	
	private Long id;
	private String fullName;
	private String alterEgo;
	private String species;
	private String universe;
	
}
