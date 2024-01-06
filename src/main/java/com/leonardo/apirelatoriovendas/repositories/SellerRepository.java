package com.leonardo.apirelatoriovendas.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leonardo.apirelatoriovendas.entities.Seller;
import com.leonardo.apirelatoriovendas.projections.salesAverageOfTheSellerProjection;

public interface SellerRepository extends JpaRepository<Seller, Long> {

        boolean existsByCpf(String cpf);

        @Query("SELECT obj"
                        + " FROM Seller obj"
                        + " WHERE obj.id != :id AND obj.cpf = :cpf")
        Optional<Seller> existsByCpfExcludingId(@Param("id") Long id, @Param("cpf") String cpf);

        @Query("SELECT obj"
                        + " FROM Seller obj")
        Page<Seller> findAllSellers(Pageable pageable);

        @Query(nativeQuery = true, value = "SELECT tb_seller.name, COUNT(tb_sale.seller_id) AS totalSales, SUM(tb_sale.total_value) AS totalSalesValue, ROUND(CAST(COUNT(tb_sale.seller_id) AS DECIMAL) / :period, 2) As averageSales "
                        + "FROM tb_seller "
                        + "INNER JOIN tb_sale ON tb_seller.id = tb_sale.seller_id "
                        + "WHERE tb_sale.date_of_sale BETWEEN :initialDate AND :finalDate "
                        + "GROUP BY tb_seller.name")
        List<salesAverageOfTheSellerProjection> getSalesAverageOfTheSeller(@Param("initialDate") LocalDate initialDate,
                        @Param("finalDate") LocalDate finalDate, @Param("period") Integer period);

}
