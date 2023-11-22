package com.kyungiljava4.board.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungiljava4.board.board.domain.Board;

@Repository
public class BoardDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private RowMapper<Board> mapper = new RowMapper<Board>() {

		@Override
		public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Board(
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getInt("view"), 
					0, 
					0,
					rs.getDate("createdAt"),
					rs.getInt("is_withdrew") == 1);
		}
	};

	public void add(Board board) {
		jdbcTemplate.update(
				"insert into boards (\"title\", \"content\", \"is_withdrew\", \"user_id\") values (?, ?, ?, ?)",
				board.getTitle(), board.getContent(), 0, board.getId());
	}
	
	public List<Board> getAll(){
		return jdbcTemplate.query("select * from boards order by \"id\"", mapper);
	}
}
