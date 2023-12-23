package com.leonardo.apirelatoriovendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.apirelatoriovendas.dtos.SaleRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SaleResponseDTO;
import com.leonardo.apirelatoriovendas.services.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> insertSale(@RequestBody @Valid SaleRequestDTO saleRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.insertSale(saleRequestDTO));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SaleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
    }

}
