package com.groups.schicken.Employee;

import lombok.Data;

@Data
public class EmployeeProfileVO {
    private String id;
    private String name;
    private String profileImg;
    private String departmentName;
    private String phoneNumber;

    public String getProfileImg() {
        return profileImg == null ? "/img/기본.jpg" : profileImg;
    }
}
