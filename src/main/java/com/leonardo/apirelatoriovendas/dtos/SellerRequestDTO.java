package com.leonardo.apirelatoriovendas.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @NotBlank(message = "Required field")
    private String name;

    @Size(min = 11, max = 11, message = "The cpf must have 11 characters")
    @NotBlank(message = "Required field")
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "Required field")
    private LocalDate dateOfBirth;

}
