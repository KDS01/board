package com.kyungiljava4.board.comments.service;

import java.util.List;

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
	public List<Comments> getComment(int boardId,int start){
		List<Comments> list= commentsDao.getParent(boardId, start);
//		for(int i=0; i<list.size(); i++) {
//			list.get(i).getId();
//			list.get(i).setChildren(commentsDao.getChild(boardId, list.get(i).getId()));
//			
//		}
		list.forEach((item)->{
			item.setChildren(getChildren(boardId,item));
		});
		return list;
	}
	private List<Comments> getChildren(int boardId,Comments comments){
		List<Comments> list = commentsDao.getChildren(boardId, comments.getId());
		list.forEach((item)->{
			item.setChildren(getChildren(boardId, item));
		});
		return list;
	}
	
	

}
