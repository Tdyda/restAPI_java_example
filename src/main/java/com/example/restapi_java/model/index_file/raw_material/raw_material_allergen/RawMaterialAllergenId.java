package com.example.restapi_java.model.index_file.raw_material.raw_material_allergen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialAllergenId {
    private String indexId;
    private String supplier;
    private int allergenId;
}
