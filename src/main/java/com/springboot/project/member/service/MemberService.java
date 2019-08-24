package com.springboot.project.member.service;

import com.springboot.project.member.vo.MemberVO;

public interface MemberService {

	// 회원가입
	public boolean RegistOneMembers(MemberVO memberVO) throws Exception;
	
	// 로그인
	public MemberVO readOneMembers(MemberVO memberVO) throws Exception;
	public MemberVO readOneMembersGetByEmail(String email);
	
	// 비밀번호 번경
	public boolean updateOneMembers(MemberVO memberVO) throws Exception;
	
	// 회원가입 이메일 중복체크
	public int duplicateCheckMembers(String email) throws Exception;
}
