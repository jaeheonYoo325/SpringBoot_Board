package com.springboot.project.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.project.common.session.Session;
import com.springboot.project.common.utils.HttpRequestHelper;
import com.springboot.project.common.validator.MemberValidator;
import com.springboot.project.member.service.MemberService;
import com.springboot.project.member.vo.MemberVO;

@Controller
public class MemberController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/memberRegist.do")
	public String viewMemberRegistPage() {		
		return HttpRequestHelper.getJspPath();
	}
	
	@PostMapping("/member/memberRegist.do")
	public ModelAndView doMemberRegistAction(
			@Validated(value= {MemberValidator.Regist.class}) @ModelAttribute MemberVO memberVO
			, Errors errors
			, HttpServletRequest request
			, HttpServletResponse response ) {
				
		ModelAndView view = new ModelAndView("redirect:/member/memberLogin.do");
		
		if ( errors.hasErrors() ) {
			view.setViewName("member/memberRegist.do");
			view.addObject("memberVO", memberVO);
		}
		
		try {
			boolean isSuccess = this.memberService.RegistOneMembers(memberVO);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping("/member/emailDuplicate.do")
	@ResponseBody
	public Map<Object, Object> doCheckDuplicationOfMemberEmail(
			@RequestParam String email) {

		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			count = this.memberService.duplicateCheckMembers(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("count : " + count);
		map.put("cnt", count);
		
		return map;
	}
	
	@GetMapping("/member/memberLogin.do")
	public String viewMemberLoginPage() {
		return HttpRequestHelper.getJspPath();
	}
	
	/*
	 * @PostMapping("/member/memberLogin.do") public ModelAndView
	 * doMemberLoginAcion(
	 * 
	 * @Validated(value= {MemberValidator.Login.class}) @ModelAttribute MemberVO
	 * memberVO , Errors errors , HttpSession session) {
	 * 
	 * ModelAndView view = new ModelAndView("redirect:/main/index.do"); MemberVO
	 * loginMemberVO; try { loginMemberVO =
	 * this.memberService.readOneMembers(memberVO);
	 * 
	 * if ( loginMemberVO == null ) {
	 * 
	 * } loginMemberVO.setEmail(memberVO.getEmail());
	 * loginMemberVO.setPassword(memberVO.getPassword());
	 * 
	 * session.setAttribute(Session.USER, loginMemberVO); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return view;
	 * 
	 * }
	 */
	
	@RequestMapping("/member/memberAjax.do")
	@ResponseBody
	public Map<Object, Object> doMemberLoginAjax(
			@Validated(value= {MemberValidator.Login.class}) @ModelAttribute MemberVO memberVO
			, Errors errors
			, HttpSession session) throws Exception {
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		boolean loginYnFlag = false;
		MemberVO loginMemberVO = new MemberVO();
		
		loginMemberVO = this.memberService.readOneMembers(memberVO);
		
		if ( loginMemberVO == null ) {
			loginYnFlag = false;
			map.put("loginFail", loginYnFlag);			
			return map;
		} 

		loginMemberVO.setEmail(memberVO.getEmail());
		loginMemberVO.setPassword(memberVO.getPassword());
		
		loginYnFlag = true;
		map.put("loginSuccess", loginYnFlag);
		session.setAttribute(Session.USER, loginMemberVO);
		
		return map;
	}
	
	@GetMapping("/member/memberLogout.do")
	public String doMemberLogoutAction(HttpSession session) {
		
		session.invalidate();
		return "redirect:/main/index.do";
	}
	
	@GetMapping("/member/memberUpdate.do")
	public String viewMemberUpdatePage() {
		return HttpRequestHelper.getJspPath();
	}
	
	@PostMapping("/member/memberUpdate.do")
	public ModelAndView doMemberUpdateAction(
			@Validated(value= {MemberValidator.Update.class})
			@SessionAttribute(Session.USER) @ModelAttribute MemberVO memberVO
			, Errors errors) {
		
		ModelAndView view = new ModelAndView("redirect:/main/index.do");
		
		if ( errors.hasErrors() ) {
			view.setViewName("member/memberUpdate.do");
			view.addObject("memberVO", memberVO);
			return view;
		}
		
		try {
			boolean isSuccess = this.memberService.updateOneMembers(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;	
	}
}
