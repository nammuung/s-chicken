package com.groups.schicken.erp.supplier;

import com.groups.schicken.Employee.EmployeeVO;
import com.groups.schicken.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class SupplierAPI {
    private final SupplierService supplierService;

    @GetMapping("/supplier")
    public ResponseEntity<?> getSupplierList(SupplierVO supplierVO) throws Exception {
        System.out.println("supplierVO = " + supplierVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(),supplierService.getSupplierList(supplierVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러", null));
        }
    }
    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getSupplierList(@PathVariable Long id) throws Exception {
        SupplierVO supplierVO = new SupplierVO();
        supplierVO.setId(id);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(),supplierService.getSupplier(supplierVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러", null));
        }
    }

    @PostMapping("/supplier")
    public ResponseEntity<?> addSupplier(@AuthenticationPrincipal EmployeeVO employeeVO, SupplierVO supplierVO) throws Exception {
        supplierVO.setManager(employeeVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "저장 완료",supplierService.addSupplier(supplierVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러", null));
        }
    }

    @PutMapping("/supplier")
    public ResponseEntity<?> updateSupplier(SupplierVO supplierVO) throws Exception {
        System.out.println(supplierVO);
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "수정 완료",supplierService.updateSupplier(supplierVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러", null));
        }
    }

    @DeleteMapping("/supplier")
    public ResponseEntity<?> deleteSupplier(SupplierVO supplierVO) throws Exception {
        try {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "삭제 완료",supplierService.deleteSupplier(supplierVO)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultVO.res(HttpStatus.INTERNAL_SERVER_ERROR,"서버 에러", null));
        }
    }
}
