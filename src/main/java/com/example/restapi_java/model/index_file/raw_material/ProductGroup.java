package com.example.restapi_java.model.index_file.raw_material;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {
    @Id
    @Column(name = "product_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_group_name", nullable = false)
    private String productGroupName;

    @OneToMany(mappedBy = "productGroup")
    private List<Index> indexes;
}
