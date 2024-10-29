package com.edvaldovitor.PlanetZoo;

import com.edvaldovitor.PlanetZoo.model.Animal;
import com.edvaldovitor.PlanetZoo.repository.AnimalRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class PlanetZooApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PlanetZooApplication.class, args);
		AnimalRepository repo = context.getBean(AnimalRepository.class);

		try {
			Animal animal1 = new Animal(101, "Elefante", "African");
			Animal animal2 = new Animal(102, "Tigre", "Bengali");
			repo.saveAll(Arrays.asList(animal1, animal2));

			animal1.setName("Elefante Atualizado");
			animal2.setSpecies("Tigre Atualizado");
			repo.updateAll(Arrays.asList(animal1, animal2));

			System.out.println("Todos os Animais:");
			repo.findAll().forEach(System.out::println);

			System.out.println("Animais da espécie 'African':");
			repo.findBySpecies("African").forEach(System.out::println);

			repo.deleteAll(Arrays.asList(101, 102));

			System.out.println("Animais após exclusão:");
			repo.findAll().forEach(System.out::println);

		} catch (Exception e) {
			System.err.println("An error occurred while interacting with the repository: " + e.getMessage());
		}
	}
}
