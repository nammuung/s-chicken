package com.groups.schicken.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;

    /**
     * DepartmentVO를 department에 insert함
     * name과 contactNumber, upperId를 입력받고
     * id는 시퀀스로 자동생성
     * sort는 같은 부모의 가장 큰 sort + 1
     * @return 입력된 행의 수가 1이상이면 입력한 VO를 id와 sort를 추가해서 돌려줌 그렇지 않으면 null 리턴
     */
    @Transactional
    public DepartmentVO addDepartment(DepartmentVO department){
        department.setSort(departmentDAO.getLastSortByUpperId(department.getUpperId()) + 1);
        int result = 0;
        try{
            result = departmentDAO.addDepartment(department);
            if(result > 0){
                return department;
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return null;
    }

    public DepartmentVO getDepartment(DepartmentVO department) {
        return departmentDAO.getDepartment(department);
    }

    public List<DepartmentVO> getList() {
        return departmentDAO.getList();
    }

    public Boolean checkContactNumber(String contactNumber){
        return departmentDAO.isContactNumber(contactNumber) == 0;
    }
}
