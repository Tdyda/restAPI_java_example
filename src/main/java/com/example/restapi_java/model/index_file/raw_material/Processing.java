package com.example.restapi_java.model.index_file.raw_material;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Processing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processing_id")
    private int processingId;

    @Column(name = "processing_name", nullable = false)
    private String processingName;
}

