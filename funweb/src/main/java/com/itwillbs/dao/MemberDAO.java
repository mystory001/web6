package com.itwillbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAO {
	
	//마이바티스 객체생성 => 멤버변수 정의 자동으로 주입
	@Inject
	private SqlSession sqlSession;
	//sql 구문 전체 이름
	private static final String namespace = "com.itwillbs.mappers.memberMapper";
	
	
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember()");
		
		sqlSession.insert(namespace+".insertMember",memberDTO);
							//sql구문이름,memberDTO
	}


	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAO userCheck()");
	 	return sqlSession.selectOne(namespace+".userCheck", memberDTO);
		
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberDAO getMember()");
		return sqlSession.selectOne(namespace+".getMember", id);
	}


	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember()");
		sqlSession.update(namespace+".updateMember", memberDTO);
	}
	
	

}
