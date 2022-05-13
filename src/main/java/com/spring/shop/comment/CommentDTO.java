package com.spring.shop.comment;

public class CommentDTO {
	private int ci_no;
	private int board_id;
	private String ci_content;
	private String board_type;
	private String ci_writer;
	private String ci_insertdt;
	
	public CommentDTO() {
		super();
	}

	public int getCi_no() {
		return ci_no;
	}

	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}

	public String getCi_content() {
		return ci_content;
	}

	public void setCi_content(String ci_content) {
		this.ci_content = ci_content;
	}

	public String getCi_writer() {
		return ci_writer;
	}

	public void setCi_writer(String ci_writer) {
		this.ci_writer = ci_writer;
	}

	public String getCi_insertdt() {
		return ci_insertdt;
	}

	public void setCi_insertdt(String ci_insertdt) {
		this.ci_insertdt = ci_insertdt;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	@Override
	public String toString() {
		return "CommentDTO [ci_no=" + ci_no + ", board_id=" + board_id + ", ci_content=" + ci_content + ", board_type="
				+ board_type + ", ci_writer=" + ci_writer + ", ci_insertdt=" + ci_insertdt + "]";
	}

}