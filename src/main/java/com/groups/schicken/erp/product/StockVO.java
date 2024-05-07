package com.groups.schicken.erp.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockVO {
    private Long id;
    private Long quantity;
    private String expireDate;
    private ProductVO product;
    private String history;
    private String createDate;
}
