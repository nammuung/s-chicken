package com.groups.schicken.notification;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDAO {
    int insertNotice(NotificationVO notification, List<String> receivers);
}
