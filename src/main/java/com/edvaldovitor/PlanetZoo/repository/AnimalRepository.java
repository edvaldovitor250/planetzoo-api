package com.edvaldovitor.PlanetZoo.repository;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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


        int rows = template.update(sql,animal.getId(),animal.getName(),animal.getSpecies());

        System.out.println(rows );


    }

    public List<Animal> findAll(){

        String sql = "select * from animal";

        RowMapper<Animal> mapper = new RowMapper<Animal>() {
            @Override
            public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {

                Animal a = new Animal();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setSpecies(rs.getString(3));


                return a;
            }
        };

       List<Animal> animals =  template.query(sql,mapper);

        return animals;
    }

}
