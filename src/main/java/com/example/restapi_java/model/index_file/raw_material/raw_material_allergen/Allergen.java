package com.example.restapi_java.model.index_file.raw_material.raw_material_allergen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "allergenes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergen_id")
    private int allergenId;

    @Column(name = "name")
    private String name;
}
