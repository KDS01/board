package com.kyungiljava4.board.comments.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ResponseComment {
	private List<Comments> list;
	private boolean isEnd;

}
