package com.spring.shop.member;

import java.util.Map;

public interface MemberMapper {
	public abstract int insertMember(MemberDTO memberDTO);
	public abstract MemberDTO selectMember(MemberDTO memberDTO);
	public abstract int deleteMember(String mi_id);
	public abstract String selectPw(String mi_id);
	public abstract int updateMember(MemberDTO memberDTO);
	public abstract String idCheck(String mi_id);
}
