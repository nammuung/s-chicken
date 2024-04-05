package com.groups.schicken.organization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrgElementVO {
    private String id;
    private String parent;
    private String type;
    private String text;
}
