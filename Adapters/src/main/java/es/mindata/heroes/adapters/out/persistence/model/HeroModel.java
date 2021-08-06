package es.mindata.heroes.adapters.out.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.rest.core.annotation.RestResource;

import es.mindata.heroes.entities.Hero;

@RestResource
@Entity(name = "hero")
public class HeroModel extends Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public String getFullName() {
		return super.getFullName();
	}

	@Override
	public void setFullName(String fullName) {
		super.setFullName(fullName);
	}

	@Override
	public String getAlterEgo() {
		return super.getAlterEgo();
	}

	@Override
	public void setAlterEgo(String alterEgo) {
		super.setAlterEgo(alterEgo);
	}

	@Override
	public String getSpecies() {
		return super.getSpecies();
	}

	@Override
	public void setSpecies(String species) {
		super.setSpecies(species);
	}

	@Override
	public String getUniverse() {
		return super.getUniverse();
	}

	@Override
	public void setUniverse(String universe) {
		super.setUniverse(universe);
	}

	public static HeroModelBuilder builder() {
		return new HeroModelBuilder(new HeroModel());
	}

	public static class HeroModelBuilder {

		private HeroModel heroModel;

		public HeroModelBuilder(HeroModel heroModel) {
			this.heroModel = heroModel;
		}

		public HeroModelBuilder fullName(String fullName) {
			heroModel.setFullName(fullName);
			return this;
		}

		public HeroModelBuilder universe(String universe) {
			heroModel.setUniverse(universe);
			return this;
		}

		public HeroModel build() {
			return heroModel;
		}
	}

}
