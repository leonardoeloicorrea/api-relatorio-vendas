package com.leonardo.apirelatoriovendas.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.leonardo.apirelatoriovendas.projections.salesAverageOfTheSellerProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class salesAverageSellerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer totalSales;
    private BigDecimal averageSales;

    public salesAverageSellerDTO(salesAverageOfTheSellerProjection entity) {
        this.name = entity.getName();
        this.totalSales = entity.getTotalSales();
        this.averageSales = BigDecimal.valueOf(entity.getAverageSales()).setScale(2, RoundingMode.HALF_UP);
    }

}
