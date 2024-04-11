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
    public Integer addDepartment(DepartmentVO department){
        department.setSort(departmentDAO.getLastSortByUpperId(department.getUpperId()) + 1);
        return departmentDAO.addDepartment(department);
    }

    public DepartmentVO getDepartment(DepartmentVO department) {
        return departmentDAO.getDepartment(department);
    }

    public List<DepartmentVO> getList() {
        return departmentDAO.getList();
    }

    public Boolean checkContactNumber(String contactNumber, String except){
        if(except == null){
            except = "not except";
        }
        return departmentDAO.isContactNumber(contactNumber, except) == 0;
    }

    public Integer updateDepartment(DepartmentVO department) {
        Integer result = departmentDAO.updateDepartmentSort(department);
        return result + departmentDAO.updateDepartment(department);
    }

    public Integer deleteDepartment(DepartmentVO department) {
        Integer countChildren = departmentDAO.countChildren(department);
        if(countChildren == 0){
            return departmentDAO.deleteDepartment(department);
        }

        return 0;
    }
}
