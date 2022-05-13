package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FileDTO;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryDTO> selectGallery(Map<String, Object> param){
		return sqlSession.getMapper(GalleryMapper.class).selectGalleryList(param);
	}
	
	public float selectGalleryTotalPage(Map<String, Object> map) {
		return sqlSession.getMapper(GalleryMapper.class).selectGalleryTotalPage(map);
	}
	
	public int insertGallery(GalleryDTO galleryDTO) {
		return sqlSession.getMapper(GalleryMapper.class).insertGallery(galleryDTO);
	}
	
	public int insertGalleryFile(FileDTO file) {
		return sqlSession.getMapper(GalleryMapper.class).insertGalleryFile(file);
	}
	
	public GalleryDTO selectGallery(int gi_no) {
		return sqlSession.getMapper(GalleryMapper.class).selectGallery(gi_no);
	}
	
	public int deleteGallery(int gi_no) {
		return sqlSession.getMapper(GalleryMapper.class).deleteGallery(gi_no);
	}
	
	public int updateGallery(GalleryDTO galleryDTO) {
		return sqlSession.getMapper(GalleryMapper.class).updateGallery(galleryDTO);
	}
	
	public int deleteFileGallery(int file_num) {
		return sqlSession.getMapper(GalleryMapper.class).deleteFileGallery(file_num);
	}
	
	public List<FileDTO> selectgGalleryFile(int gi_no) {
		return sqlSession.getMapper(GalleryMapper.class).selectgGalleryFile(gi_no);
	}
	
	public List<CommentDTO> selectCommentList(Map<String, Object> param){
		return sqlSession.getMapper(GalleryMapper.class).selectCommentList(param);
	}
	
	public int insertCommnet(CommentDTO commentDTO) {
		return sqlSession.getMapper(GalleryMapper.class).insertCommnet(commentDTO);
	}

	public int deleteComment(int ci_no) {
		return sqlSession.getMapper(GalleryMapper.class).deleteComment(ci_no);
	}
	
	public int updateComment(CommentDTO commentDTO) {
		return sqlSession.getMapper(GalleryMapper.class).updateComment(commentDTO);
	}
	
	public float selectGalleryCommTotalPage(int board_id) {
		return sqlSession.getMapper(GalleryMapper.class).selectGalleryCommTotalPage(board_id);
	}
}