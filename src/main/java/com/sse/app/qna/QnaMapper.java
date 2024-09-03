package com.sse.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sse.app.util.Pager;

@Mapper
public interface QnaMapper {

	public List<QnaVO> getList(Pager pager) throws Exception;
	
	public int add(QnaVO qnaVO) throws Exception;
	
	public int refUpdate(QnaVO qnaVO) throws Exception;
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
}