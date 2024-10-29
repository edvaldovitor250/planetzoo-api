package com.edvaldovitor.PlanetZoo.repository;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public void saveAll(List<Animal> animals) {
        String sql = "INSERT INTO animal (id, name, species) VALUES (?, ?, ?)";
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Animal animal = animals.get(i);
                ps.setInt(1, animal.getId());
                ps.setString(2, animal.getName());
                ps.setString(3, animal.getSpecies());
            }

            @Override
            public int getBatchSize() {
                return animals.size();
            }
        });
        System.out.println(animals.size() + " row(s) inserted.");
    }

    public List<Animal> findAll() {
        String sql = "SELECT * FROM animal";
        return template.query(sql, (rs, rowNum) -> new Animal(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("species")
        ));
    }

    public List<Animal> findBySpecies(String species) {
        String sql = "SELECT * FROM animal WHERE species = ?";
        return template.query(sql, new Object[]{species}, (rs, rowNum) -> new Animal(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("species")
        ));
    }

    public void update(int id, String newName, String newSpecies) {
        String sql = "UPDATE animal SET name = ?, species = ? WHERE id = ?";
        int rows = template.update(sql, newName, newSpecies, id);
        System.out.println(rows + " row(s) updated for animal ID " + id + ".");
    }

    public void updateAll(List<Animal> animals) {
        String sql = "UPDATE animal SET name = ?, species = ? WHERE id = ?";
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Animal animal = animals.get(i);
                ps.setString(1, animal.getName());
                ps.setString(2, animal.getSpecies());
                ps.setInt(3, animal.getId());
            }

            @Override
            public int getBatchSize() {
                return animals.size();
            }
        });
        System.out.println(animals.size() + " row(s) updated.");
    }

    public void delete(int id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        int rows = template.update(sql, id);
        System.out.println(rows + " row(s) deleted for animal ID " + id + ".");
    }

    public void deleteAll(List<Integer> ids) {
        String sql = "DELETE FROM animal WHERE id = ?";
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
        System.out.println(ids.size() + " row(s) deleted.");
    }
}
