package com.spring.shop.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.commons.FileDTO;

public class BoardDTO {
	
	private int bi_no;
	private String bi_title;
	private String bi_content;
	private String bi_writer;
	private String bi_insertdt;
	private String keyword;
	
	private String saved_file_name;
	private List<FileDTO> fileList;

	public BoardDTO() {
	}
	public int getBi_no() {
		return bi_no;
	}
	public void setBi_no(int bi_no) {
		this.bi_no = bi_no;
	}
	public String getBi_title() {
		return bi_title;
	}
	public void setBi_title(String bi_title) {
		this.bi_title = bi_title;
	}
	public String getBi_content() {
		return bi_content;
	}
	public void setBi_content(String bi_content) {
		this.bi_content = bi_content;
	}
	public String getBi_writer() {
		return bi_writer;
	}
	public void setBi_writer(String bi_writer) {
		this.bi_writer = bi_writer;
	}
	public String getBi_insertdt() {
		return bi_insertdt;
	}
	public void setBi_insertdt(String bi_insertdt) {
		this.bi_insertdt = bi_insertdt;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getSaved_file_name() {
		return saved_file_name;
	}
	public void setSaved_file_name(String saved_file_name) {
		this.saved_file_name = saved_file_name;
	}
	public List<FileDTO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileDTO> fileList) {
		this.fileList = fileList;
	}
	@Override
	public String toString() {
		return "BoardDTO [bi_no=" + bi_no + ", bi_title=" + bi_title + ", bi_content=" + bi_content + ", bi_writer="
				+ bi_writer + ", bi_insertdt=" + bi_insertdt + ", keyword=" + keyword + ", saved_file_name="
				+ saved_file_name + ", fileList=" + fileList + "]";
	}
}