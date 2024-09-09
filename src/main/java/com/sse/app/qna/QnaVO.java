package com.sse.app.qna;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QnaVO {
	
	private Long boardNum;
	@NotBlank//(message = "필수입니다~")
	private String boardWriter;
	@Length(min=1,max=5)
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> ar;

}