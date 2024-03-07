package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ReBoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class ReBoardService {

	@Inject
	private ReBoardDAO reBoardDAO;
	
	public List<Map<String, Object>> getBoardList(PageDTO pageDTO) {
		System.out.println("ReBoardService getBoardList()");
		
		//시작하는 행 번호 구하기 (currentPage, pageSize를 이용해서)
		int currentPage = pageDTO.getCurrentPage();
		int pageSize = pageDTO.getPageSize();
		int startRow = (currentPage - 1) * pageSize + 1;
		
		//끝나는 행 번호 구하기
		int endRow = startRow + pageSize - 1;
		
		//pageDTO에 저장
		//boardMapper => limit(한계까지 자르기) 시작행 - 1, 개수
		pageDTO.setStartRow(startRow - 1);
		pageDTO.setEndRow(endRow);
		
		return reBoardDAO.getBoardList(pageDTO);
	}
	
	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReBoardService getBoardCount()");
		return reBoardDAO.getBoardCount(pageDTO);
	}

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReBoardService insertBoard");
		
		if(reBoardDAO.getMaxNum() == null) {
			//글 없음
			param.put("num", 1);
			param.put("re_ref", 1);
			
		}else {
			//글 있음
			param.put("num", reBoardDAO.getMaxNum()+1);
			//기본글
			//기준 글 번호 => 그룹 번호 일치
			param.put("re_ref", reBoardDAO.getMaxNum()+1);
		}
		
		param.put("readcount", 0);
		param.put("date",new Timestamp(System.currentTimeMillis()));
		
		//들여쓰기 0
		param.put("re_lev", 0);
		//순서 값
		param.put("re_sql", 0);
		
		System.out.println(param);
		reBoardDAO.insertBoard(param);
	}
	
	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("ReBoardService getBoard()");
			
		return reBoardDAO.getBoard(boardDTO);
	}

	public void updateReadcount(BoardDTO boardDTO) {
		System.out.println("ReBoardService updateReadcount()");
		
		int readcount = boardDTO.getReadcount()+1;
		boardDTO.setReadcount(readcount);
		reBoardDAO.updateReadcount(boardDTO);
	}

	public void reInsertBoard(Map<String, Object> param) {
		System.out.println("ReBoardService reInsertBoard()");
		//param => name, subject, content, re_ref, re_lev, re_sql
		//num, readcount, date 처리 => param 저장
		
		if(reBoardDAO.getMaxNum() == null) {
			//글 없음
			param.put("num", 1);
			
		}else {
			//글 있음
			param.put("num", reBoardDAO.getMaxNum()+1);
		}
		
		param.put("readcount", 0);
		param.put("date",new Timestamp(System.currentTimeMillis()));
		
		//기존 답글의 순서를 재배치
		reBoardDAO.updateResql(param);
		
		
		//답글
		//답글 그룹 번호 그대로 사용
		
		//들여쓰기 기존값 + 1
		param.put("re_lev", Integer.parseInt(param.get("re_lev").toString())+1);
		//순서 값 기존값 + 1
		param.put("re_sql", Integer.parseInt(param.get("re_sql").toString())+1);
		
		reBoardDAO.insertBoard(param);
	}
	
	
	
	
	
	
	
	
}
