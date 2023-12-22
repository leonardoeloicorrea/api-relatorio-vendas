package com.leonardo.apirelatoriovendas.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Required field")
    private Long sellerId;

    @NotNull(message = "Required field")
    @Positive(message = "The total Value must be greater 0")
    private Double totalValue;

}
