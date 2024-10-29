package com.edvaldovitor.PlanetZoo.repository;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalRepository {

    @Autowired
    private JdbcTemplate template;

    public void save(Animal animal) {
        String sql = "INSERT INTO animal (id, name, species) VALUES (?, ?, ?)";
        int rows = template.update(sql, animal.getId(), animal.getName(), animal.getSpecies());
        System.out.println(rows + " row(s) inserted.");
    }

    public Animal createAndSaveAnimal(int id, String name, String species) {
        Animal animal = new Animal(id, name, species);
        save(animal);
        System.out.println("Animal saved: " + animal);
        return animal;
    }

    public List<Animal> findAll() {
        String sql = "SELECT * FROM animal";
        return template.query(sql, (rs, rowNum) -> new Animal(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("species")
        ));
    }
}
