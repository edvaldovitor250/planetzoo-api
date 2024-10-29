package com.edvaldovitor.PlanetZoo;

import com.edvaldovitor.PlanetZoo.model.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AnimalRepository {



    public void save(Animal animal){
        System.out.println("add");
    }

    public List<Animal> findAll(){
        return new ArrayList<Animal>();
    }

}
