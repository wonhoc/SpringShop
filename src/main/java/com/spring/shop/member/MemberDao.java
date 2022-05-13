package com.spring.shop.member;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertMember(MemberDTO memDto) {
		return sqlSession.getMapper(MemberMapper.class).insertMember(memDto);
	}
	public MemberDTO selectMember(MemberDTO memDto) {
		return sqlSession.getMapper(MemberMapper.class).selectMember(memDto);
	}
	public int deleteMember(String mi_id) {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(mi_id);
	}
	public String selectPw(String mi_id) {
		return sqlSession.getMapper(MemberMapper.class).selectPw(mi_id);
	}
	public int updateMember(MemberDTO memberDTO) {
		System.out.println("asdasd : " + memberDTO);
		return sqlSession.getMapper(MemberMapper.class).updateMember(memberDTO);
	}
	public String idCheck(String mi_id) {
		return sqlSession.getMapper(MemberMapper.class).idCheck(mi_id);
	}
}