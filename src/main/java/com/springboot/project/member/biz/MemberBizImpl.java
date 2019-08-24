package com.springboot.project.member.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.project.common.utils.SHA256Util;
import com.springboot.project.member.mapper.MemberMapper;
import com.springboot.project.member.vo.MemberVO;

@Component
public class MemberBizImpl implements MemberBiz{

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public boolean RegistOneMembers(MemberVO memberVO) throws Exception {
		
		String salt = SHA256Util.generateSalt();
		String password = this.getHashedPassword(salt, memberVO.getPassword());
		
		memberVO.setPassword(password);
		memberVO.setSalt(salt);
		
		return this.memberMapper.insertOneMembers(memberVO) > 0;
	}

	public String getHashedPassword(String salt, String password) {
		return SHA256Util.getEncrypt(password, salt);
	}
	
	@Override
	public MemberVO readOneMembers(MemberVO memberVO) throws Exception {
		
		String salt = this.memberMapper.getSaltByEmail(memberVO.getEmail());
		
		if ( salt != null ) {
			String password = this.getHashedPassword(salt, memberVO.getPassword());
			memberVO.setPassword(password);
		}
		
		MemberVO loginMemberVO = this.memberMapper.selectOneMembers(memberVO);
		return loginMemberVO;
	}

	@Override
	public MemberVO readOneMembersGetByEmail(String email) {
		return this.memberMapper.selectOneMembersGetByEmail(email);
	}

	@Override
	public boolean updateOneMembers(MemberVO memberVO) throws Exception {
		
		String salt = SHA256Util.generateSalt();
		String password = this.getHashedPassword(salt, memberVO.getPassword());
		
		memberVO.setPassword(password);
		memberVO.setSalt(salt);
		
		return this.memberMapper.updateOneMembers(memberVO) > 0;
	}

	@Override
	public int duplicateCheckMembers(String email) throws Exception {
		return this.memberMapper.duplicateCheckMembers(email);
	}

}
