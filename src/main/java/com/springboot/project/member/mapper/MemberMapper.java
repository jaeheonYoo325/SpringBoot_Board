package com.springboot.project.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.project.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	// 회원가입
	public int insertOneMembers(MemberVO memberVO) throws Exception;
	
	// 로그인
	public MemberVO selectOneMembers(MemberVO memberVO) throws Exception;
	public MemberVO selectOneMembersGetByEmail(String email);
	
	// 비밀번호 번경
	public int updateOneMembers(MemberVO memberVO) throws Exception;
	
	// 회원가입 이메일 중복체크
	public int duplicateCheckMembers(String email) throws Exception;
	public String getSaltByEmail(String email) throws Exception;
	
}
