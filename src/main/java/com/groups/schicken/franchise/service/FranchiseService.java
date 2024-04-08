package com.groups.schicken.franchise.service;

import com.groups.schicken.franchise.mapper.FranchiseMapper;
import com.groups.schicken.franchise.object.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseMapper franchiseMapper;
    public List<FranchiseVO> getFranchiseList() throws Exception {
        return franchiseMapper.getFranchiseList();
    }
    public FranchiseVO getFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.getFranchise(franchiseVO);
    }
    public int addFranchise(FranchiseVO franchiseVO) throws Exception {
        franchiseVO.setPassword(franchiseVO.getEmail());
        return franchiseMapper.addFranchise(franchiseVO);
    }
    public int updateFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.updateFranchise(franchiseVO);
    }
    public int deleteFranchise(FranchiseVO franchiseVO) throws Exception {
        return franchiseMapper.deleteFranchise(franchiseVO);
    }
}
