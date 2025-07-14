package com.example.restapi_java.model.index_file.raw_material.raw_material_form;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "forms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private int formId;

    @Column(name = "name")
    private String name;
}
