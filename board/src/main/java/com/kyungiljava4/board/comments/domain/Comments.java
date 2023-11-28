package com.kyungiljava4.board.comments.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comments {
	private int id;
	@NonNull
	private String content;
	private Timestamp createAt;
	private boolean withdrew = false;
	private final int user_id;
	private final int board_id;
	private int comment_id;
	private String userName;
	private List<Comments> children;
}
