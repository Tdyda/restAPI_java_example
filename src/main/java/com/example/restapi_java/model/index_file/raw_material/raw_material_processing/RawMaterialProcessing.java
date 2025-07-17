package com.example.restapi_java.model.index_file.raw_material.raw_material_processing;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_processings")
@IdClass(RawMaterialProcessingId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialProcessing {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "index_id", referencedColumnName = "index_id", insertable = false, updatable = false),
            @JoinColumn(name = "supplier", referencedColumnName = "supplier", insertable = false, updatable = false)
    })
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "processing_id", referencedColumnName = "processing_id", insertable = false, updatable = false)
    private Processing processing;
}

