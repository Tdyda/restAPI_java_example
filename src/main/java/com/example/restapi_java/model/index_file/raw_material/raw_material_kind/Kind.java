package com.example.restapi_java.model.index_file.raw_material.raw_material_kind;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kinds")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_id")
    private int kindId;

    @Column(name = "name")
    private String name;
}
