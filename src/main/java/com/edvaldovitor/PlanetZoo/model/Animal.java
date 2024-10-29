package com.edvaldovitor.PlanetZoo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Animal {

    private int id;
    private String name;
    private String species;

    // Construtor padrão
    public Animal() {
    }

    // Construtor completo
    public Animal(int id, String name, String species) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
        this.name = name != null ? name : "Unknown";
        this.species = species != null ? species : "Unknown";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name : "Unknown";
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species != null ? species : "Unknown";
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && Objects.equals(name, animal.name) && Objects.equals(species, animal.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species);
    }
}
