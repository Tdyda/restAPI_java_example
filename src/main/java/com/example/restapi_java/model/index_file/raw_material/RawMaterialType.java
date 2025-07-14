package com.example.restapi_java.model.index_file.raw_material;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Column(name = "raw_material_type_name")
    private String rawMaterialTypeName;
}