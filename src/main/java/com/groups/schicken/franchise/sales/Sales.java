package com.groups.schicken.franchise.sales;

import com.groups.schicken.franchise.FranchiseVO;
import com.groups.schicken.franchise.menu.Menu;
import lombok.Data;

import java.util.List;

@Data
public class Sales {
    private Long id;
    private Integer price;
    private String salesDate;
    private FranchiseVO franchise;
    private List<Detail> details;

    @Data
    public static class Detail{
        private Sales sales;
        private Menu menu;
        private Integer quantity;
        private Integer price;
    }

}
