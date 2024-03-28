package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;

@RestController
public class AjaxController {
	
	@Inject
	private MemberService memberService;
	@Inject
	private BoardService boardService;
	
	@GetMapping("/member/idCheck")
	public ResponseEntity<String> idCheck(MemberDTO memberDTO, HttpServletResponse response) {
		System.out.println("AjaxController idCheck()");
		System.out.println(memberDTO);
		
		MemberDTO memberDTO1 = memberService.getMember(memberDTO.getId());
		
		System.out.println(memberDTO1);
		//아이디가 있으면 memberDTO1 주소값 전달 => 아이디 중복
		//아이디가 없으면 memberDTO1 null 전달 => 아이디 사용가능
		
		String result ="";
		if(memberDTO1 != null) {
			//아이디 중복
			//result = "사용 불가능한 아이디입니다.";
			result = "iddup";
		}else {
			result = "idok";
			//result = "사용 가능한 아이디입니다.";
		}
		System.out.println(result);
		// 원래 return 이동할 주소 => ajax 처리 return 출력 결과 리턴 => 출력 결과 응답 (어노테이션@ResponseBody)
		// 출력 결과를 가지고 join.jsp 이동(안정적으로 되돌아감) => ResponseEntity
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/board/listjson")
	public ResponseEntity<List<BoardDTO>> listjson() {
		System.out.println("AjaxController listjson()");
		//최근 글 5개
		//한 화면에 보여줄 글 개수 5개
		int pageSize = 5;
		//현 페이지 1페이지 설정
		int currentPage = 1;
		//PageDTO 저장
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		ResponseEntity<List<BoardDTO>> entity = new ResponseEntity<List<BoardDTO>>(boardList, HttpStatus.OK);
		
		return entity;
	}
	
	//http://localhost:8080/funweb/member/list
	@GetMapping("/member/listjson")
	public ResponseEntity<List<MemberDTO>> memberlistjson(){
		System.out.println("AjaxController memberlistjson");
		
		List<MemberDTO> memberList = memberService.getMemberList();
		
		//memberList => json 변경 프로그램 설치
		//데이터를 json형태로 변경하는 프로그램 jackson-databind
		ResponseEntity<List<MemberDTO>> entity = new ResponseEntity<List<MemberDTO>>(memberList, HttpStatus.OK);
		
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
