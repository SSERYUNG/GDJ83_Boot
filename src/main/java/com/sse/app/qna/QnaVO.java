package com.sse.app.qna;

import java.sql.Date;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref;
	private Long step;
	private Long depth;

}