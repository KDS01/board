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
	public Board get(int id) {
		return boardDao.get(id);
	}
	public int pagingNumber() {
	    int count= boardDao.itemCounts();
	    int pagingNumber=10;
	    
	    int result=(int)Math.ceil((double)count/pagingNumber);
	    return result;
	}
	public List<Board> getAll(int offset,int pagesize){
		return boardDao.getAll(offset,pagesize);
	}
}
