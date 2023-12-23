package com.leonardo.apirelatoriovendas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leonardo.apirelatoriovendas.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByCpf(String cpf);

    @Query("SELECT obj"
            + " FROM Seller obj"
            + " WHERE obj.id != :id AND obj.cpf = :cpf")
    Optional<Seller> existsByCpfExcludingId(@Param("id") Long id, @Param("cpf") String cpf);

}
