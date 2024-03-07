package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Repository
public class BoardDAO {
	
	//마이바티스(SqlSession) 객체생성 주입 
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.mappers.boardMapper";

	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO insertBoard()");
		System.out.println("BoardDTO : " + boardDTO);
		sqlSession.insert(namespace+".insertBoard",boardDTO);
	}

	public int getMaxNum() {
		System.out.println("BoardDAO getMaxNum()");
		
		
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardDAO getBoardList()");
		System.out.println(pageDTO);
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("BoardDAO getBoardCount()");
		return sqlSession.selectOne(namespace+".getBoardCount",pageDTO);
	}

	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO getBoard()");
		return sqlSession.selectOne(namespace+".getBoard", boardDTO);
	}

	public void updateReadcount(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateReadcount()");
		sqlSession.update(namespace+".updateReadcount", boardDTO);
		
	}


	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateBoard()");
		sqlSession.update(namespace+".updateBoard", boardDTO);
	}

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO deleteBoard()");
		sqlSession.delete(namespace+".deleteBoard", boardDTO);
	}

	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO deleteBoard()");
		sqlSession.update(namespace+".fupdateBoard", boardDTO);
	}



	
	
}
