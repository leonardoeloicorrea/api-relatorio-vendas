package com.leonardo.apirelatoriovendas.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.apirelatoriovendas.dtos.SaleRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SaleResponseDTO;
import com.leonardo.apirelatoriovendas.entities.Sale;
import com.leonardo.apirelatoriovendas.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SellerService sellerService;

    public SaleResponseDTO insertSale(SaleRequestDTO saleRequestDTO) {
        Sale entity = convertDtoToEntity(saleRequestDTO);
        entity.setDateOfSale(LocalDateTime.now(ZoneId.of("UTC")));
        saleRepository.save(entity);
        return new SaleResponseDTO(entity);
    }

    private Sale convertDtoToEntity(SaleRequestDTO saleRequestDTO) {
        Sale entity = new Sale();
        entity.setSeller(sellerService.findEntity(saleRequestDTO.getSellerId()));
        entity.setTotalValue(saleRequestDTO.getTotalValue());
        return entity;
    }

}
