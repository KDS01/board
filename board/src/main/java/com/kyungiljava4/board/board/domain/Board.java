package com.kyungiljava4.board.board.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Board {
	private int id;
	@NonNull
	private int userId;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private int views = 0;
	private int likes = 0;
	private int hates = 0;
	private Date createdAt;
	private boolean isWithdrew = false;
	private String userName;
	private String git_address;
}
