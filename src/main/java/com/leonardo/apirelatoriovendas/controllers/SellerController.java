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

}
