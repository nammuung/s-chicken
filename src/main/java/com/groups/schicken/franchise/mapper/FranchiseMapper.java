package com.groups.schicken.franchise.mapper;

import com.groups.schicken.franchise.object.FranchiseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FranchiseMapper {
    List<FranchiseVO> getFranchiseList();
    FranchiseVO getFranchise(FranchiseVO franchiseVO);
    int addFranchise(FranchiseVO franchiseVO);
    int updateFranchise(FranchiseVO franchiseVO);
    int deleteFranchise(FranchiseVO franchiseVO);
}
