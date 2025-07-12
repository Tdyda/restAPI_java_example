package com.example.restapi_java.model.index_file.raw_material;

import com.example.restapi_java.model.index_file.raw_material.composite_keys.RawMaterialAttachmentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="raw_material_country_of_origins")
@IdClass(RawMaterialCountryOfOrigin.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialCountryOfOrigin {

    @Id
    @Column(name = "index_id")
    private String indexId;

    @Id
    @Column(name = "supplier")
    private String supplier;

    @Id
    @Column(name = "country_id")
    private int countryId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "index_id", referencedColumnName = "index_id", insertable = false, updatable = false),
            @JoinColumn(name = "supplier", referencedColumnName = "supplier", insertable = false, updatable = false)
    })
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", insertable = false, updatable = false)
    private Country country;
}
