package com.groups.schicken.Employee;

import com.groups.schicken.common.util.DateManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class PayUpdater {

    private final EmployeeDAO employeeDAO;

    @Scheduled(cron = "1 0 10 25 * *", zone = "Asia/Seoul")
    public void updatePaystub(){
        String yearMonth = DateManager.getTodayDateTime("yyyy-MM-dd");
        updatePaystub(yearMonth);
    }

    public void updatePaystub(String date){
        try {
            int result = employeeDAO.updatePaystub(date);
            log.info("{}명의 데이터가 Salary 테이블에 추가 되었습니다.", result);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
