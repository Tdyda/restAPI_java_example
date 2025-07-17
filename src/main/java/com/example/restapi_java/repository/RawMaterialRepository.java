package com.example.restapi_java.repository;

import com.example.restapi_java.model.index_file.raw_material.raw_material.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, String> {

    @Query(value = "SELECT rm.index_id, rm.supplier, rm.composition, rm.preservative_substance, rm.required_processing, rm.storage, " +
            "t.name AS type_name, i.index_name, i.status, pg.product_group_name, " +
            "ca.name AS custom_attribute_name, rmca.value AS custom_attribute_value, " +
            "c.name AS country_name, a.name AS allergen_name, rma.path AS attachment_path, at.name AS attachment_type " +
            "FROM raw_materials rm " +
            "JOIN types t ON rm.type_id = t.type_id " +
            "JOIN indexes i ON rm.index_id = i.index_id " +
            "JOIN product_groups pg ON i.product_group_id = pg.product_group_id " +
            "LEFT JOIN raw_material_custom_attributes rmca ON rm.index_id = rmca.index_id AND rm.supplier = rmca.supplier " +
            "LEFT JOIN custom_attributes ca ON rmca.custom_attribute_id = ca.custom_attribute_id " +
            "LEFT JOIN raw_material_country_of_origins rmcoo ON rm.index_id = rmcoo.index_id AND rm.supplier = rmcoo.supplier " +
            "LEFT JOIN countries c ON rmcoo.country_id = c.country_id " +
            "LEFT JOIN raw_material_allergenes rmae ON rm.index_id = rmae.index_id AND rm.supplier = rmae.supplier " +
            "LEFT JOIN allergenes a ON rmae.allergen_id = a.allergen_id " +
            "LEFT JOIN raw_material_attachments rma ON rm.index_id = rma.index_id AND rm.supplier = rma.supplier " +
            "LEFT JOIN attachment_types at ON rma.attachment_type_id = at.attachment_type_id " +
            "WHERE rm.index_id = :indexId AND rm.supplier = :supplier", nativeQuery = true)
    List<Object[]> findRawMaterialWithDetails(@Param("indexId") String indexId, @Param("supplier") String supplier);
}

