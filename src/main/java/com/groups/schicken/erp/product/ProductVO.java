package com.groups.schicken.erp.product;

import com.groups.schicken.common.vo.CodeVO;
import lombok.Data;

@Data
public class ProductVO {
    private String id;
    private String name;
    private String standard;
    private CodeVO category;
    private CodeVO unit;
    private Long sellPrice;
}
