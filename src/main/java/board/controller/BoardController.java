package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.service.BoardService;

@Controller
public class BoardController{
//	@Autowired : 객체 자동 생성 어노테이션
	@Autowired
	private BoardService boardService;
	
//	게시판의 전체 목록을 불러오는 페이지
//	@RequestMapping : 사용자가 접속하는 주소와 Controller의 메서드와 연동시키는 어노테이션
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
//		사용자 화면과 데이터 베이스 정보를 가지고 있는 클래스
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("data", list);
		
		return mv;
	}
	
//	@RequestParam : 사용자가 입력한 파라미터값 받아오기, jsp의 getParameter()와 동일 
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx)
	throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	
	
	
}
