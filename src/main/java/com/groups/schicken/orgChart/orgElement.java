package com.groups.schicken.orgChart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class orgElement {
    private String id;
    private String parent;
    private String type;
    private String text;
}
