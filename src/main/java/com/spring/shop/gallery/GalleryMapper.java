package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FileDTO;

public interface GalleryMapper {
	public abstract List<GalleryDTO> selectGalleryList(Map<String, Object> param);
	public abstract float selectGalleryTotalPage(Map<String, Object> param);
	public abstract int insertGallery(GalleryDTO galleryMapperDTO);
	public abstract GalleryDTO selectGallery(int gi_no);
	public abstract int insertGalleryFile(FileDTO fileDTO);
	public abstract int deleteGallery(int gi_no);
	public abstract int updateGallery(GalleryDTO galleryDTO);
	public abstract int deleteFileGallery(int file_num);
	public abstract List<FileDTO> selectgGalleryFile(int gi_no);
	public abstract List<CommentDTO> selectCommentList(Map<String, Object> param);
	public abstract int insertCommnet(CommentDTO commentDTO);
	public abstract int deleteComment(int ci_no);
	public abstract int updateComment(CommentDTO commentDTO);
	public abstract float selectGalleryCommTotalPage(int board_id);
	
}