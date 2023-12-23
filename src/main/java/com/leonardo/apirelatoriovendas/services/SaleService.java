package com.leonardo.apirelatoriovendas.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.apirelatoriovendas.dtos.SaleRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SaleResponseDTO;
import com.leonardo.apirelatoriovendas.entities.Sale;
import com.leonardo.apirelatoriovendas.repositories.SaleRepository;
import com.leonardo.apirelatoriovendas.services.exceptions.ResourceNotFoundException;

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

    public SaleResponseDTO findById(Long id) {
        return new SaleResponseDTO(findEntity(id));
    }

    public Page<SaleResponseDTO> findAllSales(Pageable pageable) {
        return saleRepository.findAllSales(pageable).map(x -> new SaleResponseDTO(x));
    }

    public SaleResponseDTO updateSale(Long id, SaleRequestDTO saleRequestDTO) {
        Sale entity = findEntity(id);
        entity.setSeller(sellerService.findEntity(saleRequestDTO.getSellerId()));
        entity.setTotalValue(saleRequestDTO.getTotalValue());
        saleRepository.save(entity);
        return new SaleResponseDTO(entity);
    }

    public void deleteSaleById(Long id) {
        findEntity(id);
        saleRepository.deleteById(id);
    }

    private Sale convertDtoToEntity(SaleRequestDTO saleRequestDTO) {
        Sale entity = new Sale();
        entity.setSeller(sellerService.findEntity(saleRequestDTO.getSellerId()));
        entity.setTotalValue(saleRequestDTO.getTotalValue());
        return entity;
    }

    private Sale findEntity(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
    }

}
