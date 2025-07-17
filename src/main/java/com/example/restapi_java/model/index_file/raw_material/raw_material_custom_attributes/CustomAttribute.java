package com.example.restapi_java.model.index_file.raw_material.raw_material_custom_attributes;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "custom_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custom_attribute_id")
    private int customAttributeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @OneToMany(mappedBy = "customAttribute")
    private List<RawMaterialCustomAttribute> rawMaterialCustomAttributes;
}
