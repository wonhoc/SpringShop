package com.spring.shop.commons;

public class FileDTO {

	private int file_num;
	private String file_name;
	private String saved_file_name;
	private long file_size;
	private int board_id;
	
	public FileDTO() {
	}
	public FileDTO(String file_name, String saved_file_name, long file_size) {
		super();
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
	}
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getSaved_file_name() {
		return saved_file_name;
	}
	public void setSaved_file_name(String saved_file_name) {
		this.saved_file_name = saved_file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	@Override
	public String toString() {
		return "FileDTO [file_num=" + file_num + ", file_name=" + file_name + ", saved_file_name=" + saved_file_name
				+ ", file_size=" + file_size + ", board_id=" + board_id + "]";
	}
}
