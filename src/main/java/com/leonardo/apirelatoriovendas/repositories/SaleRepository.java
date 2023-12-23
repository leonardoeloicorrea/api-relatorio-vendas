package com.leonardo.apirelatoriovendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonardo.apirelatoriovendas.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj"
            + " FROM Sale obj")
    Page<Sale> findAllSales(Pageable pageable);

}
