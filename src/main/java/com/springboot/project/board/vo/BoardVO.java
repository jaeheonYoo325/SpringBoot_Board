package com.springboot.project.board.vo;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.common.mapper.support.Types;
import com.springboot.project.member.vo.MemberVO;

import lombok.Data;

@Data
public class BoardVO {

	@Types
	private int boardId;
	
	@Types
	@NotEmpty(message = "글 제목은 필수 잆력 값입니다.")
	private String title;
	
	@Types
	@NotEmpty(message = "글 내용은 필수 잆력 값입니다.")
	private String content;
	
	@Types
	private int hit;
	
	@Types
	private String wrtDt;
	
	@Types
	private String mdfyDt;
	
	@Types(alias = "B_EMAIL")
	private String email;
	
	@Types
	private String fileName;
	
	@Types
	private String originFileName;
	
	private MultipartFile file;
	
	private MemberVO memberVO;
	
	
	public BoardVO() {
		this.fileName = "";
		this.originFileName = "";
	}
}
