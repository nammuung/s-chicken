package com.groups.schicken.franchise.service;

import com.groups.schicken.Employee.EmployeeService;
import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.franchise.mapper.FranchiseMapper;
import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.util.FileManager;
import com.groups.schicken.util.FileVO;
import com.groups.schicken.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseMapper franchiseMapper;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<FranchiseVO> getFranchiseList(Pager pager) throws Exception {
        return franchiseMapper.getFranchiseList(pager);
    }
    public FranchiseVO getFranchise(FranchiseVO franchiseVO) throws Exception {
        franchiseVO = franchiseMapper.getFranchise(franchiseVO);
        franchiseVO.getManager().setId(franchiseVO.getManagerId());
        franchiseVO.setManager(employeeService.userDetail(franchiseVO.getManager()));
        return franchiseVO;
    }
    public int addFranchise(FranchiseVO franchiseVO, MultipartFile[] attach) throws Exception {
        int result = 0;

        FileVO file = new FileVO();
        file.setTblId("101");
        if(!fileManager.uploadFile(attach[0], file)) return 0;
        System.out.println("file = " + file);
        franchiseVO.setContractId(file.getId());
        if(!fileManager.uploadFile(attach[1], file)) return 0;
        System.out.println("file = " + file);
        franchiseVO.setRegisterId(file.getId());

        franchiseVO.setPassword(passwordEncoder.encode(franchiseVO.getEmail()));//초기 비밀번호는 이메일
        result += franchiseMapper.addFranchise(franchiseVO);

        return result;
    }
    public int updateFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.updateFranchise(franchiseVO);
    }
    public int deleteFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.deleteFranchise(franchiseVO);
    }


    public int initPassword(FranchiseVO franchiseVO) throws Exception {
        franchiseVO.setPassword(passwordEncoder.encode(franchiseVO.getEmail()));
        return franchiseMapper.updateFranchise(franchiseVO);
    }


    public int updatePassword(FranchiseVO franchiseVO) throws  Exception {
        franchiseVO.setPassword(passwordEncoder.encode(franchiseVO.getPassword()));
        return franchiseMapper.updateFranchise(franchiseVO);
    }
}
