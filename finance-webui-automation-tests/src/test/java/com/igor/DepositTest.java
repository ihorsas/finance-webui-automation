package com.igor;


import com.igor.bo.DepositBO;
import com.igor.model.CapitalizationType;
import com.igor.model.Currency;
import com.igor.model.ReplenishmentType;
import com.igor.model.TermType;
import com.igor.utils.DateUtil;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.igor.assertion.DepositAssertion.assertExchangeRate;
import static com.igor.assertion.DepositAssertion.assertTotal;

public class DepositTest extends BaseTest{

    @Test()
    void test(){
        DateTime dateTime = DateUtil.getDateTimeByMonthAndDay(10, 15);
        DepositBO depositBO = new DepositBO();
        depositBO.setMainDepositInfo(Currency.USD, BigDecimal.valueOf(900.0), BigDecimal.valueOf(15.0));
        depositBO.setDateAndTerms(dateTime, 5, TermType.MONTHS);
        depositBO.setAdditionalInfo(BigDecimal.valueOf(5.0), ReplenishmentType.MONTHLY, CapitalizationType.MONTHLY);
        depositBO.submit();
        assertTotal(depositBO);
        assertExchangeRate(depositBO);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
