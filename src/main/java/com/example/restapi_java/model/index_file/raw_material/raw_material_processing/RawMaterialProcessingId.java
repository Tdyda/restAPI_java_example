package com.example.restapi_java.model.index_file.raw_material.raw_material_processing;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialProcessingId implements Serializable {
    private RawMaterial rawMaterial;
    private Processing processing;
}
