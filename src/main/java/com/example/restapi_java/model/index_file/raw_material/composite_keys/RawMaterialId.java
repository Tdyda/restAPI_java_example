package com.example.restapi_java.model.index_file.raw_material.composite_keys;

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

