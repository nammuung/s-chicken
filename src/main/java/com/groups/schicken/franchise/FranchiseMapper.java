package com.groups.schicken.franchise;

import com.groups.schicken.common.vo.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FranchiseMapper {
    List<FranchiseVO> getFranchiseList(Pager pager);
    FranchiseVO getFranchise(FranchiseVO franchiseVO);
    int addFranchise(FranchiseVO franchiseVO);
    int updateFranchise(FranchiseVO franchiseVO);
    int deleteFranchise(FranchiseVO franchiseVO);
}
