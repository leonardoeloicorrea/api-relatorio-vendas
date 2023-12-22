package com.leonardo.apirelatoriovendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.apirelatoriovendas.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
