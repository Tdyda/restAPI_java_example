package com.example.restapi_java.model.index_file.raw_material.raw_material_color;

import com.example.restapi_java.model.index_file.raw_material.RawMaterialType;
import com.example.restapi_java.model.index_file.raw_material.raw_material_kind.Kind;
import com.example.restapi_java.model.index_file.raw_material.raw_material_kind.RawMaterialKindId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "raw_material_colors")
@IdClass(RawMaterialColorId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RawMaterialColor {
    @Id
    @Column(name = "raw_material_type_id")
    private int rawMaterialTypeId;

    @Id
    @Column(name = "color_id")
    private int colorId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "raw_material_type_id", referencedColumnName = "raw_material_type_id", insertable = false, updatable = false),
    })
    private RawMaterialType rawMaterialType;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private Color color;
}
