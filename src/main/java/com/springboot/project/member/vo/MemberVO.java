package com.springboot.project.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.springboot.project.common.mapper.support.Types;
import com.springboot.project.common.validator.MemberValidator;

import lombok.Data;

@Data
public class MemberVO {

	@Types(alias = "M_EMAIL")
	@NotEmpty(message = "이메일은 필수 입력 값입니다.", groups = { MemberValidator.Regist.class, MemberValidator.Login.class })
	@Email(message = "이메일 형식으로 작성해주세요.", groups = { MemberValidator.Regist.class, MemberValidator.Login.class })
	private String email;
	
	@Types
	@NotEmpty(message = "이름은 필수 입력 값입니다.", groups = { MemberValidator.Regist.class })
	private String name;
	
	@Types
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.", groups = { MemberValidator.Regist.class, MemberValidator.Login.class, MemberValidator.Update.class })
	@Length(min = 8, max = 20, message = "비밀번호는 8~20글자 사이로 입력해주세요.", groups = {MemberValidator.Regist.class, MemberValidator.Update.class })
	@Pattern(regexp = "((?=.*[a-zA-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20})", message = "대/소문자, 특수문자를 조합하여 비밀번호 8자리 이상 입력해주세요.")
	private String password;
	
	@Types
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.", groups = { MemberValidator.Regist.class, MemberValidator.Login.class, MemberValidator.Update.class })
	@Length(min = 8, max = 20, message = "비밀번호는 8~20글자 사이로 입력해주세요.", groups = {MemberValidator.Regist.class, MemberValidator.Update.class })
	@Pattern(regexp = "((?=.*[a-zA-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20})", message = "대/소문자, 특수문자를 조합하여 비밀번호 8자리 이상 입력해주세요.")
	private String passwordConfirm;
	
	@Types
	private String crtDt;
	
	@Types
	private String latestLogin;
	
	@Types
	private String salt;
	
	@Types
	private int loginFailCount;
	
	@Types
	private String isAdminYn;
	
	@Types
	private String mdfyDt;
}
