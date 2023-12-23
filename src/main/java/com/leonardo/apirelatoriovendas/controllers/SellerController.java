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

import com.leonardo.apirelatoriovendas.dtos.SellerRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SellerResponseDTO;
import com.leonardo.apirelatoriovendas.services.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<SellerResponseDTO> insertSeller(@RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.insertSeller(sellerRequestDTO));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SellerResponseDTO> findSellerById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SellerResponseDTO>> findAllSellers(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.findAllSellers(pageable));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable Long id,
            @RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSeller(id, sellerRequestDTO));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteSellerById(@PathVariable Long id) {
        sellerService.deleteSellerById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Seller removed successfully");
    }

}
