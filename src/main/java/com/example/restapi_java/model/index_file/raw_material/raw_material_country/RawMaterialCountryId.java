package com.example.restapi_java.model.index_file.raw_material.raw_material_country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialCountryId implements Serializable {
    private String indexId;
    private String supplier;
    private int countryId;
}
