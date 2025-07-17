package com.example.restapi_java.model.index_file.raw_material.raw_material;

import lombok.NoArgsConstructor;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialId implements Serializable {
    private String indexId;
    private String supplier;
}

