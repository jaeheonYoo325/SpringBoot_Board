package com.springboot.project.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.project.board.vo.BoardSearchVO;
import com.springboot.project.board.vo.BoardVO;

@Mapper
public interface BoardMapper {

	public int insertOneBoard(BoardVO boardVO) throws Exception;
	
	public BoardVO selectOneBoard(int boardId) throws Exception;
	
	public int updateHitOneBoard(int boardId) throws Exception;
	
	public int updateOneBoard(BoardVO boardVO) throws Exception;
	
	public int deleteOneBoard(int boardId) throws Exception;
	
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO) throws Exception;
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO) throws Exception;
	
}
