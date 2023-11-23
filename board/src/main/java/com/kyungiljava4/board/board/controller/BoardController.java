package com.kyungiljava4.board.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungiljava4.board.board.domain.Board;
import com.kyungiljava4.board.board.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;

	@GetMapping("/")
	public String boardMainPage(@RequestParam(name="page",defaultValue = "1") int paging,  Model model) {
		int pagingcount=boardService.pagingNumber();
		model.addAttribute("page",pagingcount);
		model.addAttribute("title", "게시판");
		model.addAttribute("path", "/board/index");
		model.addAttribute("content", "boardFragment");
		model.addAttribute("contentHead", "boardFragmentHead");
		model.addAttribute("list", boardService.getAll(paging,10));

		return "/basic/layout";
	}
	@GetMapping("/content")
	public String boardnextPage(Model model) {
		model.addAttribute("title", "게시글");
		model.addAttribute("path", "/board/index");
		model.addAttribute("content", "contentFragment");
		model.addAttribute("contentHead", "contentFragmentHead");
		return "/basic/layout";
	}
	@PostMapping("/add")
	public String add(@RequestParam Map<String, String> data, HttpSession session) {
		if(session.getAttribute("userName")!=null) {
		int userId=(Integer)session.getAttribute("userId");
		boardService.add(new Board(
				userId,
				data.get("title"),
				data.get("content")));
		}
		return "redirect:/";
	}

	@GetMapping("/notice")
	public String noticePage(Model model) {
		model.addAttribute("title", "공지사항");
		model.addAttribute("path", "/board/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		return "/basic/layout";
	}
}
