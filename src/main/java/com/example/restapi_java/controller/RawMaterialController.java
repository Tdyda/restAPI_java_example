package com.example.restapi_java.controller;

import com.example.restapi_java.dto.index_file_DTO.RawMaterialDTO;
import com.example.restapi_java.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @GetMapping("/{indexId}/{supplier}")
    public ResponseEntity<RawMaterialDTO> getRawMaterial(@PathVariable String indexId, @PathVariable String supplier) {
        RawMaterialDTO dto = rawMaterialService.getRawMaterial(indexId, supplier);
        return ResponseEntity.ok(dto);
    }
}

