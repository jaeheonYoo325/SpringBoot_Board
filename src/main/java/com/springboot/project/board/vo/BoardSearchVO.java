package com.springboot.project.board.vo;

import com.springboot.project.common.pager.annotations.EndRow;
import com.springboot.project.common.pager.annotations.StartRow;

import lombok.Data;

@Data
public class BoardSearchVO {

	private int pageNo;

	private String searchKeyword;

	@StartRow
	private int startRow;

	@EndRow
	private int endRow;
}
