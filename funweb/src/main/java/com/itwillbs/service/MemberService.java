package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberDTO;

@Service
public class MemberService {

	//MemberDAO 파일 만들기
	//MemberDAO 객체 생성 => 멤버 변수 자동으로 주입	
	@Inject
	private MemberDAO memberDAO;
	
	
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember()");
		//시스템의 날짜 => 가입날짜 설정
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberDAO.insertMember(memberDTO);
		
	}

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberService userCheck()");
		
		return memberDAO.userCheck(memberDTO);
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberService getMember()");
		
		return memberDAO.getMember(id);
		
	}

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberService updateMember()");
		memberDAO.updateMember(memberDTO);
	}

	
}
