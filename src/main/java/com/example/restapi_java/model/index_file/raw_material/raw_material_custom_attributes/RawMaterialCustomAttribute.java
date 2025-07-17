package com.example.restapi_java.model.index_file.raw_material.raw_material_custom_attributes;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_custom_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RawMaterialCustomAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "custom_attribute_id")
    private CustomAttribute customAttribute;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "index_id", referencedColumnName = "index_id"),
            @JoinColumn(name = "supplier", referencedColumnName = "supplier")
    })
    private RawMaterial rawMaterial;

    @Column(name = "value")
    private String value;
}
