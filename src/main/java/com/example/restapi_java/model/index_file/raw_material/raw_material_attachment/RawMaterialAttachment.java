package com.example.restapi_java.model.index_file.raw_material.raw_material_attachment;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="raw_material_attachments")
@IdClass(RawMaterialAttachmentId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterialAttachment {
    @Id
    @Column(name = "index_id")
    private String indexId;

    @Id
    @Column(name = "supplier")
    private String supplier;

    @Id
    @Column(name = "attachment_type_id")
    private int attachmentTypeId;

    @Column(name = "path")
    private String path;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "index_id", referencedColumnName = "index_id", insertable = false, updatable = false),
            @JoinColumn(name = "supplier", referencedColumnName = "supplier", insertable = false, updatable = false)
    })
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "attachment_type_id", referencedColumnName = "attachment_type_id", insertable = false, updatable = false)
    private AttachmentType attachmentType;
}
