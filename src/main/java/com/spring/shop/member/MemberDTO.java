package com.spring.shop.member;

public class MemberDTO {
	
	private String mi_id;
	private String mi_pw;
	private String mi_email;
	private String mi_mobile;
	private String mi_tell;
	private String mi_addr;
	private String mi_insertdt;

	private String mi_addr1;
	private String mi_addr2;
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMi_id() {
		return mi_id;
	}
	public void setMi_id(String mi_id) {
		this.mi_id = mi_id;
	}
	public String getMi_pw() {
		return mi_pw;
	}
	public void setMi_pw(String mi_pw) {
		this.mi_pw = mi_pw;
	}
	public String getMi_email() {
		return mi_email;
	}
	public void setMi_email(String mi_email) {
		this.mi_email = mi_email;
	}
	public String getMi_mobile() {
		return mi_mobile;
	}
	public void setMi_mobile(String mi_mobile) {
		this.mi_mobile = mi_mobile;
	}
	public String getMi_tell() {
		return mi_tell;
	}
	public void setMi_tell(String mi_tell) {
		this.mi_tell = mi_tell;
	}
	public String getMi_addr() {
		return mi_addr;
	}
	public void setMi_addr(String mi_addr) {
		this.mi_addr = mi_addr;
	}
	public String getMi_insertdt() {
		return mi_insertdt;
	}
	public void setMi_insertdt(String mi_insertdt) {
		this.mi_insertdt = mi_insertdt;
	}
	public String getMi_addr1() {
		return mi_addr1;
	}

	public void setMi_addr1(String mi_addr1) {
		this.mi_addr1 = mi_addr1;
	}

	public String getMi_addr2() {
		return mi_addr2;
	}

	public void setMi_addr2(String mi_addr2) {
		this.mi_addr2 = mi_addr2;
	}

	@Override
	public String toString() {
		return "MemberDTO [mi_id=" + mi_id + ", mi_pw=" + mi_pw + ", mi_email=" + mi_email + ", mi_mobile=" + mi_mobile
				+ ", mi_tell=" + mi_tell + ", mi_addr=" + mi_addr + ", mi_insertdt=" + mi_insertdt + ", mi_addr1="
				+ mi_addr1 + ", mi_addr2=" + mi_addr2 + "]";
	}

}
