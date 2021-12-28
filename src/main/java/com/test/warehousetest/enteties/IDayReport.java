package com.test.warehousetest.enteties;

import java.math.BigDecimal;
import java.util.Date;

public interface IDayReport {
    Date getDay();
    BigDecimal getTotalSum();
}
