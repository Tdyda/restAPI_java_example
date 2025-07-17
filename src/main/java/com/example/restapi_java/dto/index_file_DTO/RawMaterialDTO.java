package com.example.restapi_java.dto.index_file_DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RawMaterialDTO {
    private String indexId;
    private String supplier;
    private String composition;
    private String preservativeSubstance;
    private String requiredProcessing;
    private String storage;
    private String typeName;
    private String indexName;
    private String status;
    private String productGroupName;

    private List<CustomAttributeDTO> customAttributes = new ArrayList<>();
    private Set<String> countries = new HashSet<>();
    private Set<String> allergenes = new HashSet<>();
    private List<AttachmentDTO> attachments = new ArrayList<>();
}

