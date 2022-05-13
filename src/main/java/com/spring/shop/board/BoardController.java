package com.spring.shop.board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.comment.CommentDTO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public String boardList(HttpServletRequest req) {
		req.setAttribute("content", "boardlist.jsp");
		return "home";
	}
	
	// MessageConverter >> viewResolver
	@RequestMapping(value="/getBoardList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getList(HttpServletRequest req){
		return boardService.retrieveList(req);
	}

	@RequestMapping(value = "/boardWriteForm", method = RequestMethod.GET)
	public String boardWriteForm(HttpServletRequest req) {
		req.setAttribute("content", "boardWrite.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public String boardWrite(BoardDTO boardDTO, HttpServletRequest req) throws IOException {
		int r = boardService.writeBoard(boardDTO,req);
		if(r > 0) {
			req.setAttribute("content", "boardlist.jsp");
		}
		return "home";
	}
	
	@RequestMapping(value = "/detailBoard/{bi_no}", method = RequestMethod.GET)
	public String detailBoard(HttpServletRequest req,Model m, @PathVariable("bi_no") int bi_no) {
		BoardDTO boardDTO = boardService.retrieveDetail(bi_no);
		m.addAttribute("board",boardDTO);
		req.setAttribute("content", "boardDetail.jsp");
		return "home";
	}
	
	@RequestMapping(value="/deleteBoard", method = RequestMethod.POST)
	@ResponseBody
	public int deleteBoard(@RequestParam("bi_no") int bi_no, HttpServletRequest req) {
		return boardService.removeBoard(bi_no, req);
	}
	
	@RequestMapping(value="/editBoardForm", method = RequestMethod.GET)
	public String editBoardForm(HttpServletRequest req, @RequestParam("bi_no") int bi_no, Model m) {
		BoardDTO boardDTO = boardService.retrieveDetail(bi_no);
		m.addAttribute("board",boardDTO);
		req.setAttribute("content", "boardEdit.jsp");
		return "home";
	}
	
	@RequestMapping(value="/boardEdit", method = RequestMethod.POST)
	public String editBoard(HttpServletRequest req, BoardDTO boardDTO) {
		int r = boardService.editBoard(boardDTO);
		if(r>0) {
			req.setAttribute("MSG", "수정이 완료되었습니다");
			req.setAttribute("content", "boardlist.jsp");
		}
		return "home";
	}
	
	@RequestMapping(value = "/commentBoardList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> commentBoardList(HttpServletRequest req) {
		return boardService.retrieveBoardCommentList(req);
	}
	
	@RequestMapping(value = "/writeBoardComment", method = RequestMethod.POST)
	@ResponseBody
	public int commentBoardWrite(CommentDTO comDto) {
		return boardService.writeBoardComment(comDto);
	}
	
	@RequestMapping(value = "/deleteBoardComment", method = RequestMethod.POST)
	@ResponseBody
	public int removeBoardComment(@RequestParam("ci_no") int ci_no) {
		return boardService.removeBoardComment(ci_no);
	}
	
	@RequestMapping(value = "/editBoardComment", method = RequestMethod.POST)
	@ResponseBody
	public int editBoardComment(CommentDTO commentDTO) {
		return boardService.editBoardComment(commentDTO);
	}
}