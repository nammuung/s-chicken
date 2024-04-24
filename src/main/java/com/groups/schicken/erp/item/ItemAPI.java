package com.groups.schicken.erp.item;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/items")
@RequiredArgsConstructor
public class ItemAPI {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getItemList(ItemVO itemVO) throws Exception {
        System.out.println("itemVO = " + itemVO);
        try {
            System.out.println(itemService.getItemList(itemVO));
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), itemService.getItemList(itemVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) throws Exception {
        ItemVO itemVO = new ItemVO();
        itemVO.setId(id);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), itemService.getItem(itemVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PostMapping
    public ResponseEntity<?> addItem(@AuthenticationPrincipal EmployeeVO employeeVO, ItemVO itemVO) throws Exception {
        itemVO.setWriter(employeeVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "제품 추가 완료", itemService.addItem(itemVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateItem(ItemVO itemVO) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "제품 수정 완료", itemService.updateItem(itemVO)));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류", null));
        }
    }
}
