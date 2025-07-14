package com.example.restapi_java.model.index_file.raw_material.raw_material_kind;

import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import com.example.restapi_java.model.index_file.raw_material.raw_material_type.IndexTypeId;
import com.example.restapi_java.model.index_file.raw_material.raw_material_type.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_kinds")
@IdClass(RawMaterialKindId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RawMaterialKind {

    @Id
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Id
    @Column(name = "kind_id")
    private int kindId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id", insertable = false, updatable = false),
    })
    private RawMaterialType rawMaterialType;

    @ManyToOne
    @JoinColumn(name = "kind_id", referencedColumnName = "kind_id")
    private Kind kind;
}
