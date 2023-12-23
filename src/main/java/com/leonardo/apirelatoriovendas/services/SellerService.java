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
        validateUniqueDate(sellerRequestDTO);
        Seller entity = convertDtoToEntity(sellerRequestDTO);
        sellerRepository.save(entity);
        return new SellerResponseDTO(entity);
    }

    public SellerResponseDTO findById(Long id) {
        return new SellerResponseDTO(findEntity(id));
    }

    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO) {
        findEntity(id);
        validateUniqueDateExcludingId(id, sellerRequestDTO);
        Seller entity = findEntity(id);
        entity.setName(sellerRequestDTO.getName());
        entity.setCpf(sellerRequestDTO.getCpf());
        entity.setDateOfBirth(sellerRequestDTO.getDateOfBirth());
        sellerRepository.save(entity);
        return new SellerResponseDTO(entity);
    }

    public Seller convertDtoToEntity(SellerRequestDTO sellerDTO) {
        Seller entity = new Seller();
        entity.setName(sellerDTO.getName());
        entity.setCpf(sellerDTO.getCpf());
        entity.setDateOfBirth(sellerDTO.getDateOfBirth());
        return entity;
    }

    public void validateUniqueDate(SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByCpf(sellerRequestDTO.getCpf())) {
            throw new DatabaseException("The entered cpf already exists");
        }
    }

    public void validateUniqueDateExcludingId(Long id, SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByCpfExcludingId(id, sellerRequestDTO.getCpf()).isPresent()) {
            throw new DatabaseException("The entered cpf already exists");
        }
    }

    private Seller findEntity(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
    }

}
