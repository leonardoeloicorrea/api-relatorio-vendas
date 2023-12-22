package com.leonardo.apirelatoriovendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.apirelatoriovendas.dtos.SellerRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SellerResponseDTO;
import com.leonardo.apirelatoriovendas.entities.Seller;
import com.leonardo.apirelatoriovendas.repositories.SellerRepository;
import com.leonardo.apirelatoriovendas.services.exceptions.DatabaseException;
import com.leonardo.apirelatoriovendas.services.exceptions.ResourceNotFoundException;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public SellerResponseDTO insertSeller(SellerRequestDTO sellerRequestDTO) {
        Seller entity = convertDtoToEntity(sellerRequestDTO);
        validateUniqueDate(entity);
        sellerRepository.save(entity);
        return new SellerResponseDTO(entity);
    }

    public SellerResponseDTO findById(Long id) {
        return new SellerResponseDTO(findEntity(id));
    }

    public Seller convertDtoToEntity(SellerRequestDTO sellerDTO) {
        Seller entity = new Seller();
        entity.setName(sellerDTO.getName());
        entity.setCpf(sellerDTO.getCpf());
        entity.setDateOfBirth(sellerDTO.getDateOfBirth());
        return entity;
    }

    public void validateUniqueDate(Seller entity) {
        if (sellerRepository.existsByCpf(entity.getCpf())) {
            throw new DatabaseException("The entered cpf already exists");
        }
    }

    private Seller findEntity(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
    }

}
