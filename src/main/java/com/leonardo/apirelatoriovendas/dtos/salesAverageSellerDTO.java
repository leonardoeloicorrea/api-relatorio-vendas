package com.leonardo.apirelatoriovendas.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer TotalSales;
    private BigDecimal totalSalesValue;
    private BigDecimal averageSales;

    public salesAverageSellerDTO(salesAverageOfTheSellerProjection entity) {
        this.name = entity.getName();
        this.TotalSales = entity.getTotalSales();
        this.totalSalesValue = entity.getTotalSalesValue();
        this.averageSales = entity.getAverageSales();
    }

}
