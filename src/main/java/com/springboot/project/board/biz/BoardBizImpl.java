package com.springboot.project.board.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.project.board.mapper.BoardMapper;
import com.springboot.project.board.vo.BoardSearchVO;
import com.springboot.project.board.vo.BoardVO;
import com.springboot.project.common.pager.Pager;
import com.springboot.project.common.pager.PagerFactory;
import com.springboot.project.common.pager.explorer.ListPageExplorer;
import com.springboot.project.common.pager.explorer.PageExplorer;
import com.springboot.project.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardBizImpl implements BoardBiz{

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean createOneBoard(BoardVO boardVO, MemberVO memberVO) throws Exception {
		boolean isSuccess = this.boardMapper.insertOneBoard(boardVO) > 0;
		return isSuccess;
	}

	@Override
	public BoardVO readOneBoard(int boardId) throws Exception {
		return this.boardMapper.selectOneBoard(boardId);
	}

	@Override
	public BoardVO readOneBoard(int boardId, MemberVO memberVO) throws Exception {
		
		BoardVO boardVO = this.readOneBoard(boardId);
		log.info("-----------boardVO : " + boardVO.getEmail());	
		log.info("!!!!!!!!!!!!!memberVO : " + memberVO.getEmail());	
		if ( !boardVO.getEmail().equals(memberVO.getEmail()) ) {
			
			// 조회수 증가
			this.boardMapper.updateHitOneBoard(boardId);
			
			// 조회수 증가 후 게시글 다시 조회
			boardVO = this.readOneBoard(boardId);
		}
		return boardVO;
	}
	
	@Override
	public boolean updateOneBoard(BoardVO boardVO) throws Exception {
		boolean isSuccess = this.boardMapper.updateOneBoard(boardVO) > 0;
		return isSuccess;
	}

	@Override
	public boolean deleteOneBoard(int boardId) throws Exception {
		boolean isSuccess = this.boardMapper.deleteOneBoard(boardId) > 0;
		return isSuccess;
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) throws Exception {
		int totalCount = this.boardMapper.selectAllBoardsCount(boardSearchVO);
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE, boardSearchVO.getPageNo() + "");
		pager.setTotalArticleCount(totalCount);
		
		PageExplorer pageExplorer = pager.makePageExplorer(ListPageExplorer.class, boardSearchVO);
		pageExplorer.setList(this.boardMapper.selectAllBoards(boardSearchVO));
		
		return pageExplorer;
	}

}
