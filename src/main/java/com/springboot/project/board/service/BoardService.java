package com.springboot.project.board.service;

import com.springboot.project.board.vo.BoardSearchVO;
import com.springboot.project.board.vo.BoardVO;
import com.springboot.project.common.pager.explorer.PageExplorer;
import com.springboot.project.member.vo.MemberVO;

public interface BoardService {

	public boolean createOneBoard(BoardVO boardVO, MemberVO memberVO) throws Exception;
	
	public BoardVO readOneBoard(int boardId) throws Exception;
	public BoardVO readOneBoard(int boardId, MemberVO memberVO) throws Exception;
	
	public boolean updateOneBoard(BoardVO boardVO) throws Exception;
	
	public boolean deleteOneBoard(int boardId) throws Exception;
	
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) throws Exception;
}
