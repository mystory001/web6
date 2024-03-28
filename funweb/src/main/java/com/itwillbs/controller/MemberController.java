package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	//MemberService 파일 만들고 MemberService 객체 생성 => 멤버변수 자동으로 주입
	//MemberService memberService = new MemberService();
	//멤버 변수를 정의하고 root-context.xml에서 객체 생성해서 멤버 변수에 전달(주입)
	//p50 의존 관계 주입
	@Inject
	private MemberService memberService; 
	
	
	
	// 가상주소 http://localhost:8080/funweb/member/insert
	@GetMapping("/insert")
	public String insert() {
		System.out.println("MemberController insert()");
		// src/main/webapp/resources 폴더에 이미지, CSS, JS, JQuery 넣기
		// src/main/webapp/views 나머지 넣기
		
		//member/join.jsp 화면으로 주소변경없이 이동
		return "member/join";
	}

	// 가상주소 http://localhost:8080/funweb/member/login
	// => member/login.jsp 화면으로 주소변경없이 이동
	@GetMapping("/login")
	public String login() {
		System.out.println("MemberController login()");
		return "/member/login";
	}
	
	// 가상주소 http://localhost:8080/funweb/member/insertPro, post방식
	// join.jsp에서 입력한 데이터 => request => MemberDTO memberDTO 변수에 전달
	// DB 작업
	// /member/login 주소가 변경되면서 이동
	@PostMapping("/insertPro")
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemberController insertPro()");
		System.out.println(memberDTO);
		
		memberService.insertMember(memberDTO);
		
		return "redirect:/member/login";
	}
	
	// 가상주소 http://localhost:8080/funweb/member/loginPro, post방식
	// login.jsp에서 입력한 데이터 => request => MemberDTO memberDTO 변수에 전달
	// DB 작업 : MemberDTO memberDTO2 = userCheck(memberDTO)
	// memberDTO2 null이 아니면 아이디, 비밀번호가 일치 => 로그인 표시 세션값을 "id",값 => /memeber/main 주소가 변경되면서 이동
	//		      null이면 아이디, 비밀번호가 틀림 => /memeber/login 주소 변경되면서 이동
	@PostMapping("/loginPro")
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController loginPro()");
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		
		if(memberDTO2 != null) {
			session.setAttribute("id",memberDTO.getId());
			
			return "redirect:/member/main";
		} else {
			
//			return "redirect:/member/login"; member/msg.jsp 주소 변경없이 이동("아이디, 비밀번호 틀림 "메세지 출력", 뒤로 이동)
			return "member/msg";
		}
	}
	
	// 가상주소 http://localhost:8080/funweb/member/main
	// => main/main.jsp 화면으로 주소 변경 없이 이동
	@GetMapping("/member/main")
	public String main(MemberDTO memberDTO) {
		System.out.println("MemberController main()");
		
		return "/main/main";
	}
	
	// 가상주소 http://localhost:8080/funweb/member/logout get방식
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("MemberController logout");
		session.invalidate();
		return "redirect:/member/main";
	}
	
	// 가상주소 http://localhost:8080/funweb/member/update get방식
	// 세션 "id"값을 가져와서 => 변수에 저장 String id에 저장
	// MemberDTO memberDTO = getMember(id) 메소드 호출
	// Model model에 담아서 "memberDTO",memberDTO 이동
	// member/update.jsp로 주소 변경 없이 이동
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		System.out.println("MemberController update()");
		String id = (String)session.getAttribute("id");
		MemberDTO memberDTO = memberService.getMember(id);
		model.addAttribute("memberDTO", memberDTO);
		return "/member/update";
				
	}
	
	// 가상주소 http://localhost:8080/funweb/member/updatePro post방식
	// update.jsp에서 입력한 데이터 => request => MemberDTO memberDTO 변수에 전달
	// 디비 작업 MemberDTO memberDTO2 = userCheck(memberDTO) 메소드 호출
	// memberDTO2가 null이 아니면 => updateMember(memberDTO) 메소드 호출, /member/main 주소 변경되면서 이동
	//			   null이면 	  => /member/update 주소 변경되면서 이동
	@PostMapping("/updatePro")
	public String updatePro(MemberDTO memberDTO) {
		System.out.println("MemberController updatePro()");
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			memberService.updateMember(memberDTO);
			return "redirect:/member/main";
		} else {
//			return "redirect:/member/update";
			//주소 변경없이 이동
			return "member/msg";
		}
		
		
	}
	
//	@GetMapping("/idCheck")
//	@ResponseBody
//	public String idCheck(MemberDTO memberDTO, HttpServletResponse response) {
//		System.out.println("MemberController idCheck()");
//		System.out.println(memberDTO);
//		
//		MemberDTO memberDTO1 = memberService.getMember(memberDTO.getId());
//		
//		System.out.println(memberDTO1);
//		//아이디가 있으면 memberDTO1 주소값 전달 => 아이디 중복
//		//아이디가 없으면 memberDTO1 null 전달 => 아이디 사용가능
//		
//		String result ="";
//		if(memberDTO1 != null) {
//			//아이디 중복
//			//result = "사용 불가능한 아이디입니다.";
//			result = "iddup";
//		}else {
//			result = "idok";
//			//result = "사용 가능한 아이디입니다.";
//		}
//		System.out.println(result);
//		// 원래 return 이동할 주소 => ajax 처리 return 출력 결과 리턴 => 출력 결과 응답 (어노테이션@ResponseBody)
//		return result;
//	}
	
	@GetMapping("/list")
	public String list() {
		System.out.println("MemberController list()");
		
		// member/list.jsp 화면으로 주소 변경 없이 이동
		return "/member/list";
	}
	
	
	
	
	
	
}
