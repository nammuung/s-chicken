package com.groups.schicken.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrganizationVO {
    private String id;
    private String parent;
    private String type;
    private String text;
    private Long depth;
    private String sort;
}
