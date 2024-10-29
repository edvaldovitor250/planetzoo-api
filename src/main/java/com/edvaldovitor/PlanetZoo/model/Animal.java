package com.edvaldovitor.PlanetZoo.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
@Data
public class Animal {
    private int id;
    private String name;
    private String species;
}
