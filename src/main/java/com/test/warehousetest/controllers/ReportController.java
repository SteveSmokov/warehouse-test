package com.test.warehousetest.controllers;

import com.test.warehousetest.services.ReportService;
import liquibase.repackaged.org.apache.commons.lang3.time.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class ReportController {
    @Autowired private ReportService reportService;

    @GetMapping(value = "/executeReport",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, BigDecimal> reportPerDay(@RequestParam(name="from", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date dateFrom,
                                                @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date dateTo){
        if (dateFrom==null) dateFrom=DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        if (dateTo==null) dateTo= DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), 1);
        log.info("Period from {} to {}", dateFrom, dateTo);
        return reportService.executeBetween2Days(dateFrom, dateTo);
    }
}
