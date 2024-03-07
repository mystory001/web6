package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.ReBoardService;

@Controller
@RequestMapping("/reboard/*")
public class ReboardController {
	
	@Inject
	private ReBoardService reBoardService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("ReboardController list()");
		
		//검색어 가져오기
		String search = request.getParameter("search");
		
		// 한 화면에 보여줄 글의 개수 설정
		int pageSize = 15;
		// pageNum에 파라미터 값을 가져오기
		String pageNum = request.getParameter("pageNum"); //request에서 값을 받아옴
		// pageNum값이 없으면 1로 설정
		if(pageNum == null) {
			pageNum = "1";
		}
		
		//pageNum => 정수형 변경
		int currentPage = Integer.parseInt(pageNum);
		
		//pageDTO에 저장
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		//검색어 추가
		pageDTO.setSearch(search);
		
		List<Map<String, Object>> boardList = reBoardService.getBoardList(pageDTO);
		
		//페이징 작업
		//전체 글의 개수 구하기 int count = getBoardCount(pageDTO) 검색어 포함
		int count = reBoardService.getBoardCount(pageDTO);
		//한 화면에 보여줄 페이지 개수 설정
		int pageBlock = 10;
		//한 화면에 보여줄 시작 페이지 구하기
		//1~10 => 1, 11~20 => 11,..
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		
		//한 화면에 보여줄 끝 페이지 구하기
		int endPage = startPage + pageBlock -1;
		//전체 페이지 개수 구하기
		int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
		if(endPage > pageCount) {
			endPage = pageCount; //끝 페이지, 전체 페이지 수를 비교해서 => 끝 페이지가 크면 전체 페이지 수로 끝 페이지 변경
		}
		
		
		//pageDTO 저장
		pageDTO.setCount(count); //[전체 글의 개수 ${pageDTO.count }]
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		//model 저장
		model.addAttribute("pageDTO", pageDTO);
		
		model.addAttribute("boardList", boardList);
		
		// recenter/notice.jsp 주소변경없이 이동
		return "recenter/notice";
	}
	
	@GetMapping("/write")
	public String write() {
		System.out.println("ReBoardController write()");
		
		return "recenter/write";
	}
	
	@PostMapping("/writePro")
	public String writePro(@RequestParam Map<String, Object> param) { //DTO를 사용하지 않고 @RequestParam Map<이름, 값> 변수
		System.out.println("ReBoardController writePro()");
		System.out.println(param);
		
		reBoardService.insertBoard(param);
		
		return "redirect:/reboard/list";
	}
	
	@GetMapping("/content")
	public String content(BoardDTO boardDTO, Model model) {
		System.out.println("ReBoardController content()");
		System.out.println(boardDTO);
		//조회수 증가
		reBoardService.updateReadcount(boardDTO);
		boardDTO = reBoardService.getBoard(boardDTO);
		
		
		//model 저장
		model.addAttribute("boardDTO", boardDTO);
		
		System.out.println(boardDTO);
		// board/content.jsp 글 목록으로 주소변경없이 이동
		return "/recenter/content";
	}
	
	// /reboard/rewrite?num=
	@GetMapping("/rewrite")
	public String rewrite(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController rewrite()");
		
		boardDTO = reBoardService.getBoard(boardDTO);
		
		model.addAttribute("boardDTO", boardDTO);
		return "recenter/rewrite";
	}
	
	@PostMapping("/rewritePro")
	public String rewritePro(@RequestParam Map<String, Object> param) {
		System.out.println("BoardController rewritePro()");
		System.out.println(param);
		
		reBoardService.reInsertBoard(param);
		
		return "redirect:/reboard/list";
	}
	
	
}
