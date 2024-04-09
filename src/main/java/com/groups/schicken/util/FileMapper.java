package com.groups.schicken.util;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int uploadFile(FileVO fileVO) throws Exception;
    int deleteFile(FileVO fileVO) throws Exception;
}
