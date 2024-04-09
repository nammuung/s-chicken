package com.groups.schicken.department;

import lombok.Data;

@Data
public class DepartmentVO {
    private Long id;
    private String name;
    private String contactNumber;
    private Integer sort;
    private Long upperId;
    private Integer depth;
}
