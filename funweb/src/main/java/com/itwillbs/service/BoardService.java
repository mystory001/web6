package com.itwillbs.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardService {
	
	//BoardDAO 객체생성 주입
	@Inject
	private BoardDAO boardDAO;
	
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardService insertBoard()");
		//boardDTO에 담긴 값 : name=park1, subject=제목, content=내용
		//num, readcount, date를 처리 => boardDTO 저장		
		//num
		boardDTO.setNum(boardDAO.getMaxNum()+1);
		//readcount
		boardDTO.setReadcount(0);
		//date
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
				
		boardDAO.insertBoard(boardDTO);
		
	}

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		
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
		
		return boardDAO.getBoardList(pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		return boardDAO.getBoardCount(pageDTO);
	}

	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("BoardService getBoard()");
			
		return boardDAO.getBoard(boardDTO);
	}

	public void updateReadcount(BoardDTO boardDTO) {
		System.out.println("BoardService updateReadcount()");
		
		int readcount = boardDTO.getReadcount()+1;
		boardDTO.setReadcount(readcount);
		boardDAO.updateReadcount(boardDTO);
	}


	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService updateBoard()");
		boardDAO.updateBoard(boardDTO);
	}

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardService deleteBoard()");
		boardDAO.deleteBoard(boardDTO);
	}

	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService fupdateBoard()");
		boardDAO.fupdateBoard(boardDTO);
	}







}
