package com.edvaldovitor.PlanetZoo;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PlanetZooApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PlanetZooApplication.class, args);

		Animal animal1 = context.getBean(Animal.class);
		animal1.setId(111);
		animal1.setName("Cachorro");
		animal1.setSpecies("PitBull");

		AnimalRepository repo = context.getBean(AnimalRepository.class);
		repo.save(animal1);

		System.out.println(repo.findAll());



	}

}
