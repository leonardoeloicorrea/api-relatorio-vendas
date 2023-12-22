package com.leonardo.apirelatoriovendas.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardo.apirelatoriovendas.entities.Sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Double totalValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dateOfSale;
    private Long sellerId;
    private String sellerName;

    public SaleResponseDTO(Sale entity) {
        this.id = entity.getId();
        this.totalValue = entity.getTotalValue();
        this.dateOfSale = entity.getDateOfSale();
        this.sellerId = entity.getSeller().getId();
        this.sellerName = entity.getSeller().getName();
    }

}
