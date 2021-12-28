package com.test.warehousetest.services;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface ReportService {
    Map<String, BigDecimal> executeBetween2Days(Date dateFrom, Date dateTo);
}
