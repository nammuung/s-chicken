package com.groups.schicken.util;

import lombok.Data;

@Data
public class FileVO {
    private Long id;
    private String url;
    private String name;
    private String origin_name;
    private String extension;
    private Long parent_id;
    private String tbl_id;
}
