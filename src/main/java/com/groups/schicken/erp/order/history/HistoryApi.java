package com.groups.schicken.erp.order.history;

import com.groups.schicken.common.vo.OrderVO;
import com.groups.schicken.common.vo.ResultVO;
import com.groups.schicken.erp.order.HeadOrderVO;
import com.groups.schicken.erp.supplier.SupplierVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/")
public class HistoryApi {
    private final HistoryService historyService;

    @GetMapping("history/receive/{id}")
    public ResponseEntity<?> getReceiveHistoryList (@PathVariable String id) throws Exception {
        try {
            String[] ids = id.split("-");
            HeadOrderVO orderVO = new HeadOrderVO();
            orderVO.setId(Long.parseLong(ids[0]));
            SupplierVO supplierVO = new SupplierVO();
            supplierVO.setId(Long.parseLong(ids[1]));
            orderVO.setSupplier(supplierVO);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), historyService.getReceiveHistoryList(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("history/release/{id}")
    public ResponseEntity<?> getReleaseHistoryList (@PathVariable Long id) throws Exception {
        try {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(id);
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), historyService.getReleaseHistoryList(orderVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }


}
