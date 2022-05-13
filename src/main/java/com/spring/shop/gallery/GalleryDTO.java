package com.spring.shop.gallery;

import java.util.List;

import com.spring.shop.commons.FileDTO;

public class GalleryDTO {
	private int gi_no;
	private String gi_title;
	private String gi_content;
	private String gi_writer;
	private String gi_insertdt;
	private String keyword;
	private String saved_file_name;
	
	private List<Integer> file_num;
	private List<FileDTO> fileList;
	
	public GalleryDTO() {
		super();
	}
	public int getGi_no() {
		return gi_no;
	}
	public void setGi_no(int gi_no) {
		this.gi_no = gi_no;
	}
	public String getGi_title() {
		return gi_title;
	}
	public void setGi_title(String gi_title) {
		this.gi_title = gi_title;
	}
	public String getGi_content() {
		return gi_content;
	}
	public void setGi_content(String gi_content) {
		this.gi_content = gi_content;
	}
	public String getGi_writer() {
		return gi_writer;
	}
	public void setGi_writer(String gi_writer) {
		this.gi_writer = gi_writer;
	}
	public String getGi_insertdt() {
		return gi_insertdt;
	}
	public void setGi_insertdt(String gi_insertdt) {
		this.gi_insertdt = gi_insertdt;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<Integer> getFile_num() {
		return file_num;
	}
	public void setFile_num(List<Integer> file_num) {
		this.file_num = file_num;
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
		return "GalleryDTO [gi_no=" + gi_no + ", gi_title=" + gi_title + ", gi_content=" + gi_content + ", gi_writer="
				+ gi_writer + ", gi_insertdt=" + gi_insertdt + ", keyword=" + keyword + ", saved_file_name="
				+ saved_file_name + ", file_num=" + file_num + ", fileList=" + fileList + "]";
	}
}