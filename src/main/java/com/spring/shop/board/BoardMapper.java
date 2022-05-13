package com.spring.shop.board;

import java.util.List;
import java.util.Map;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FileDTO;

public interface BoardMapper {
	public abstract List<BoardDTO> selectBoardList(Map<String, Object> param);
	public abstract int insertBoard(BoardDTO boardDTO);
	public abstract BoardDTO selectDetailBoard(int bi_no);
	public abstract float selectTotalPage(Map<String, Object> param);
	public abstract int deleteBoard(int bi_no);
	public abstract int updateBoard(BoardDTO boardDTO);
	public abstract int insertFile(FileDTO param);
	public abstract List<FileDTO> selectFile(int bi_id);
	public abstract int deleteFile(int bi_no);
	public abstract FileDTO fileDown(int file_num);
	public abstract List<CommentDTO> selectCommentBoardList(Map<String, Object> param);
	public abstract float selectBoardComTotalPage(int board_num);
	public abstract int insertBoardCommnet(CommentDTO commentDTO);
	public abstract int deleteBoardComment(int ci_no);
	public abstract int updateBoardComment(CommentDTO commentDTO);
}