package com.groups.schicken.Employee;
import com.groups.schicken.common.util.NumberFormatter;
import lombok.Data;

@Data
public class PaystubVO {
    String employeeId;
    String yearMonth;
    Long basePay;
    Long bonusSum;
    boolean payed = true;

    public String getSum(){
        return NumberFormatter.formatting(bonusSum + basePay);
    }

    public String getBasePayByString(){
        return NumberFormatter.formatting(basePay);
    }

    public String getBonusSumByString(){
        return NumberFormatter.formatting(bonusSum);
    }
}
