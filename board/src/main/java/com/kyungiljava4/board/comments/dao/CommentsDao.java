package com.kyungiljava4.board.comments.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kyungiljava4.board.board.dao.BoardDao;
import com.kyungiljava4.board.comments.domain.Comments;
import com.kyungiljava4.board.user.dao.UserDao;

@Repository
public class CommentsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	BoardDao boardDao;
	@Autowired
	UserDao userDao;
	
	private RowMapper<Comments> mapper= new RowMapper<Comments>() {
		
		@Override
		public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Comments(
					rs.getInt("id"),
					rs.getInt("board_id"),
					rs.getInt("user_id"),
					rs.getString("content"),
					rs.getTimestamp("createAt"),
					rs.getInt("comment_id")
					);
		}
	};
	
	public void add(Comments comments) {
		jdbcTemplate.update("insert into comments (\"board_id\", \"user_id\", \"content\", \"comment_id\") values (?, ?, ?, ?)",
				comments.getBoard_id(),
				comments.getUser_id(),
				comments.getContent(),
				comments.getComment_id()
				);
	}
	public Comments get(int id) {
		String query="select a.*,b.\"id\",c.\"id\" from comments a join users b on a.\"user_id\"=b.\"id\" join boards c on a.\"board_id\"=c.\"id\" where a.\"id\"=?";
		return jdbcTemplate.queryForObject(query, mapper,id);
	}
	

}
