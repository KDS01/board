package com.kyungiljava4.board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungiljava4.board.board.dao.BoardDao;
import com.kyungiljava4.board.board.domain.Board;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public void add(Board board) {
		boardDao.add(board);
	}
	
	public List<Board> getAll(){
		return boardDao.getAll();
	}
}
