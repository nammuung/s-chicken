package com.groups.schicken.franchise.api;

import com.groups.schicken.franchise.object.FranchiseVO;
import com.groups.schicken.franchise.object.MessageVO;
import com.groups.schicken.franchise.object.ResultVO;
import com.groups.schicken.franchise.service.FranchiseService;
import com.groups.schicken.util.Pager;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/")
@Slf4j
public class FranchiseApi {
    @Autowired
    private FranchiseService franchiseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("franchise")
    public ResponseEntity<?> getFranchise(Pager pager) throws Exception {
        System.out.println("pager = " + pager);
        return ResponseEntity.ok(franchiseService.getFranchiseList(pager));
    }

    @GetMapping("franchise/{id}")
    public ResponseEntity<ResultVO<FranchiseVO>> getFranchise (@PathVariable("id") String id) throws Exception {
        FranchiseVO vo = new FranchiseVO();
        vo.setId(id);
        try {
            vo = franchiseService.getFranchise(vo);
        } catch (Exception e){
            log.info("{}", e);
        }
        if(vo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultVO.res(HttpStatus.BAD_REQUEST, "찾을 수 없는 가맹점.", null));
        } else {
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, HttpStatus.OK.toString(), vo));
        }
    }
    @PostMapping("/franchise/updatePassword")
    public ResponseEntity<ResultVO<String>> updatePassword(FranchiseVO franchiseVO, @RequestParam(value = "prevPassword", required = false) String prevPassword) throws Exception {
        System.out.println("franchiseVO = " + franchiseVO);
        FranchiseVO vo = franchiseService.getFranchise(franchiseVO);

        if(!passwordEncoder.matches(prevPassword, vo.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultVO.res(HttpStatus.BAD_REQUEST, "기존 비밀번호가 일치하지 않습니다.", null));
        }
        if(passwordEncoder.matches(franchiseVO.getPassword(), vo.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultVO.res(HttpStatus.BAD_REQUEST, "기존 비밀번호와 동일한 비밀번호입니다.", null));
        }
        int result = franchiseService.updatePassword(franchiseVO);
        if (result == 1){
            return ResponseEntity.ok(ResultVO.res(HttpStatus.OK, "비밀번호 변경이 되었습니다.", null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResultVO.res(HttpStatus.BAD_REQUEST, "비밀번호 변경에 실패했습니다.", null));
        }
    }

}
