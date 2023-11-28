package com.kyungiljava4.board.comments.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kyungiljava4.board.comments.domain.ResponseComment;
import com.kyungiljava4.board.comments.domain.Comments;
import com.kyungiljava4.board.comments.service.CommentsService;
import com.kyungiljava4.board.user.domain.User;
import com.kyungiljava4.board.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	@Autowired
	CommentsService commentsService;
	@Autowired
	UserService userService;
	@GetMapping("")
	@ResponseBody
	public ResponseComment getComments(@RequestParam Map<String,String> map){
		ResponseComment res=new ResponseComment(commentsService.getComment(Integer.parseInt(map.get("boardId").trim()),Integer.parseInt(map.get("start").trim())),commentsService.getCount(Integer.parseInt(map.get("boardId").trim()))<= Integer.parseInt(map.get("start"))+5);
		return res;
	}
	@GetMapping("th")
	public String getThymeComment(Model model, @RequestParam Map<String,String> map) {
		model.addAttribute("commentList",commentsService.getComment(Integer.parseInt(map.get("boardId")), 0));
		return "/comment/list";
	}
	@PostMapping("add")
	public String addComments(@RequestParam Map<String, String> map, Model model)  {
		Comments comments= new Comments(
				map.get("content"),
				Integer.parseInt(map.get("user_id")),
				Integer.parseInt(map.get("board_id"))
				);
		if(map.get("comment_id")!=null) {
			comments.setComment_id(Integer.parseInt(map.get("comment_id")));
		}
		commentsService.add(comments);
		return "redirect:/board/" + map.get("board_id");
	}

}
