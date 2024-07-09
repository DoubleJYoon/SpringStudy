package com.a.ezn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.a.ezn.repo.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String joinOk(MemberVO vo) {
		int result = repository.join(vo);
		if(result > 0) {
			return "redirect:/user/login.do";
		}else {
			return "redirect:/user/join.do";
		}
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
