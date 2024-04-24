package com.groups.schicken.erp.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groups.schicken.common.vo.CodeVO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVO {
    private String id;
    private String name;
    private String standard;
    private CodeVO category;
    private CodeVO unit;
    private Long sellPrice;
}
