package com.groups.schicken.franchise.api;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.service.FranchiseService;
import com.groups.schicken.util.Pager;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
@Slf4j
public class FranchiseApi {
    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("franchise")
    public ResponseEntity<?> getFranchise(Pager pager) throws Exception {
        System.out.println("pager = " + pager);
        return ResponseEntity.ok(franchiseService.getFranchiseList(pager));
    }

    @GetMapping("franchise/{id}")
    public ResponseEntity<?> getFranchise (@PathVariable("id") String id) throws Exception {
        FranchiseVO vo = new FranchiseVO();
        vo.setId(id);
        try {
            vo = franchiseService.getFranchise(vo);
        } catch (Exception e){
            log.info("{}", e);
        }
        if(vo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vo);
        }
    }
    }
