package com.groups.schicken.common.vo;

import lombok.Data;

@Data
public class FileVO {
    private Long id;
    private String url;
    private String name;
    private String originName;
    private String extension;
    private Long parentId;
    private String tblId;
}
