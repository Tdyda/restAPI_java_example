package com.example.restapi_java.model.index_file.raw_material.raw_material_allergen;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="raw_material_allergenes")
@IdClass(RawMaterialAllergenId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialAllergen {
    @Id
    @Column(name = "index_id")
    private String indexId;

    @Id
    @Column(name = "supplier")
    private String supplier;

    @Id
    @Column(name = "allergen_id")
    private int allergenId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "index_id", referencedColumnName = "index_id", insertable = false, updatable = false),
            @JoinColumn(name = "supplier", referencedColumnName = "supplier", insertable = false, updatable = false)
    })
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "allergen_id", referencedColumnName = "allergen_id")
    private Allergen allergen;
}
