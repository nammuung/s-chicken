package com.groups.schicken;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    /*
        orgChart/orgChart
        spendingDetails/spendingDetails

     */


    @GetMapping("/")
    public String test(String path){
        return path;
    }

}
