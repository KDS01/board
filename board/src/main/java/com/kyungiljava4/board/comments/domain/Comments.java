package com.kyungiljava4.board.comments.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Comments {
	private int id;
	private int board_id;
	private int user_id;
	@NonNull
	private String content;
	private Timestamp createAt;
	private int comment_id;

}
