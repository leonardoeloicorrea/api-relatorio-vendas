package com.leonardo.apirelatoriovendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.insertSale(saleRequestDTO));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SaleResponseDTO> findSaleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SaleResponseDTO>> findAllSales(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAllSales(pageable));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(@PathVariable Long id,
            @RequestBody @Valid SaleRequestDTO saleRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.updateSale(id, saleRequestDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sale removed successfully");
    }
}
