package com.groups.schicken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    /*
        orgChart/orgChart
        spendingDetails/spendingDetails

     */


    @GetMapping("/")
    public String test(@RequestParam("path")String path){
        return path;
    }
    
//    @GetMapping("/")
//    public String test(){
//        return "home";
//    }

}
