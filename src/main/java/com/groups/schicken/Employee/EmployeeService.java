package com.groups.schicken.Employee;

import java.util.*;

import com.groups.schicken.common.util.DateManager;
import com.groups.schicken.common.util.PhoneNumberHyphenInserter;
import com.groups.schicken.franchise.FranchiseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.groups.schicken.franchise.FranchiseVO;
import com.groups.schicken.annual.AnnualVO;
import com.groups.schicken.common.util.FileManager;
import com.groups.schicken.common.vo.FileVO;
import com.groups.schicken.common.vo.Pager;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)  //error 났을때 rollbac설정
public class EmployeeService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private FranchiseMapper franchiseMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;  //비밀번호를 저장할때 사용 암호화 하는 역할


	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private FileManager fileManager;


	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	    System.out.println("입력 아이디: "+id);
	    EmployeeVO employeeVO = new EmployeeVO();
	    // emp로 시작하는 경우 그룹웨어 사용자
	    if (id.startsWith("emp")) {
	        // "emp"를 제거한 실제 id 파싱
	        String realId = id.substring(3);
	        employeeVO.setId(realId);
	        try {
	        	employeeVO= employeeDAO.getDetail(employeeVO);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    // fra로 시작하는 경우 여기 수정해야댐 =============================================
	    else if (id.startsWith("fra")) {
			FranchiseVO franchiseVO = new FranchiseVO();
	        // "fra"를 제거한 실제 id 파싱
	        String realId = id.substring(3);
	        franchiseVO.setId(realId);
	        try {
	            franchiseVO = franchiseMapper.getFranchise(franchiseVO);
				System.out.println("franchiseVO = " + franchiseVO);
				return franchiseVO;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


	    log.info("{}",id);
	    return employeeVO;
	}


	/**
	 *
	 * @param employeeVO
	 * @return
	 * @throws Exception
	 */
	public Integer join(EmployeeVO employeeVO)throws Exception{
	    // 생년월일에서 하이픈 제거
	    String residentNumbers = employeeVO.getResidentNumber().replaceAll("-", "");
	    // 제거된 생년월일을 다시 설정
	    employeeVO.setResidentNumber(residentNumbers);

	    // 입사일에서 하이픈 제거
	    String dateOfEmploymens = employeeVO.getDateOfEmployment().replaceAll("-", "");
	    // 제거된 입사일을 다시 설정
	    employeeVO.setDateOfEmployment(dateOfEmploymens);

	    employeeVO.setPassword(passwordEncoder.encode(employeeVO.getPassword()));
	    int result = employeeDAO.join(employeeVO);


	    return result;
	}


	public EmployeeVO userDetail (EmployeeVO employeeVO)throws Exception{
		return employeeDAO.userDetail(employeeVO);
	}


	public List<EmployeeVO> userList(Pager pager)throws Exception{
		pager.makeIndex();
		pager.makeNum(employeeDAO.getTotalCount(pager));
		return employeeDAO.userList(pager);
	}

	public List<EmployeeVO> isuserList(Pager  pager)throws Exception{
		pager.makeIndex();
		pager.makeNum(employeeDAO.getTotalCount2(pager));
		return employeeDAO.isuserList(pager);
	}


	public List<RoleVO> rolelist (RoleVO roleVO)throws Exception{
		return employeeDAO.rolelist(roleVO);
	}

	public List<RoleVO> role (EmployeeVO employeeVO)throws Exception{
		return employeeDAO.role(employeeVO);
	}




	// 소셜 로그인
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		ClientRegistration clientRegistration = userRequest.getClientRegistration();  // 인가 서버에서 클라이언트의 정보를 가져와 매핑시킴

		OAuth2User user = super.loadUser(userRequest); //loadUser메서드 호출하여 userRequest요청에 대한 정보를  OAuth2User 객체에 담음
		String email = user.getAttribute("email");
		log.info("Client ID == > {}", clientRegistration.getClientId());
		log.info("Client Name == > {}", clientRegistration.getClientName());
		log.info("Client email == > {}", clientRegistration.getScopes());
		log.info("Client email == > {}", clientRegistration.getScopes());



		SocialVO socialVO = new SocialVO();
		EmployeeVO employeeVO = new EmployeeVO();
		socialVO.setId(clientRegistration.getClientId());
		socialVO.setKind(clientRegistration.getClientName());
		if(clientRegistration.getClientName().equals("Kakao")) {

			try {
				user = this.kakao(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}



	// Kakao
	private OAuth2User kakao(OAuth2User oAuth2User)throws Exception{
		Map<String, Object> map = oAuth2User.getAttribute("properties");
		EmployeeVO employeeVO = new EmployeeVO();
		// 사용자 이름을 꺼내옴

		employeeVO.setId(oAuth2User.getName());
		employeeVO.setAttributes(oAuth2User.getAttributes());


		return employeeVO;




	}




	 // 임시 비밀번호 생성 메서드
    private String generateTempPassword() {
        // 임시 비밀번호를 랜덤하게 생성하는 로직 추가 (예: UUID 사용)
        return UUID.randomUUID().toString().substring(0, 8); // 임시로 8자리 생성
    }

    // 메일용 비밀번호 재설정 및 임시 비밀번호 전송 메서드
    public boolean resetPassword(EmployeeVO employeeVO)throws Exception {
        // 임시 비밀번호 생성
        String tempPassword = generateTempPassword();

        employeeVO.setPassword(passwordEncoder.encode(tempPassword));
        employeeDAO.password(employeeVO);


        // 메일로 임시 비밀번호 전송
        sendTempPasswordEmail(employeeVO.getEmail(), tempPassword);
		return true;
    }


    public int employeeResetPassword(EmployeeVO employeeVO, String hiddenId)throws Exception{

    		EmployeeVO a = employeeDAO.passwordinfo(employeeVO);
    		String infoPassword=a.getDateOfEmployment();

    	System.out.println(infoPassword);
    	employeeVO.setPassword(passwordEncoder.encode(infoPassword));

    	return employeeDAO.passupdate(employeeVO);
    }


    public int passupdate(EmployeeVO employeeVO, String currentPassword, String newPassword, String hiddenId) throws Exception {
        // 비밀번호 DB에서 꺼내옴
        EmployeeVO emp = employeeDAO.passwordinfo(employeeVO);
        String infoPassword =  emp.getPassword();

        System.out.println("DB에 저장된 패스워드: " + infoPassword);
        System.out.println("사용자가 입력한 현재 패스워드: " + currentPassword);

        // true면 비밀번호가 동일하다 false는 비밀번호가 다르다.
        boolean isCurrentPasswordCorrect = passwordEncoder.matches(currentPassword, infoPassword);

        System.out.println(isCurrentPasswordCorrect);

        if(isCurrentPasswordCorrect) {
            // 현재 비밀번호가 일치하면 새 비밀번호로 업데이트
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            employeeVO.setPassword(encodedNewPassword);
            return employeeDAO.passupdate(employeeVO);
        } else {
            // 현재 비밀번호가 일치하지 않으면 -1을 반환하여 변경 실패를 나타냄
            return -1;
        }
    }


    public int updateEmployee(EmployeeVO employeeVO, MultipartFile attach, Long fid) throws Exception {
        int result = 0; // 기본적으로 반환할 결과 값을 0으로 설정

        // 파일이 첨부되었는지 확인ss
        if (attach != null && !attach.isEmpty()) {
            FileVO file = new FileVO();

            String id = employeeVO.getId();
            System.out.println();
            Long lid = Long.parseLong(id);
            file.setParentId(lid);
            file.setTblId("1077");
			if(fid != null) {

				file.setId(fid);
				System.out.println(lid);

				// 기존 파일 삭제
				fileManager.deleteFile(file);

				// 새 파일 업로드
			}
			fileManager.uploadFile(attach, file);

			employeeVO.setFile(file);
		}

        // 파일 첨부 여부와 상관없이 직원 정보를 업데이트
        result = employeeDAO.updateEmployee(employeeVO);

        return result;
    }





 // 임시 비밀번호를 이메일로 전송하는 메서드
    private void sendTempPasswordEmail(String to, String tempPassword) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // 인코딩 설정 추가
            helper.setTo(to);
            helper.setSubject("임시 비밀번호 발급 안내");

            // 메일 내용을 HTML 형식으로 작성
            String mailContent = "<p>안녕하세요, 임시 비밀번호가 발급되었습니다.</p>"
                    + "<p>임시 비밀번호: <strong>" + tempPassword + "</strong></p>"
                    + "<p>로그인 후에는 반드시 비밀번호를 변경해주세요.</p>";
            helper.setText(mailContent, true); // HTML 설정 추가
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // 이메일 전송 실패 시 예외 처리
            throw new RuntimeException("이메일 전송 에러");
        }
    }

    //@Transactional
    public int rolecontrolle(String departmentId, String[] rolIds) throws Exception {
		employeeDAO.roledelete(departmentId);
		List<RoleVO> list = new ArrayList<>();
			if(rolIds != null) {
				for(String rolId : rolIds) {
					list.add(RoleVO.ofs(departmentId, rolId));
					log.info("{} =      :",rolId);
				}
				employeeDAO.roleinsert(list);
			}
            return 1;
    }


    public EmployeeProfileVO getProfile(String id) {
		EmployeeProfileVO profile = employeeDAO.getProfile(id);
		profile.setPhoneNumber(PhoneNumberHyphenInserter.hyphenInsert(profile.getPhoneNumber()));
		return profile;
    }

    public PaystubVO getPaystub(PaystubVO paystubVO) {
		if(paystubVO.getYearMonth() == null){
			paystubVO.setYearMonth(DateManager.getTodayDateTime("yyyy-MM"));
		}

		PaystubVO paystub = employeeDAO.getPaystub(paystubVO);

		if(paystub == null){

			if(paystubVO.getYearMonth().compareTo(DateManager.getTodayDateTime("yyyy-MM")) >= 0) {
				paystub = employeeDAO.calcPaystub(paystubVO);
			}

			if(paystub == null){
				paystub = new PaystubVO();
			}

			paystub.setPayed(false);
		}

		if(paystub.getBonusSum() > 0){
			paystub.setBonusReason(employeeDAO.getBonusDocument(paystub));
		}

		if(paystub.getYearMonth() == null){
			paystub.setYearMonth(paystubVO.getYearMonth());
		}

		return paystub;
    }
}
