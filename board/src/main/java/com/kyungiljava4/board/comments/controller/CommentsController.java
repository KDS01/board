package com.kyungiljava4.board.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyungiljava4.board.comments.service.CommentsService;

@Controller
public class CommentsController {
	@Autowired
	CommentsService commentsService;
	@GetMapping("board/content")
	public String addComments() {
		return "";
	}

}
