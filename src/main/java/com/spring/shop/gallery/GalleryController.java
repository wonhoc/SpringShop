package com.spring.shop.gallery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.comment.CommentDTO;
import com.spring.shop.commons.FileDTO;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/galleryList")
	public String galleryList(HttpServletRequest req) {
		req.setAttribute("content", "galleryList.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/getGalleryList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGalleryList(HttpServletRequest req) {
		return galleryService.retrieveGalleryList(req);
	}
	
	@RequestMapping(value = "/galleryDetail/{gi_no}", method = RequestMethod.GET)
	public String detailGallery(HttpServletRequest req, @PathVariable("gi_no") int gi_no, Model m) {
		GalleryDTO galleryDTO = galleryService.retrieveGallery(gi_no, req);
		m.addAttribute("gallery", galleryDTO);
		req.setAttribute("content", "galleryDetail.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/galleryWriteForm", method = RequestMethod.GET)
	public String galleryWriteForm(HttpServletRequest req) {
		req.setAttribute("content", "galleryWrite.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/galleryWrite", method = RequestMethod.POST)
	public String galleryWrite(HttpServletRequest req, GalleryDTO galleryDTO) throws IOException {
		int r = galleryService.writeGallery(galleryDTO, req);
		if(r > 0) {
			req.setAttribute("content", "galleryList.jsp");
		}
		return "home";
	}
	
	@RequestMapping(value = "/deleteGallery", method = RequestMethod.POST)
	@ResponseBody
	public int deleteGallery(@RequestParam("gi_no") int gi_no, HttpServletRequest req) {
		return galleryService.removeGallery(gi_no, req);
	}
	
	@RequestMapping(value = "/galleryEditForm", method = RequestMethod.GET)
	public String editGalleryForm(HttpServletRequest req, @RequestParam("gi_no") int gi_no, Model m) {
		GalleryDTO galleryDTO = galleryService.retrieveGallery(gi_no, req);
		m.addAttribute("gallery", galleryDTO);
		req.setAttribute("content", "galleryEdit.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/galleryEdit", method = RequestMethod.POST)
	public String galleryEdit(HttpServletRequest req, GalleryDTO galleryDTO, @RequestParam("file_num") List<Integer> file_num) {
		
		int r = galleryService.editGalley(galleryDTO, req);
		if(r>0) {
			req.setAttribute("content", "galleryList.jsp");
		}
		return "home";
	}
	
	@RequestMapping(value="/commentList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> retrieveCommentList(HttpServletRequest req) {
		return galleryService.retrieveCommentList(req);
	}
	
	@RequestMapping(value="/writeComment", method = RequestMethod.POST)
	@ResponseBody
	public int retrieveCommentList(CommentDTO commentDTO) {
		return galleryService.writeComment(commentDTO);
	}
	
	@RequestMapping(value="/deleteComment", method = RequestMethod.POST)
	@ResponseBody
	public int removeComment(@RequestParam("ci_no") int ci_no) {
		return galleryService.removeComment(ci_no);
	}
	
	@RequestMapping(value = "/editComment", method = RequestMethod.POST)
	@ResponseBody
	public int editComment(CommentDTO commentDTO) {
		System.out.println(commentDTO);
		
		return galleryService.editComment(commentDTO);
	}
}