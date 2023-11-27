package com.kyungiljava4.board.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungiljava4.board.board.domain.Board;
import com.kyungiljava4.board.board.service.BoardService;
import com.kyungiljava4.board.comments.domain.Comments;
import com.kyungiljava4.board.comments.service.CommentsService;
import com.kyungiljava4.board.user.domain.User;
import com.kyungiljava4.board.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	CommentsService commentsService;
	@Autowired
	UserService userService;

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
	@GetMapping("/board/{boardId}")//boardId를 라우터로써 받게 된다
	public String itemPage(@PathVariable("boardId")int boardId, Model model) {
		Board board=boardService.get(boardId);
		model.addAttribute("title",board.getTitle());
		model.addAttribute("path","/board/content");
		model.addAttribute("content","contentFragment");
		model.addAttribute("contentHead","contentFragmentHead");
		board.setContent(board.getContent().replace("\n", "<br />"));
		model.addAttribute("board",board);
		return "/basic/layout";
		
	}
//	@PostMapping("/board/{boardId}/comments")
//	public String addComments(@PathVariable("boardId")int boardId,@RequestParam Map<String,String> map, Model model,HttpSession session) {
//		if(session.getAttribute("userName")!=null) {
//		Board board=boardService.get(boardId);
//		Integer sessionId=(Integer)(session.getAttribute("userId"));
//		int userId=(sessionId!=null) ? sessionId.intValue():0;
//		User user=userService.get(userId);
//		Comments comments= new Comments();
//		comments.setBoard_id(board.getId());
//		comments.setUser_id(userId);
//		comments.setContent(map.get("comments"));
//		comments.setComment_id(0);
//		commentsService.add(comments);
//		model.addAttribute("nickname",user.getUserId());
//		model.addAttribute("comment",comments);
//		System.out.println(comments.getContent());
//		System.out.println(comments.getUser_id());
//		System.out.println(comments.getBoard_id());
//		}
//		return "redirect:/board/"+boardId;
//	}
}
