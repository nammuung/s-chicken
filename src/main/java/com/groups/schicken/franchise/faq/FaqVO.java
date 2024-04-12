package com.groups.schicken.franchise.faq;

import lombok.Data;

@Data
public class FaqVO {
    private Long id;
    private String title;
    private String content;
    private String writeDate;
    private String modifyDate;
    private Boolean isImportant;
    private Boolean isDeleted;
    private FaqVO preFaq;
    private FaqVO nextFaq;
}
