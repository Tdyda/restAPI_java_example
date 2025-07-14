package com.example.restapi_java.model.index_file.raw_material.raw_material_color;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private int kindId;

    @Column(name = "name")
    private String name;
}
