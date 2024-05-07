package com.groups.schicken.erp.supplier;

import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierMapper supplierMapper;
    private final FileManager fileManager;
    public List<SupplierVO> getSupplierList(SupplierVO supplierVO) throws Exception {
        return supplierMapper.getSupplierList(supplierVO);
    }

    public SupplierVO getSupplier(SupplierVO supplierVO) throws Exception {
        return supplierMapper.getSupplier(supplierVO);
    }

    public int addSupplier(SupplierVO supplierVO) throws Exception {
        return supplierMapper.addSupplier(supplierVO);
    }

    public int updateSupplier(SupplierVO supplierVO) throws Exception {
        return supplierMapper.updateSupplier(supplierVO);
    }

    public int deleteSupplier(SupplierVO supplierVO) throws Exception {
        return supplierMapper.deleteSupplier(supplierVO);
    }
}
