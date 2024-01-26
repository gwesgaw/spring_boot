package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.BoardVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.handler.PagingHandler;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		 bsv.register(bvo);
		 return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String selectList(Model m, PagingVO pgvo) {
		log.info(">>>> pgvo >> {}", pgvo);
		//totalCount
		int totalCount = bsv.getTotalCount(pgvo);
		//PagingHandler 객체생성
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", bsv.getList(pgvo));
		//PagingHandler 객체보내기
		m.addAttribute("ph",ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno") long bno) {
		m.addAttribute("bvo", bsv.getDetail(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info(">>> modify >>> {} ", bvo);
		bsv.modify(bvo);
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") long bno, RedirectAttributes re) {
		log.info(">>> bno >> {} "+ bno);
		int isOk = bsv.delete(bno);
		re.addFlashAttribute("isDel",isOk);
		return "redirect:/board/list";
	}
}
