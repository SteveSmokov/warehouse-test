package com.test.warehousetest.dao;

import com.test.warehousetest.enteties.IDayReport;
import com.test.warehousetest.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select t.day, sum(t.amount) as totalSum " +            "from (select date_trunc('day', o.\"timestamp\") as day, o.amount from orders o " +
            "where o.\"timestamp\" between :date_from and :date_to) t " +
            "group by t.day", nativeQuery = true)
    List<IDayReport> queryIncomeSumPerDay(@Param("date_from") @Temporal(TemporalType.TIMESTAMP) Date dateFrom,
                                          @Param("date_to") @Temporal(TemporalType.TIMESTAMP) Date dateTo);
}
