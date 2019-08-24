package com.springboot.project.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.springboot.project.board.service.BoardService;
import com.springboot.project.board.vo.BoardSearchVO;
import com.springboot.project.board.vo.BoardVO;
import com.springboot.project.common.mimetype.ExtFilter;
import com.springboot.project.common.mimetype.ExtensionFilter;
import com.springboot.project.common.mimetype.ExtensionFilterFactory;
import com.springboot.project.common.pager.explorer.PageExplorer;
import com.springboot.project.common.session.Session;
import com.springboot.project.common.utils.DownloadUtil;
import com.springboot.project.common.utils.HttpRequestHelper;
import com.springboot.project.member.vo.MemberVO;

@Controller
public class BoardController {

	@Autowired 
	private String uploadFilePath;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/boardInit.do")
	public String viewBoardListPageForInitiate(HttpSession session) {
		
		session.removeAttribute(Session.BOARD_SEARCH);
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView viewBoardListPage(
			@ModelAttribute BoardSearchVO boardSearchVO
			, HttpSession session) throws Exception {
		
		if ( boardSearchVO.getSearchKeyword() == null ) {
			boardSearchVO = (BoardSearchVO) session.getAttribute(Session.BOARD_SEARCH);
			
			if ( boardSearchVO == null ) {
				boardSearchVO = new BoardSearchVO();
				boardSearchVO.setPageNo(0);
			}
		}
		
		PageExplorer pageExplorer = this.boardService.readAllBoards(boardSearchVO);
		
		session.setAttribute(Session.BOARD_SEARCH, boardSearchVO);
		
		ModelAndView view = new ModelAndView(HttpRequestHelper.getJspPath());
		view.addObject("boardVOList", pageExplorer.getList());
		view.addObject("pagenation", pageExplorer.make());
		view.addObject("size", pageExplorer.getTotalCount());
		view.addObject("boardSearchVO", boardSearchVO);
		
		return view;		
	}

	@GetMapping("/board/boardWrite.do")
	public String doBoardWritePage() {
		return HttpRequestHelper.getJspPath();
	}
	
	@PostMapping("/board/boardWrite.do")
	public ModelAndView doBoardWriteAction(
			@Valid @ModelAttribute BoardVO boardVO
			, Errors errors
			, HttpSession session) {
		
		
		ModelAndView view = new ModelAndView("redirect:/board/boardList.do");
		
		// Validation Annotation이 실패했는지 체크
		if ( errors.hasErrors() ) {
			view.setViewName("board/boardWrite.do");
			view.addObject("boardVO", boardVO);
			return view;
		}
		
		MultipartFile uploadFile = boardVO.getFile();
		
		if ( !uploadFile.isEmpty() ) {
			// 실제 파일 이름
			String originFileName = uploadFile.getOriginalFilename();
			// 파일 시스템에 저장될 파일의 이름(난수)
			String fileName = UUID.randomUUID().toString();
			
			File uploadDir = new File(this.uploadFilePath);
			
			// 폴더가 존재하지 않는다면 생성
			if ( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			// 파일이 업로드될 경로 지정
			File destFile = new File(this.uploadFilePath, fileName);
						
			try {
				// 업로드
				uploadFile.transferTo(destFile);
				// DB에 File 정보 저장하기 위한 정보 셋팅
				boardVO.setOriginFileName(originFileName);
				boardVO.setFileName(fileName);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute(Session.USER);
		String email = loginMemberVO.getEmail();
		boardVO.setMemberVO(loginMemberVO);
		boardVO.setEmail(email);
		
		try {
			boolean isSuccess = this.boardService.createOneBoard(boardVO, loginMemberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	@RequestMapping("/board/boardDetail.do/{boardId}")
	public ModelAndView viewBoardDetailPage(
			@PathVariable int boardId
			, @SessionAttribute(Session.USER) MemberVO memberVO) {
		
		BoardVO boardVO = new BoardVO();
		try {
			boardVO = this.boardService.readOneBoard(boardId, memberVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView(HttpRequestHelper.getJspPath());
		view.addObject("boardVO", boardVO);
		return view;
	}
	
	@RequestMapping("/board/boardFileDownload.do/{boardId}")
	public void doBoardFileDownloadAction(
			@PathVariable int boardId
			, HttpServletRequest request
			, HttpServletResponse response
			, @SessionAttribute(Session.USER) MemberVO memberVO) {
		
		BoardVO boardVO = new BoardVO();
		String originFileName = null;
		String fileName = null;
		
		try {
			
			boardVO = this.boardService.readOneBoard(boardId);
			originFileName = boardVO.getOriginFileName();
			fileName = boardVO.getFileName();
			new DownloadUtil(this.uploadFilePath + File.separator + fileName)
			 .download(request, response, originFileName);
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
	
	@GetMapping("/board/boardUpdate.do/{boardId}")
	public ModelAndView viewBoardUpdatePage(
			@PathVariable int boardId
			, @SessionAttribute(Session.USER) MemberVO memberVO) {
		
		BoardVO boardVO = null;
		try {
			boardVO = this.boardService.readOneBoard(boardId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView(HttpRequestHelper.getJspPath());
		view.addObject("boardVO", boardVO);
		return view;
	}
	
	@PostMapping("/board/boardUpdate.do/{boardId}")
	public ModelAndView doBoardUpdateAction(
			@PathVariable int boardId
			, @Valid @ModelAttribute BoardVO boardVO
			, Errors errors
			, HttpSession session) {
		
		ModelAndView view = new ModelAndView("redirect:/board/boardDetail.do/" + boardId);
		
		if ( errors.hasErrors() ) {
			view.setViewName("board/boardUpdate.do");
			view.addObject("boardVO", boardVO);
			return view;
		}
		
		MultipartFile uploadFile = boardVO.getFile();
		
		if ( !uploadFile.isEmpty() ) {
			// 실제 파일 이름
			String originFileName = uploadFile.getOriginalFilename();
			// 파일 시스템에 저장될 파일의 이름(난수)
			String fileName = UUID.randomUUID().toString();
			
			File uploadDir = new File(this.uploadFilePath);
			
			// 폴더가 존재하지 않는다면 생성
			if ( !uploadDir.exists() ) {
				uploadDir.mkdirs();
			}
			
			// 파일이 업로드될 경로 지정
			File destFile = new File(this.uploadFilePath, fileName);
						
			try {
				// 업로드
				uploadFile.transferTo(destFile);
				// DB에 File 정보 저장하기 위한 정보 셋팅
				boardVO.setOriginFileName(originFileName);
				boardVO.setFileName(fileName);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		MemberVO loginMemberVO = (MemberVO) session.getAttribute(Session.USER);
		String email = loginMemberVO.getEmail();
		boardVO.setMemberVO(loginMemberVO);
		boardVO.setEmail(email);
		
		try {
			boolean isSuccess = this.boardService.updateOneBoard(boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	@RequestMapping("/board/boardDelete.do/{boardId}")
	public String doBoardDeleteAction(
			@PathVariable int boardId
			, @SessionAttribute(Session.USER) MemberVO memberVO) {
		
		try {
			boolean isSuccess = this.boardService.deleteOneBoard(boardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/board/boardImageFileUpload.do")
	@ResponseBody
	public Map<String, Object> doBoardImageFileUploadAction(
			MultipartHttpServletRequest multiFile) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		MultipartFile uploadFile = multiFile.getFile("upload");
		
		if ( !uploadFile.isEmpty() ) {
        	String fileName = UUID.randomUUID().toString();
        	
        	 File uploadDir = new File(this.uploadFilePath);
             if ( !uploadDir.exists() ) {
                uploadDir.mkdirs();
             }
             
             File destFile = new File(this.uploadFilePath, fileName);

             try {
            	 uploadFile.transferTo(destFile);
             } catch (IllegalStateException | IOException e) {
            	 throw new RuntimeException(e.getMessage(), e);
             } finally {
            	 if ( destFile.exists() ) {
            		 ExtensionFilter filter = ExtensionFilterFactory.getFilter(ExtFilter.APACHE_TIKA);
            		 boolean isImageFile = filter.doFilter(destFile.getAbsolutePath()
							            				 ,"image/jpg"
							                             ,"image/bmp"
							                             ,"image/jpeg"
							                             ,"image/gif"
							                             ,"image/png" );
            		 
            		 if ( !isImageFile ) {
            			 destFile.delete();
            			 throw new RuntimeException("이미지 파일이 아닙니다.");
            		 }            		 
            	 }
             }
             result.put("uploaded", true);
             result.put("url", "/board/boardImageDownloaded.do/" + fileName);
             
             return result;
        }
        throw new RuntimeException("파일이 존재하지 않습니다.");		
	}
	
	@RequestMapping("/board/boardImageDownloaded.do/{fileName}")
	public void BoardImageDownload(
			@PathVariable String fileName
			, HttpServletRequest request
			, HttpServletResponse response) {
		
		try {
			new DownloadUtil(this.uploadFilePath + File.separator + fileName).download(request, response, fileName);
		} catch ( UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
	}
	
}
