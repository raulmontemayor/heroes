package es.mindata.heroes.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.mindata.heroes.adapters.out.persistence.model.HeroModel;
import es.mindata.heroes.adapters.out.persistence.repository.HeroRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@RestController
@Data
public class HeroController {
	
	@Autowired
	private HeroRepository repository;
	
	@ApiOperation(value = "Search heros by universe")
	@GetMapping("/hero/search/universe")
	public List<HeroModel> searchByUniverse(
			@ApiParam(value = "Currently Marvel or DC, Marvel is default if empty", required = true) @RequestParam(required = false) Optional<String> name) {
		// INFO: I know this is not the best way to filter results from DB, but I'm doing this
		// just to demonstrate streams and optional
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.filter(h -> h.getUniverse().equals(name.orElse("Marvel"))).collect(Collectors.toList());

	}

}
