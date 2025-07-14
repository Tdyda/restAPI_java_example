package com.example.restapi_java.model.index_file.raw_material.raw_material_custom_attributes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "custom_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private int kindId;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
