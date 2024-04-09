package com.groups.schicken.franchise.service;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.franchise.mapper.FranchiseMapper;
import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.util.FileManager;
import com.groups.schicken.util.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseMapper franchiseMapper;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private EmployeeService employeeService;

    public List<FranchiseVO> getFranchiseList() throws Exception {
        return franchiseMapper.getFranchiseList();
    }
    public FranchiseVO getFranchise(FranchiseVO franchiseVO) throws Exception {
        franchiseVO = franchiseMapper.getFranchise(franchiseVO);
        franchiseVO.getManager().setId(franchiseVO.getManagerId());
        franchiseVO.setManager(employeeService.userDetail(franchiseVO.getManager()));
        return franchiseVO;
    }
    public int addFranchise(FranchiseVO franchiseVO, MultipartFile[] attach) throws Exception {
        int result = 0;
        franchiseVO.setPassword(franchiseVO.getEmail()); //초기 비밀번호는 이메일
        result += franchiseMapper.addFranchise(franchiseVO);

        FileVO file = new FileVO();
        file.setParentId(franchiseVO.getId());
        file.setTblId("101");
        if(!fileManager.uploadFile(attach[0], file)) return 0;
        franchiseVO.setContractId(file.getId());
        if(!fileManager.uploadFile(attach[1], file)) return 0;
        franchiseVO.setRegisterId(file.getId());
        FranchiseVO updateVO = new FranchiseVO();
        updateVO.setId(franchiseVO.getId());
        updateVO.setContractId(franchiseVO.getContractId());
        updateVO.setRegisterId(franchiseVO.getRegisterId());
        updateFranchise(updateVO);
        return result;
    }
    public int updateFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.updateFranchise(franchiseVO);
    }
    public int deleteFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.deleteFranchise(franchiseVO);
    }
}
