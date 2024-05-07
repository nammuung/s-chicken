package com.groups.schicken.file;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TestMapper {
    @Insert("INSERT INTO menu (menu, price) VALUES (#{menu},#{price})")
    void insertMenu(Map<?,?> map);
}
