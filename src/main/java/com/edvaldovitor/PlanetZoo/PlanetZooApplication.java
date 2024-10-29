package com.edvaldovitor.PlanetZoo;

import com.edvaldovitor.PlanetZoo.repository.AnimalRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PlanetZooApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PlanetZooApplication.class, args);
		AnimalRepository repo = context.getBean(AnimalRepository.class);

		try {
			repo.createAndSaveAnimal(111, "Cachorro", "PitBull");

			repo.update(111, "Cachorro Atualizado", "Labrador");

			repo.findAll().forEach(System.out::println);

			repo.delete(111);

			repo.findAll().forEach(System.out::println);

		} catch (Exception e) {
			System.err.println("An error occurred while interacting with the repository: " + e.getMessage());
		}
	}
}
