package com.example.restapi_java.model.index_file.raw_material;

import com.example.restapi_java.model.index_file.raw_material.product_group.ProductGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "indexes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Index {

    @Id
    @Column(name = "index_id")
    private String indexId;

    @Column(name = "index_name", nullable = false)
    private String indexName;

    @ManyToOne
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroup;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToMany
    @JoinTable(
            name = "substitute",
            joinColumns = @JoinColumn(name = "index_id"),
            inverseJoinColumns = @JoinColumn(name = "substitute_id")
    )
    private List<Index> substitutes;
}
