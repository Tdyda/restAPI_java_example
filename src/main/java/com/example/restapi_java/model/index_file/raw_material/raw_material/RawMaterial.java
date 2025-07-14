package com.example.restapi_java.model.index_file.raw_material.raw_material;
import com.example.restapi_java.model.index_file.raw_material.Index;
import com.example.restapi_java.model.index_file.raw_material.processing.Processing;
import com.example.restapi_java.model.index_file.raw_material.raw_material_allergen.RawMaterialAllergen;
import com.example.restapi_java.model.index_file.raw_material.raw_material_country.RawMaterialCountryOfOrigin;
import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "raw_materials")
@IdClass(RawMaterialId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterial {

    @Id
    @Column(name = "index_id")
    private String indexId;

    @Id
    @Column(name = "supplier")
    private String supplier;

    @ManyToOne
    @JoinColumn(name = "index_id", referencedColumnName = "index_id", insertable = false, updatable = false)
    private Index index;

    @ManyToOne
    @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id")
    private RawMaterialType rawMaterialType;

    @Column(name = "composition")
    private String composition;

    @ManyToOne
    @JoinColumn(name = "processing_id", referencedColumnName = "processing_id")
    private Processing processing;

    @OneToMany(mappedBy = "rawMaterial")
    private List<RawMaterialCountryOfOrigin> countryOfOrigins;

    @OneToMany(mappedBy = "rawMaterial")
    private List<RawMaterialAllergen> allergens;

    @Column(name = "storage")
    private String storage;

    @Column(name = "preservative_substance")
    private String preservativeSubstance;

    @Column(name = "required_processing")
    private String requiredProcessing;
}

