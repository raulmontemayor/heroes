package es.mindata.heroes.adapters.out.persistence.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import es.mindata.heroes.adapters.out.persistence.model.HeroModel;

@RepositoryRestResource(collectionResourceRel = "hero", path = "hero")
public interface HeroRepository
		extends CrudRepository<HeroModel, Long>, PagingAndSortingRepository<HeroModel, Long> {
	
	@RestResource(path = "fullNameContains", rel = "fullNameContains")
	@Cacheable
	public Page<HeroModel> findByFullNameContainingIgnoreCase(@Param("fullName") String fullName, Pageable p);
	
	@RestResource(path = "alterEgoContains", rel = "alterEgoContains")
	@Cacheable
	public Page<HeroModel> findByAlterEgoContainingIgnoreCase(@Param("alterEgo") String alterEgo, Pageable p);
	

}
