package com.springboot.project.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.member.biz.MemberBiz;
import com.springboot.project.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberBiz memberBiz;

	@Override
	public boolean RegistOneMembers(MemberVO memberVO) throws Exception {
		return this.memberBiz.RegistOneMembers(memberVO);
	}

	@Override
	public MemberVO readOneMembers(MemberVO memberVO) throws Exception {
		return this.memberBiz.readOneMembers(memberVO);
	}

	@Override
	public MemberVO readOneMembersGetByEmail(String email) {
		return this.memberBiz.readOneMembersGetByEmail(email);
	}

	@Override
	public boolean updateOneMembers(MemberVO memberVO) throws Exception {
		return this.memberBiz.updateOneMembers(memberVO);
	}

	@Override
	public int duplicateCheckMembers(String email) throws Exception {
		return this.memberBiz.duplicateCheckMembers(email);
	}
	
	
}
