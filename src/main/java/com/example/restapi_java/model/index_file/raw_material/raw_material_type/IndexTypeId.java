package com.example.restapi_java.model.index_file.raw_material.raw_material_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexTypeId implements Serializable {
    private int rawMaterialTypeId;
    private int typeId;
}