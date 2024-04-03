package com.groups.schicken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    /*
        orgChart/orgChart
        spendingDetails/spendingDetails

     */


    @GetMapping("/")
    public String test(){
        return "member/profile";
    }

}
