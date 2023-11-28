package com.kyungiljava4.board.comments.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kyungiljava4.board.board.dao.BoardDao;
import com.kyungiljava4.board.comments.domain.Comments;
import com.kyungiljava4.board.user.dao.UserDao;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

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
					rs.getString("content"),
					rs.getTimestamp("createAt"),
					rs.getInt("is_withdrew")==1,
					rs.getInt("user_id"),
					rs.getInt("board_id"),
					rs.getInt("comment_id"),
					rs.getString("name"),
					null
					);
		}
	};
	@Transactional
	public void add(Comments comments) {
		jdbcTemplate.update("insert into comments (\"content\", \"user_id\", \"board_id\",\"comment_id\" ) values (?, ?, ?, ?)",
				comments.getContent(),
				comments.getUser_id(),
				comments.getBoard_id(),
				comments.getComment_id() > 0 ? comments.getComment_id() :null);
	}
	public Comments get(int id) {
		String query="select a.*,b.\"id\",c.\"id\" from comments a join users b on a.\"user_id\"=b.\"id\" join boards c on a.\"board_id\"=c.\"id\" where a.\"id\"=?";
		return jdbcTemplate.queryForObject(query, mapper,id);
	}
	public Comments getComByBoardId(int boardId) {
		String query="select a.*,b.\"id\",c.\"id\" from comments a join users b on a.\"user_id\"=b.\"id\" join boards c on a.\"board_id\"=c.\"id\" where a.\"id\"=?";
		return jdbcTemplate.queryForObject(query, mapper,boardId);
	}	
	public List<Comments> getParent(int board_id, int Start){
		String commentList="select comments.*,"
				+ "users.\"name\" FROM comments"
				+ " join users on comments.\"user_id\"=users.\"id\" "
				+ "where comments.\"board_id\"=? and \"comment_id\" "
				+ "is null order by comments.\"id\" desc offset ? rows fetch first 5 rows only";
//		String commentList="select * from comments "
//				+ "where \"board_id\"=? and "
//				+ "\"comment_id\" is null "
//				+ "order by \"id\" desc offset ? rows fetch first 5 rows only";
		return jdbcTemplate.query(commentList,mapper,board_id,Start);
	}
	public List<Comments> getChildren(int board_id, int comment_id){
		String com_comList="select comments.*,"
				+ "users.\"name\" from comments"
				+ " join users on comments.\"user_id\"=users.\"id\" "
				+ "where comments.\"board_id\" = ? and comments.\"comment_id\"=? order by comments.\"id\"";
		return jdbcTemplate.query(com_comList,mapper,board_id,comment_id);
	}
	public int getCountInboard(int boardId) {
		return jdbcTemplate.queryForObject("select count(*) from comments "
				+ "where \"board_id\"= ? and \"comment_id\" is null", Integer.class,boardId);
	}

}
