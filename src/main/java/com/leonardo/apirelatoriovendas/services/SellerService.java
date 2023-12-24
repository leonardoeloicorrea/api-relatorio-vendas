package com.leonardo.apirelatoriovendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.apirelatoriovendas.dtos.SellerRequestDTO;
import com.leonardo.apirelatoriovendas.dtos.SellerResponseDTO;
import com.leonardo.apirelatoriovendas.dtos.salesAverageSellerDTO;
import com.leonardo.apirelatoriovendas.entities.Seller;
import com.leonardo.apirelatoriovendas.projections.salesAverageOfTheSellerProjection;
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

    public Page<SellerResponseDTO> findAllSellers(Pageable pageable) {
        return sellerRepository.findAllSellers(pageable).map(x -> new SellerResponseDTO(x));
    }

    public List<salesAverageSellerDTO> listSalesAveragesOfSellers(String initialDate, String finalDate) {
        List<salesAverageOfTheSellerProjection> list = this.sellerRepository.getSalesAverageOfTheSeller(initialDate,
                finalDate);
        return list.stream().map(x -> new salesAverageSellerDTO(x)).toList();
    }

    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO sellerRequestDTO) {
        validateUniqueDateExcludingId(id, sellerRequestDTO);
        Seller entity = findEntity(id);
        entity.setName(sellerRequestDTO.getName());
        entity.setCpf(sellerRequestDTO.getCpf());
        entity.setDateOfBirth(sellerRequestDTO.getDateOfBirth());
        sellerRepository.save(entity);
        return new SellerResponseDTO(entity);
    }

    public void deleteSellerById(Long id) {
        findEntity(id);
        sellerRepository.deleteById(id);
    }

    private Seller convertDtoToEntity(SellerRequestDTO sellerDTO) {
        Seller entity = new Seller();
        entity.setName(sellerDTO.getName());
        entity.setCpf(sellerDTO.getCpf());
        entity.setDateOfBirth(sellerDTO.getDateOfBirth());
        return entity;
    }

    private void validateUniqueDate(SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByCpf(sellerRequestDTO.getCpf())) {
            throw new DatabaseException("The entered cpf already exists");
        }
    }

    private void validateUniqueDateExcludingId(Long id, SellerRequestDTO sellerRequestDTO) {
        if (sellerRepository.existsByCpfExcludingId(id, sellerRequestDTO.getCpf()).isPresent()) {
            throw new DatabaseException("The entered cpf already exists");
        }
    }

    protected Seller findEntity(Long id) {
        return sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
    }

}
