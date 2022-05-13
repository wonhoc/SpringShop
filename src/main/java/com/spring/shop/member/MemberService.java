package com.spring.shop.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public int registerMember(MemberDTO memberDto) {
		String addr = memberDto.getMi_addr1()+"@"+memberDto.getMi_addr2();
		memberDto.setMi_addr(addr);
		return memberDao.insertMember(memberDto);
	}
	public MemberDTO retrieveMember(MemberDTO memberDTO) {
		return memberDao.selectMember(memberDTO);
	}
	public int removeMember(String mi_id) {
		return memberDao.deleteMember(mi_id);
	}
	public String retrievePw(String mi_id) {
		return memberDao.selectPw(mi_id);
	}
	public int editMember(HttpServletRequest req, MemberDTO memberDTO) {
		MemberDTO user = (MemberDTO)req.getSession().getAttribute("user");
		if(user.getMi_id().equals(memberDTO.getMi_id())) {
			String addr = memberDTO.getMi_addr1()+"@"+memberDTO.getMi_addr2();
			memberDTO.setMi_addr(addr);
			return memberDao.updateMember(memberDTO);
		}else {
			return 0;
		}
	}
	public String idCheck(String mi_id) {
		return memberDao.idCheck(mi_id);
	}
}