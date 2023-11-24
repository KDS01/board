package com.kyungiljava4.board.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungiljava4.board.comments.dao.CommentsDao;
import com.kyungiljava4.board.comments.domain.Comments;

@Service
public class CommentsService {
	@Autowired
	CommentsDao commentsDao;
	
	public void add(Comments comments) {
		commentsDao.add(comments);
	}
	public Comments get(int id) {
		Comments comment=commentsDao.get(id);
		return comment;
	}
	

}
