package com.example.restapi_java.model.index_file.raw_material.raw_material_attachment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attachment_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_type_id")
    private int attachmentTypeId;

    @Column(name = "name", nullable = false)
    private String name;
}
