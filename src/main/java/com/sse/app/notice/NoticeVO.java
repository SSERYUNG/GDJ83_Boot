package com.sse.app.notice;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	
	private Long board_num;
	private String board_writer;
	private String board_contents;
	private Date create_date;

}
