package com.spring.shop.gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FIleService;
import com.spring.shop.commons.FileDTO;
import com.spring.shop.commons.PageGenerator;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	@Autowired
	private FIleService fileService;
	
	@Autowired
	private PageGenerator page;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	public Map<String, Object> retrieveGalleryList(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		float pagePerCnt = Float.parseFloat(req.getParameter("pagePerCnt"));
		String keyword = req.getParameter("keyword");
		String searchtype = req.getParameter("searchtype");
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		
		param.put("keyword", keyword);
		param.put("searchtype", searchtype);
		
		float totalCnt = galleryDao.selectGalleryTotalPage(param);
		
		param.putAll(page.pageGenerate(totalCnt,pagePerCnt,curPage));  
		List<GalleryDTO> list = galleryDao.selectGallery(param);
		map.put("galleryList", list);
		map.put("paging", param);
		
		return map;
	}
	
	public int writeGallery(GalleryDTO galleryDTO, HttpServletRequest req) throws IOException {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		
		try {
			galleryDao.insertGallery(galleryDTO);
			fileList = fileService.uploadFile(mr);
			if(!fileList.isEmpty()) {
				for (FileDTO file : fileList) {
					int gallery_id = galleryDTO.getGi_no();
					file.setBoard_id(gallery_id);
					galleryDao.insertGalleryFile(file);
				}
			}
			platformTransactionManager.commit(status);
		} catch (Exception e) {
			e.printStackTrace();
			String path = mr.getSession().getServletContext().getRealPath("resources/file/");
			for (FileDTO file : fileList) {
				File f = new File(path+file.getSaved_file_name());
				f.delete();
			}
			platformTransactionManager.rollback(status);
		}
		return 1;
	}
	
	public GalleryDTO retrieveGallery(int gi_no, HttpServletRequest req) {
		GalleryDTO galleryDTO = galleryDao.selectGallery(gi_no);
		galleryDTO.setFileList(galleryDao.selectgGalleryFile(gi_no));
		return galleryDTO;
	}
	
	public int removeGallery(int gi_no, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		List<FileDTO> fileList = galleryDao.selectgGalleryFile(gi_no);

		try {
			galleryDao.deleteGallery(gi_no);
			
			String path = req.getSession().getServletContext().getRealPath("resources/file/");
			for (FileDTO file : fileList) {
				galleryDao.deleteFileGallery(file.getFile_num());
				File f = new File(path+file.getSaved_file_name());
				f.delete();
			}
			platformTransactionManager.commit(status);
			return 1;
		} catch (Exception e) {
			platformTransactionManager.rollback(status);
		}
		return 0;
	}
	
	public int editGalley(GalleryDTO galDto, HttpServletRequest req) {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		List<FileDTO> fileList = galDto.getFileList();
		List<Integer> delFileList = galDto.getFile_num();
		String path = mr.getSession().getServletContext().getRealPath("resources/file/");
		List<FileDTO> existFile = galleryDao.selectgGalleryFile(galDto.getGi_no());
		
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		try {
			int r = galleryDao.updateGallery(galDto);
			fileList = fileService.uploadFile(mr);
			
			if(fileList != null) {
				for (FileDTO file : fileList) {
					
					file.setBoard_id(galDto.getGi_no());
					galleryDao.insertGalleryFile(file);
					
				}
				for (FileDTO fileDTO : existFile) {
					for (Integer delFile : delFileList) {
						if (fileDTO.getFile_num() == delFile) {
							galleryDao.deleteFileGallery(delFile);
							File f = new File(path+delFile);
							f.delete();
						}
					}
				}
			}
			platformTransactionManager.commit(status);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);
			return 0;
		}
	}
	
	public Map<String, Object> retrieveCommentList(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		float pagePerCnt = Float.parseFloat(req.getParameter("pagePerCnt"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		int board_id = Integer.parseInt(req.getParameter("board_id"));
		param.put("board_id", board_id);
		float totalCnt = galleryDao.selectGalleryCommTotalPage(board_id);
		
		param.putAll(page.pageGenerate(totalCnt, pagePerCnt, curPage));
		List<CommentDTO> list = galleryDao.selectCommentList(param);
		
		map.put("list", list);
		map.put("paging", param);
		
		return map;
	}
	
	public int writeComment(CommentDTO commentDTO) {
		return galleryDao.insertCommnet(commentDTO);
	}
	
	public int removeComment(int ci_no) {
		return galleryDao.deleteComment(ci_no);
	}
	
	public int editComment(CommentDTO commentDTO) {
		return galleryDao.updateComment(commentDTO);
	}
}