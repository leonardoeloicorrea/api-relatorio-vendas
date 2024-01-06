package com.leonardo.apirelatoriovendas.projections;

import java.math.BigDecimal;

public interface salesAverageOfTheSellerProjection {

    String getName();

    Integer getTotalSales();

    BigDecimal getTotalSalesValue();

    BigDecimal getAverageSales();

}
