package com.example.boardPractice.boardPractice.board.domain;

import java.util.Date;
import java.util.Locale.Category;

import com.example.boardPractice.boardPractice.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
	private int id;
	private String title;
	private String content;
	private int view;
	private int likes;
	private int hates;
	private Date createAt;
	private User writer;
	private Category category;
	private boolean isWithdrew;
}
