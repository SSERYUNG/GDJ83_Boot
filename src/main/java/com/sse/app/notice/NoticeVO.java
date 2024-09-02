package com.sse.app.notice;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class NoticeVO {

	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	
}
