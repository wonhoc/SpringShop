package com.spring.shop.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FileDTO;
import com.spring.shop.member.MemberMapper;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> selectBoardList(Map<String, Object> param) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardList(param);
	}
	
	public int insertBoard(BoardDTO boardDTO) {
		return sqlSession.getMapper(BoardMapper.class).insertBoard(boardDTO);
	}
	
	public BoardDTO selectDetail(int bi_no) {
		return sqlSession.getMapper(BoardMapper.class).selectDetailBoard(bi_no);
	}

	public float selectTotalPage(Map<String, Object> param) {
		return sqlSession.getMapper(BoardMapper.class).selectTotalPage(param);
	}
	
	public int deleteBoard(int bi_no) {
		return sqlSession.getMapper(BoardMapper.class).deleteBoard(bi_no);
	}
	
	public int updateBoard(BoardDTO boardDTO) {
		return sqlSession.getMapper(BoardMapper.class).updateBoard(boardDTO);
	}

	public int intsertFile(FileDTO param) {
		return sqlSession.getMapper(BoardMapper.class).insertFile(param);
	}
	
	public List<FileDTO> selectFile(int bi_no){
		return sqlSession.getMapper(BoardMapper.class).selectFile(bi_no);
	}
	
	public int deleteFile(int bi_no) {
		return sqlSession.getMapper(BoardMapper.class).deleteFile(bi_no);
	}
	
	public FileDTO fileDown(int file_num) {
		return sqlSession.getMapper(BoardMapper.class).fileDown(file_num);
	}
	
	public List<CommentDTO> selectCommentBoardList(Map<String, Object> param){
		return sqlSession.getMapper(BoardMapper.class).selectCommentBoardList(param);
	}
	
	public float selectBoardComTotalPage(int board_num) {
		return sqlSession.getMapper(BoardMapper.class).selectBoardComTotalPage(board_num);
	}
	
	public int insertBoardCommnet(CommentDTO commentDTO) {
		return sqlSession.getMapper(BoardMapper.class).insertBoardCommnet(commentDTO);
	}
	
	public int deleteBoardComment(int board_id) {
		return sqlSession.getMapper(BoardMapper.class).deleteBoardComment(board_id);
	}
	
	public int updateBoardComment(CommentDTO commentDTO) {
		return sqlSession.getMapper(BoardMapper.class).updateBoardComment(commentDTO);
	}
}