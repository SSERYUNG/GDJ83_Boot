package com.sse.app.comments;

import lombok.Data;

@Data
public class PostVO {

	private Long userId;
	private Long id;
	private String title;
	private String body;
	
}
