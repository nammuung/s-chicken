package com.groups.schicken.Employee;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.policy.Principal;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.common.vo.Pager;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employee/*")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Login
	@GetMapping("login")
	public String login( @ModelAttribute EmployeeVO employeeVO, HttpSession session, Model model) throws Exception {
		//강제로 주소를 입력하거나 뒤로 로그인할때를 방지하는 용도
		Object obj=(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		log.info("{}",obj);
		System.out.println(employeeVO.getId());
		if (obj == null) {
			log.info("============오브젝트 Null=================================");

			return "employee/login";
		}
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();

		if(user.equals("anonymousUser")) {

			return "employee/login";
		}


		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String id = auth.getName();


		// 로그인할때 세션에 프로필 넣기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal().getClass() == EmployeeVO.class){
			EmployeeVO a = (EmployeeVO)authentication.getPrincipal();
			model.addAttribute("id",id);
			session.setAttribute("details", a);
				return "redirect:/";
			} else {
				return "redirect:/franchise/home";
			}






	}


	//패스워드 변경
	@PostMapping("updatePassword")
	public String updatePassword(@RequestParam("password") String currentPassword,
	                             @RequestParam("newpassword") String newPassword,
	                             @RequestParam("renewpassword") String confirmNewPassword,
	                             @RequestParam("hiddenId") String hiddenId,
	                             Model model, EmployeeVO employeeVO) throws Exception {
	    // 인증 정보에서 ID 가져오기

		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  String id = auth.getName();
		  employeeVO.setId(id);


	    // 새 비밀번호와 확인 비밀번호 일치 여부 확인
	    if (!newPassword.equals(confirmNewPassword)) {
	        model.addAttribute("msg", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
	        model.addAttribute("path", "./profile?id=" + hiddenId);
	        return "employee/result";
	    }

	    // 비밀번호 업데이트 시도						// ▽ 요기에 아이디가 필요함
	    int result = employeeService.passupdate(employeeVO, currentPassword, newPassword, hiddenId);

	    String msg = "비밀번호 변경 실패";
	    String path = "./profile?id=" + hiddenId;

	    if (result > 0) {
	        msg = "비밀번호 변경 성공";
	    }

	    model.addAttribute("msg", msg);
	    model.addAttribute("path", path);
	    return "employee/result";
	}

	//패스워드 초기화
	@PostMapping("employeeResetPassword")
	public String employeeResetPassword(Model model, EmployeeVO employeeVO, @RequestParam("hiddenId") String hiddenId)throws Exception{
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  String id = auth.getName();
		  employeeVO.setId(hiddenId);
	    int result = employeeService.employeeResetPassword(employeeVO, hiddenId);
		String msg = "비밀번호가 초기화 되었습니다.";
		String path = "./profile?id=" + hiddenId;
	    model.addAttribute("msg", msg);
	    model.addAttribute("path", path);
		return "employee/result";
	}


	// 직원 정보 수정
	@PostMapping("updateEmployee")
	public String updateEmployee(Model model, HttpSession session,EmployeeVO employeeVO, MultipartFile attach, @RequestParam("id") String id, @RequestParam(value = "fid", required = false)Long fid)throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int result = employeeService.updateEmployee(employeeVO, attach, fid);


		    String msg = "수정을 실패 하였습니다.";
		    String path = "./profile?id=" + id;

		    if (result > 0) {
		        msg = "수정을 성공 하였습니다.";
		        path = "./profile?id=" + id;

		    }

		    model.addAttribute("msg", msg);
		    model.addAttribute("path", path);
		    return "employee/result";
	}


	//회원가입 페이지 이동
	@GetMapping("join")
	public void join(@ModelAttribute EmployeeVO employeeVO) throws Exception{
	}

	// 회원가입 요청
	@PostMapping("join")
	public String join(EmployeeVO employeeVO, Model model /*@RequestParam("attach") MultipartFile attach*/) throws Exception {

		int result = employeeService.join(employeeVO/* , attach */);

	    String msg = "가입 실패";
	    String path = "./join";

	    if (result > 0) {
	        msg = "가입 성공";
	        path = "../";
	    }

	    model.addAttribute("msg", msg);
	    model.addAttribute("path", path);

	    return "employee/result";
	}


	// 직원 상세정보
	@GetMapping("profile")
	public String userDetail(@AuthenticationPrincipal Principal principal,  HttpSession session,EmployeeVO employeeVO, Model model)throws Exception{
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String id = auth.getName();
		    model.addAttribute("id", id);
		    if(employeeVO.getId().isBlank()) {
		    	employeeVO = new EmployeeVO();
		    	employeeVO.setId(id);
		    }

		employeeVO = employeeService.userDetail(employeeVO);
	    session.setAttribute("details", employeeVO);
		EmployeeProfileVO employeeProfileVO =  employeeService.getProfile(id);
		model.addAttribute("detail", employeeVO);
		model.addAttribute("profile", employeeProfileVO);
		return "employee/profile";

	}

	// 직원 목록
	@GetMapping("list")
	public String userList(Pager pager, Model model)throws Exception{
		List<EmployeeVO> ar = employeeService.userList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager",pager);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			boolean hasPersonnel = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PERSONNEL_WRITER"));
			if (!hasPersonnel) {
			    String msg = "권한이 없습니다.";
			    String path = "../";
			    model.addAttribute("msg", msg);
			    model.addAttribute("path", path);
				return "employee/result";
			}
		}
		return "employee/list";


	}

	// 퇴사자 목록
	@GetMapping("isuserList")
	public String isuserList(Pager pager, Model model) throws Exception{
		List<EmployeeVO> ar = employeeService.isuserList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager",pager);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			boolean hasPersonnel = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PERSONNEL_WRITER"));
			if (!hasPersonnel) {
			    String msg = "권한이 없습니다.";
			    String path = "../";
			    model.addAttribute("msg", msg);
			    model.addAttribute("path", path);
				return "employee/result";
			}
		}
		return "employee/isuserList";
	}


	// 권한 설정 화면
	@GetMapping("role")
	public String role(EmployeeVO employeeVO ,Model model) throws Exception {
	    List<RoleVO> roles = employeeService.role(employeeVO);
	    model.addAttribute("list", roles);
	    return "employee/role";
	}

	//권한데이터 json으로 뿌리는 용도
	@GetMapping("/roles")
    public ResponseEntity<List<RoleVO>> rolelist1(RoleVO roleVO) throws Exception {
        List<RoleVO> roles = employeeService.rolelist(roleVO);

        return ResponseEntity.ok(roles);
    }

	// 권한 수정
	@PostMapping("role")
	public String update(@RequestParam("departmentId") String departmentId, @RequestParam("rolId") String[] rolId , Model model)throws Exception {
		System.out.println(departmentId);
		System.out.println("rolId = " + Arrays.toString(rolId));

		employeeService.rolecontrolle(departmentId, rolId);
	    return "redirect:/employee/role";
	}


	// 비밀번호 찾기 페이지로 이동
    @GetMapping("resetPassword")
    public String resetPasswordPage() {
        return "employee/resetPassword";
    }

    // 비밀번호 찾기 요청 처리
    @PostMapping("resetPassword")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("name") String name,@RequestParam("id") String id, Model model) throws Exception{
        // 이메일 주소를 이용하여 비밀번호 재설정 메서드 호출
    	   System.out.println("ID: " + id);
    	    System.out.println("Name: " + name);
    	    System.out.println("Email: " + email);

    	    EmployeeVO employeeVO = new EmployeeVO();
    	    employeeVO.setName(name);
    	    employeeVO.setEmail(email);
    	    employeeVO.setId(id);

        boolean result = employeeService.resetPassword(employeeVO);


        return "employee/login"; // 결과를 보여줄 페이지로 이동
    }

	@GetMapping("getProfile")
	public ResponseEntity<EmployeeProfileVO> getProfile(String id){
		EmployeeProfileVO profile = employeeService.getProfile(id);

		if(profile == null){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(profile);
	}

	@GetMapping("paystub")
	public String getPaystub(@AuthenticationPrincipal EmployeeVO employee, PaystubVO paystubVO, Model modal){
		log.info("paystub = {}" , paystubVO);

		paystubVO.setEmployeeId(employee.getId());
		paystubVO = employeeService.getPaystub(paystubVO);

		modal.addAttribute("paystub", paystubVO);
		return "paystub/paystub";
	}
}
