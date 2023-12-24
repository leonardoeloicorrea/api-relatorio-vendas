package com.leonardo.apirelatoriovendas.repositories;

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

        @Query(nativeQuery = true, value = "SELECT tb_seller.name, COUNT(tb_sale.id) AS totalSales, COUNT(tb_sale.id) / AVG(EXTRACT(DAY FROM (tb_sale.date_of_sale - TO_DATE(:initialDate, 'YYYY-MM-DD')))) AS averageSales"
                        + " FROM tb_seller "
                        + " INNER JOIN tb_sale ON tb_seller.id = tb_sale.seller_id "
                        + " WHERE tb_sale.date_of_sale BETWEEN TO_DATE(:initialDate, 'YYYY-MM-DD') AND TO_DATE(:finalDate, 'YYYY-MM-DD')"
                        + " GROUP BY tb_seller.name")
        List<salesAverageOfTheSellerProjection> getSalesAverageOfTheSeller(@Param("initialDate") String initialDate,
                        @Param("finalDate") String finalDate);

}
