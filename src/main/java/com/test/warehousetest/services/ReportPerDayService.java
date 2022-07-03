package com.test.warehousetest.services;

import com.test.warehousetest.dao.OrderRepository;
import com.test.warehousetest.enteties.IDayReport;
import com.test.warehousetest.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class ReportPerDayService implements ReportService {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private OrderRepository repository;

    @Override
    public Map<String, BigDecimal> executeBetween2Days(Date dateFrom, Date dateTo) {
        List<IDayReport> iDaysReport = repository.queryIncomeSumPerDay(dateFrom, dateTo);
        Map<String, BigDecimal> result = new TreeMap<>();
        if (iDaysReport!=null && !iDaysReport.isEmpty()){
            for (IDayReport day:
                    iDaysReport ) {
                result.put(simpleDateFormat.format(day.getDay()), day.getTotalSum());
            }
        }
        return result;
    }
}
