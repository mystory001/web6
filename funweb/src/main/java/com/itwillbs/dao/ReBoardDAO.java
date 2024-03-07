package com.itwillbs.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Repository
public class ReBoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.mappers.reBoardMapper";
	
	
	public List<Map<String, Object>> getBoardList(PageDTO pageDTO) {
		System.out.println("ReBoardDAO getBoardList()");
		System.out.println(pageDTO);
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	}
	
	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReBoardDAO getBoardCount()");
		return sqlSession.selectOne(namespace+".getBoardCount",pageDTO);
	}
	
	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReBoardDAO insertBoard()");
		System.out.println(param);
		sqlSession.insert(namespace+".insertBoard",param);
	}
	
	public Integer getMaxNum() {
		System.out.println("ReBoardDAO getMaxNum()");
		return sqlSession.selectOne(namespace+".getMaxNum");
	}
	
	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("ReBoardDAO getBoard()");
		return sqlSession.selectOne(namespace+".getBoard", boardDTO);
	}

	public void updateReadcount(BoardDTO boardDTO) {
		System.out.println("ReBoardDAO updateReadcount()");
		sqlSession.update(namespace+".updateReadcount", boardDTO);
		
	}

	public void updateResql(Map<String, Object> param) {
		System.out.println("ReBoardDAO updateResql()");
		
		sqlSession.update(namespace+".updateResql",param);
	}


	
	
	
}
