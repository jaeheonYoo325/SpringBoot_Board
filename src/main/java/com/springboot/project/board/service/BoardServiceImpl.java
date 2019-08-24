package com.springboot.project.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.board.biz.BoardBiz;
import com.springboot.project.board.mapper.BoardMapper;
import com.springboot.project.board.vo.BoardSearchVO;
import com.springboot.project.board.vo.BoardVO;
import com.springboot.project.common.pager.explorer.PageExplorer;
import com.springboot.project.member.vo.MemberVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardBiz boardBiz;

	@Override
	public boolean createOneBoard(BoardVO boardVO, MemberVO memberVO) throws Exception {
		return this.boardBiz.createOneBoard(boardVO, memberVO);
	}

	@Override
	public BoardVO readOneBoard(int boardId) throws Exception {
		return this.boardBiz.readOneBoard(boardId);
	}
	
	@Override
	public BoardVO readOneBoard(int boardId, MemberVO memberVO) throws Exception {
		return this.boardBiz.readOneBoard(boardId, memberVO);
	}

	@Override
	public boolean updateOneBoard(BoardVO boardVO) throws Exception {
		return this.boardBiz.updateOneBoard(boardVO);
	}

	@Override
	public boolean deleteOneBoard(int boardId) throws Exception {
		return this.boardBiz.deleteOneBoard(boardId);
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) throws Exception {
		return this.boardBiz.readAllBoards(boardSearchVO);
	}

}
