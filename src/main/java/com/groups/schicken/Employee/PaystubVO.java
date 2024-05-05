package com.groups.schicken.Employee;
import com.groups.schicken.common.util.NumberFormatter;
import com.groups.schicken.document.DocumentVO;
import lombok.Data;

import java.util.List;

@Data
public class PaystubVO {
    String employeeId;
    String yearMonth;
    Long basePay;
    Long bonusSum;
    boolean payed = true;
    List<DocumentVO> bonusReason;

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
