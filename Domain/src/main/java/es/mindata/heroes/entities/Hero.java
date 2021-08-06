package es.mindata.heroes.entities;

import lombok.Data;

@Data
public class Hero {
	
	private Long id;
	private String fullName;
	private String alterEgo;
	private String species;
	private String universe;
	
}
