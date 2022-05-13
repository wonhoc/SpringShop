package com.spring.shop.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogInController {

	@Autowired
	private MemberService MemberService;
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String login(HttpServletRequest req) {
		req.setAttribute("content", "login.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/registerUserForm", method = RequestMethod.GET)
	public String join(HttpServletRequest req) {
		req.setAttribute("content", "registerUser.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest req, MemberDTO memberDto) {
		int r = MemberService.registerMember(memberDto);
		if(r > 0) {
			req.setAttribute("MSG", "회원가입성공");
		}
		req.setAttribute("content", "login.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.POST)
	public String loginSucces(HttpServletRequest req, MemberDTO memberDTO) {
		MemberDTO user = MemberService.retrieveMember(memberDTO);
		if(user != null) {
			req.getSession().setMaxInactiveInterval(60*60);
			req.getSession().setAttribute("user", user);
			req.setAttribute("content", "main.jsp");
		}else {
			req.setAttribute("content", "login.jsp");
		}
		return "home";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		req.setAttribute("content", "main.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/detailMember", method = RequestMethod.GET)
	public String detailMember(HttpServletRequest req) {
		req.setAttribute("content", "detailMember.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/checkPwForm", method = RequestMethod.GET)
	public String checkPwForm(HttpServletRequest req) {
		req.setAttribute("content", "checkPw.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/checkPw", method = RequestMethod.POST)
	public String checkPw(HttpServletRequest req, @RequestParam("mi_pw") String mi_pw) {
		MemberDTO user = (MemberDTO)req.getSession().getAttribute("user");
		String mi_id = user.getMi_id();
		String user_pw = MemberService.retrievePw(mi_id);
		if (mi_pw == user_pw) {
			req.setAttribute("content", "editMember.jsp");
		}else{
			req.setAttribute("MSG", "비밀번호가 다릅니다.");
		}
		return "home";
	}
	
	@RequestMapping(value = "/editForm", method = RequestMethod.GET)
	public String editMemberForm(HttpServletRequest req) {
		req.setAttribute("content", "editMember.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/editMember", method = RequestMethod.POST)
	public String editMember(HttpServletRequest req, MemberDTO memberDTO) {
		int r = MemberService.editMember(req ,memberDTO);
		if(r > 0) {
			MemberDTO member = MemberService.retrieveMember(memberDTO);

			req.getSession().setAttribute("user", member);
			req.setAttribute("MSG", "정보 수정 완료!");
		}
		req.setAttribute("content", "detailMember.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public String deleteMember(HttpServletRequest req) {
		MemberDTO member = (MemberDTO)req.getSession().getAttribute("user");
		int r = MemberService.removeMember(member.getMi_id());
		if(r > 0) {
			req.setAttribute("MSG", "회원탈퇴 성공!");
		}
		req.setAttribute("content", "main.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/idConfirm", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(@RequestParam("mi_id") String mi_id) {
		Map<String, String> map = new HashMap<String, String>();
		
		
		String r = MemberService.idCheck(mi_id); 
		System.out.println(r);
		return r;
	}
}
