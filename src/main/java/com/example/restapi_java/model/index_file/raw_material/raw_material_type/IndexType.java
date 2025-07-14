package com.example.restapi_java.model.index_file.raw_material.raw_material_type;

import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "index_type")
@IdClass(IndexTypeId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IndexType {

    @Id
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Id
    @Column(name = "type_id")
    private int typeId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id", insertable = false, updatable = false),
    })
    private RawMaterialType rawMaterialType;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private Type type;
}
