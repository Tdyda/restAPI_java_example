package com.example.restapi_java.model.index_file.raw_material.raw_material_custom_attributes;

import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_custom_attributes")
@IdClass(RawMaterialTypeCustomAttribute.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RawMaterialTypeCustomAttribute {
    @Id
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Id
    @Column(name = "custom_attribute_id")
    private int customAttributeId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id", insertable = false, updatable = false),
    })
    private RawMaterialType rawMaterialType;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private CustomAttribute customAttribute;
}
