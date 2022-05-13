package com.spring.shop.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FIleService;
import com.spring.shop.commons.FileDTO;
import com.spring.shop.commons.PageGenerator;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private FIleService fileService;
	
	@Autowired
	private PageGenerator page;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	public Map<String, Object> retrieveList(HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		float pagePerCnt = Float.parseFloat(req.getParameter("pagePerCnt"));
		String keyword = req.getParameter("keyword");
		String searchtype = req.getParameter("searchtype");
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		
		param.put("keyword", keyword);
		param.put("searchtype", searchtype);
		
		float totalCnt = boardDao.selectTotalPage(param);
		
		param.putAll(page.pageGenerate(totalCnt,pagePerCnt,curPage));  
		List<BoardDTO> list = boardDao.selectBoardList(param);
		
		map.put("paging", param);
		map.put("list", list);
		
		return map;
	}
	
	// WAS에 파일을 저장해야되는 것은 인지함
	// WAS 에서 절대경로 or 상대경로 << 절대경로 
	// 상대경로를 써볼 것임
	public int writeBoard(BoardDTO boardDTO, HttpServletRequest req) throws IOException {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req; //확장 시켜주기 -> bean 등록
		// platformManager >> 이거를 씀
		// Annotation 편하게 사용 가능하지만 교육이기 때문에 platformManager사용
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		
		try {
		boardDao.insertBoard(boardDTO);
		
		// 2. 파일업로드하고
		fileList = fileService.uploadFile(mr);
		if(fileList != null) {
			for (FileDTO file : fileList) {
				int board_id = boardDTO.getBi_no();
				file.setBoard_id(board_id);
				boardDao.intsertFile(file);
			}
		}
		platformTransactionManager.commit(status);
		}catch (Exception e) {
			String path = mr.getSession().getServletContext().getRealPath("resources/file/");
			for (FileDTO file : fileList) {
				File f = new File(path+file.getSaved_file_name());
				f.delete();
			}
			platformTransactionManager.rollback(status);
		}
		return 1;
	}
	public BoardDTO retrieveDetail(int bi_no) {
		BoardDTO board = boardDao.selectDetail(bi_no);
		board.setFileList(boardDao.selectFile(bi_no));
		return board;
	}
	
	public int removeBoard(int bi_no, HttpServletRequest req) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		String path = mr.getSession().getServletContext().getRealPath("resources/file/");
		
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		try {
			List<FileDTO> list = boardDao.selectFile(bi_no);
			boardDao.deleteFile(bi_no);
			for (FileDTO file : list) {
				File f = new File(path+file.getSaved_file_name());
				f.delete();
			}
			platformTransactionManager.commit(status);
		} catch (Exception e) {
			platformTransactionManager.rollback(status);
		}
		return boardDao.deleteBoard(bi_no);
	}
	
	public int editBoard(BoardDTO boardDTO) {
		return boardDao.updateBoard(boardDTO);
	}
	
	public FileDTO fileDownload(int file_num) {
		return boardDao.fileDown(file_num);
	}
	
	public Map<String, Object> retrieveBoardCommentList(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		float pagePerCnt = Float.parseFloat(req.getParameter("pagePerCnt"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		param.put("board_id", board_id);
		float totalCnt = boardDao.selectBoardComTotalPage(board_id);
		
		param.putAll(page.pageGenerate(totalCnt, pagePerCnt, curPage));
		List<CommentDTO> list = boardDao.selectCommentBoardList(param);
		
		map.put("list", list);
		map.put("paging", param);
		
		return map;
	}
	
	public int writeBoardComment(CommentDTO commentDTO) {
		return boardDao.insertBoardCommnet(commentDTO);
	}
	
	public int removeBoardComment(int board_no) {
		return boardDao.deleteBoardComment(board_no);
	}
	
	public int editBoardComment(CommentDTO commentDTO) {
		return boardDao.updateBoardComment(commentDTO);
	}
}