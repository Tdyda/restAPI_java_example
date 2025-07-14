package com.example.restapi_java.model.index_file.raw_material.raw_material_form;

import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import com.example.restapi_java.model.index_file.raw_material.raw_material_kind.Kind;
import com.example.restapi_java.model.index_file.raw_material.raw_material_kind.RawMaterialKindId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_forms")
@IdClass(RawMaterialFormId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RawMaterialForm {
    @Id
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Id
    @Column(name = "form_id")
    private int formId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id", insertable = false, updatable = false),
    })
    private RawMaterialType rawMaterialType;

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    private Form form;
}
