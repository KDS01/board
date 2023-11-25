package com.kyungiljava4.board.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungiljava4.board.board.domain.Board;
import com.kyungiljava4.board.user.dao.UserDao;
import com.kyungiljava4.board.user.domain.User;

@Repository
public class BoardDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UserDao userDao;

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
					rs.getDate("createAt"),
					rs.getInt("is_withdrew")==1,
					rs.getString("name"),
					rs.getString("git_address")
					);
		}
	};
	public void add(Board board) {
		jdbcTemplate.update(
				"insert into boards (\"title\", \"content\", \"is_withdrew\", \"user_id\") values (?, ?, ?, ?)",
				board.getTitle(), board.getContent(),board.isWithdrew() ? 1:0, board.getUserId());
	}
	
	public List<Board> getAll(int page,int pageSize){
		int offset=(page-1) * pageSize;
//		return jdbcTemplate.query("select a.*,b.\"name\" from boards a join users b on a.\"user_id\"=b.\"id\" order by a.\"id\" offset ? rows fetch first ? rows only",new Object[] {offset,pageSize}, mapper);
		return jdbcTemplate.query("select a.*,b.\"name\",b.\"git_address\" from boards a join users b on a.\"user_id\"=b.\"id\" order by a.\"id\" offset ? rows fetch first ? rows only",mapper,offset,pageSize);
	}
	public int itemCounts() {
		int result=jdbcTemplate.queryForObject("select count(*) from boards",Integer.class);
		return result;
	}
	public Board get(int id) {
		return jdbcTemplate.queryForObject("select a.*,b.\"name\",b.\"git_address\" from boards a join users b on a.\"user_id\"=b.\"id\" where a.\"id\"=?",mapper,id);	
	}
	

}
