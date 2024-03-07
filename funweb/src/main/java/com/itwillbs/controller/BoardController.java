package com.itwillbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	//servlet-context.xml id="uploadPath"에서 정의한 파일 경로 이름 @@Resources(name = "uploadPath")
	@javax.annotation.Resource(name = "uploadPath")
	private String uploadPath;

	
	@GetMapping("/list")
	public String list(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("BoardController list()");
		
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
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		
		//페이징 작업
		//전체 글의 개수 구하기 int count = getBoardCount(pageDTO) 검색어 포함
		int count = boardService.getBoardCount(pageDTO);
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
		
		// center/notice.jsp 주소변경없이 이동
		return "center/notice";
	}
	
	// get 방식 /board/write 주소 매핑
	// center/write.jsp 주소변경없이 이동
	@GetMapping("/write")
	public String write() {
		System.out.println("BoardController write()");
		
		return "center/write";
	}
	
	// post방식 /board/wrtiePro 주소 매핑
	// BoardDTO boardDTO 전달받으면 자동으로 request에 담긴 파라미터 값이 저장
	// insertBoard(boardDTO) 메소드 호출
	// /board/list 글 목록으로 주소 변경하면서 이동
	@PostMapping("/writePro")
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		System.out.println(boardDTO);
		
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/content")
	public String content(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController content()");
		System.out.println(boardDTO);
		//boardDTO 리턴할형 = boardService.getBoard(boardDTO) 메소드 호출
		boardDTO = boardService.getBoard(boardDTO);
		//조회수 증가
		boardService.updateReadcount(boardDTO);
		
		
		//model 저장
		model.addAttribute("boardDTO", boardDTO);
		
		System.out.println(boardDTO);
		// board/content.jsp 글 목록으로 주소변경없이 이동
		return "/center/content";
	}
	
	//GET방식 /board/update?num=
	//BoardDTO boardDTO 전달받으면 자동으로 request에 담긴 파라미터 값이 저장
	//boardDTO 리턴할형 = boardService.getBoard(boardDTO) 메소드 호출
	//model 저장 "boardDTO",boardDTO
	// /center/update.jsp 글 수정으로 주소변경없이 이동
	@GetMapping("/update")
	public String update(BoardDTO boardDTO,Model model) {
		System.out.println("BoardController update()");
		boardDTO = boardService.getBoard(boardDTO);
		System.out.println(boardDTO);
		
		model.addAttribute("boardDTO",boardDTO);
		return "/center/update";
	}
	
	//POST방식 /board/updatePro 주소 매핑
	//BoardDTO board 전달 받으면 자동으로 request에 담긴 파라미터 값이 저장
	//boardService.updateBoard(boardDTO) 메소드 호출
	// /board/list 글 목록으로 주소 변경하면서 이동
	@PostMapping("updatePro")
	public String updatePro(BoardDTO boardDTO) {
		System.out.println("BoardController updatePro()");
		boardService.updateBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(BoardDTO boardDTO) {
		System.out.println("BoardController delete()");
		boardService.deleteBoard(boardDTO);
		return "redirect:/board/list";
	}
	
	//---------------------------------------
	// board/flist
	@GetMapping("/flist")
	public String flist(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("BoardController flist()");
		
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
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		
		//페이징 작업
		//전체 글의 개수 구하기 int count = getBoardCount()
		int count = boardService.getBoardCount(pageDTO);
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
		
		// center/fnotice.jsp 주소변경없이 이동
		return "center/fnotice";
	}
	
	// get 방식 /board/write 주소 매핑
	// center/write.jsp 주소변경없이 이동
	@GetMapping("/fwrite")
	public String fwrite() {
		System.out.println("BoardController fwrite()");
		return "center/fwrite";
	}
	
	//
	@PostMapping("/fwritePro")
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception{
		//throws Exception => 메소드 호출하는 곳에서 예외처리를 함
		System.out.println("BoardController fwritePro()");
		//파일 업로드 -> 프로그램 설치
		//commons-fileupload, commons-io, javax-annotation 설치
		//pom.xml에 코드 작성
		//servlet-context.xml에서 프로그램 설정
		// 1) 대용량 데이터베이스(오라클) 컬럼에 저장
		// 2) 저용량 데이터베이스(Mysql) 서버 폴더에 파일을 업로드하고, 데이터베이스에 파일 이름이 저장 <- 사용할 예정
		
		//name = "file" => MultipartFile file => file 이름이 동일(name="이름"과 MultipartFile 이름 동일해야함)
		//업로드 파일 이름이 동일할 경우 => 랜덤문자_파일이름 => 이름 변경
		//랜덤 문자 만들기
		UUID uuid = UUID.randomUUID();
		String filename = uuid.toString() + "_" + file.getOriginalFilename();
		System.out.println(filename);
		
		//업로드 원본 파일 file.getBytes() => upload폴더에 복사(파일 업로드)
		//FileCopyUtils.copy(원본파일, 복사파일);
		System.out.println(uploadPath);
		FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename));
		
		//boardDTO에 파라미터값 저장
		//name,subject, content, file
		//BoardDTO 객체 생성
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));

		//file 저장
		boardDTO.setFile(filename);
		System.out.println(boardDTO);
		
		//insertBoard() 메소드 호출
		boardService.insertBoard(boardDTO);
		return "redirect:/board/flist";
	}
	
	@GetMapping("/fcontent")
	public String fcontent(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController fcontent()");
		System.out.println(boardDTO);
		//조회수 증가
		boardService.updateReadcount(boardDTO);
		
		//boardDTO 리턴할형 = boardService.getBoard(boardDTO) 메소드 호출
		boardDTO = boardService.getBoard(boardDTO);
		
		//model 저장
		model.addAttribute("boardDTO", boardDTO);
		
		// board/content.jsp 글 목록으로 주소변경없이 이동
		return "/center/fcontent";
	}
	
	@GetMapping("/fupdate")
	public String fupdate(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController fupdate()");
		boardDTO = boardService.getBoard(boardDTO);
		System.out.println(boardDTO);
		
		model.addAttribute("boardDTO",boardDTO);
		return "/center/fupdate";
	}
	
	@PostMapping("fupdatePro")
	public String fupdatePro(HttpServletRequest request, MultipartFile file) throws Exception{
		System.out.println("BoardController fupdatePro()");
		BoardDTO boardDTO = new BoardDTO();
		//num, name, subject, content
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		
		//file, oldfile
		if(file.isEmpty()) {
			//첨부파일 없음 => 기존 파일 수정(oldfile)
			System.out.println("첨부파일 없음");
			boardDTO.setFile(request.getParameter("oldfile"));
		} else {
			//첨부파일이 있음 => 새 파일로 수정(file)
			System.out.println("첨부파일 있음");
			UUID uuid = UUID.randomUUID();
			String filename = uuid.toString() + "_" + file.getOriginalFilename();
			System.out.println(filename);
			
			//업로드 원본 파일 file.getBytes() => upload폴더에 복사(파일 업로드)
			//FileCopyUtils.copy(원본파일, 복사파일);
			System.out.println(uploadPath);
			FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename));
			
			boardDTO.setFile(filename);
		}
		
		boardService.fupdateBoard(boardDTO);
		
		return "redirect:/board/flist";
	}
	
	@GetMapping("/fdelete")
	public String fdelete(BoardDTO boardDTO) {
		System.out.println("BoardController fdelete()");
		boardService.deleteBoard(boardDTO);
		return "redirect:/board/flist";
	}
	
	//--------------------------------------------------------
	//http://localhost:8080/funweb/board/listjson
//	@GetMapping("/listjson")
//	@ResponseBody
//	public List<BoardDTO> listjson() {
//		System.out.println("BoardController listjson()");
//		//최근 글 5개
//		//한 화면에 보여줄 글 개수 5개
//		int pageSize = 5;
//		//현 페이지 1페이지 설정
//		int currentPage = 1;
//		//PageDTO 저장
//		PageDTO pageDTO = new PageDTO();
//		pageDTO.setPageSize(pageSize);
//		pageDTO.setCurrentPage(currentPage);
//		
//		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
//		
//		return boardList;
//	}
	
	
	
	
	
	
	
	
	
	
}
