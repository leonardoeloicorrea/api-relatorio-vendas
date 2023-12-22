package com.leonardo.apirelatoriovendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.apirelatoriovendas.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByCpf(String cpf);

}
