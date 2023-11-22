package com.kyungiljava4.board.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungiljava4.board.board.dao.BoardDao;
import com.kyungiljava4.board.board.domain.Board;
import com.kyungiljava4.board.user.dao.UserDao;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	@Autowired
	UserDao userDao;
	
	public void add(Board board) {
		boardDao.add(board);
	}
	public List<String> getUserIdFromUsers(){
		List<Board> list=boardDao.getAll();
		List<String> IdList=new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			IdList.add(i,userDao.get(list.get(i).getUserId()).getUserId());
		}
		return IdList;
	}
	
	public List<Board> getAll(){
		return boardDao.getAll();
	}
}
