package com.edvaldovitor.PlanetZoo;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Animal animal){

        String sql = "INSERT INTO animal (id, name, species) VALUES (? ,? ,?)";


        template.update(sql,animal.getId(),animal.getName(),animal.getSpecies());
    }

    public List<Animal> findAll(){
        return new ArrayList<Animal>();
    }

}
