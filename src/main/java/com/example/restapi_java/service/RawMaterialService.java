package com.example.restapi_java.service;

import com.example.restapi_java.dto.index_file_DTO.AttachmentDTO;
import com.example.restapi_java.dto.index_file_DTO.CustomAttributeDTO;
import com.example.restapi_java.dto.index_file_DTO.RawMaterialDTO;
import com.example.restapi_java.repository.RawMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository rawMaterialRepository;

    public RawMaterialDTO getRawMaterial(String indexId, String supplier) {
        List<Object[]> results = rawMaterialRepository.findRawMaterialWithDetails(indexId, supplier);

        if (results.isEmpty()) {
            throw new EntityNotFoundException("Raw material not found");
        }

        RawMaterialDTO dto = null;

        for (Object[] row : results) {
            if (dto == null) {
                dto = new RawMaterialDTO();
                dto.setIndexId((String) row[0]);
                dto.setSupplier((String) row[1]);
                dto.setComposition((String) row[2]);
                dto.setPreservativeSubstance((String) row[3]);
                dto.setRequiredProcessing((String) row[4]);
                dto.setStorage((String) row[5]);
                dto.setTypeName((String) row[6]);
                dto.setIndexName((String) row[7]);
                dto.setStatus((String) row[8]);
                dto.setProductGroupName((String) row[9]);
            }

            String customAttrName = (String) row[10];
            String customAttrValue = (String) row[11];
            String countryName = (String) row[12];
            String allergenName = (String) row[13];
            String attachmentPath = (String) row[14];
            String attachmentType = (String) row[15];

            if (customAttrName != null && customAttrValue != null) {
                dto.getCustomAttributes().add(new CustomAttributeDTO(customAttrName, customAttrValue));
            }

            if (countryName != null) {
                dto.getCountries().add(countryName);
            }

            if (allergenName != null) {
                dto.getAllergenes().add(allergenName);
            }

            if (attachmentPath != null && attachmentType != null) {
                dto.getAttachments().add(new AttachmentDTO(attachmentType, attachmentPath));
            }
        }

        return dto;
    }
}

