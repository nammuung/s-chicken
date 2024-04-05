package com.groups.schicken.orgChart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {

    @GetMapping("orgChart")
    @ResponseBody
    public List<orgElement> getOrgChart(){
        List<orgElement> list = new ArrayList<>();

        String[] parent = "#,0,1,1,1,0,5,5,0,8,8,0,11,11,0,14,14,0,17,17".split(",");
        String[] text = "S치킨,경영지원부,총무과,재무과,인사과,식품개발부,개발1팀,개발2팀,영업부,영업1팀,영업2팀,마케팅부,마케팅1팀,마케팅2팀,공급망관리부,구매팀,물류팀,가맹관리부,가맹1팀,가맹2팀".split(",");

        for (int i = 0; i < text.length; i++) {
            list.add(new orgElement(i + "",parent[i], "dept",text[i]));
        }

        return list;
    }

    @PostMapping("orgChart")
    public String addOrgChart(){
        return "aaa";
    }

}
