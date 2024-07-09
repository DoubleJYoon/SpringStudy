package com.a.ezn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a.ezn.repo.BoardRepository;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardRepository repository;
	
	@RequestMapping(value="/board.do", method=RequestMethod.GET)
	public String board(Model model, 
			HttpServletRequest request, 
			BoardVO vo) {
		
		List<BoardVO> slist = repository.getAllData();
		
		model.addAttribute("vo", slist);
		
		return "board";
	}
	
	@RequestMapping(value="/post.do", method=RequestMethod.GET)
	public String view(@RequestParam(name="sno", defaultValue="0") int sno,
			Model model) {
		
		BoardVO vo = repository.selectOne(sno);
		model.addAttribute("vo", vo);
		
		return "view";
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String write() {
		return "write";
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String writeOk(BoardVO vo) {
		
		int result = repository.insertOne(vo);
		System.out.println(vo);
		if(result >= 1) {
			return "redirect:/board/post.do?sno="+vo.getSno();
		}else {
			return "redirect:/board/board.do";
		}
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyBoard(
		@RequestParam(name="sno", required=false, defaultValue="0") int sno
		,Model model) {
		
		BoardVO vo = repository.selectOne(sno);
		model.addAttribute("vo", vo);
		
		return "modify";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modifyBoardOk(
			BoardVO vo
			/*@RequestParam(name="sno") int sno,
			@RequestParam("title") String title,
			@RequestParam("writer") String writer,
			@RequestParam("body") String body*/
			) {
		
		int result = repository.update(vo);
		if(result > 0) {
			return "redirect:/board/post.do?sno="+vo.getSno();
		}else {
			return "redirect:/board/board.do";
		}
	
	}
		
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String deleteBoard(
			@RequestParam(name="sno") int sno) {
		int result = repository.delete(sno);
		if(result >0) {
			return "redirect:/board/board.do";
		}else {
			return "redirect:/board/post.do?sno="+sno;
		}
	}
}
