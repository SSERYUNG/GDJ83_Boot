package com.sse.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sse.app.util.Pager;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("list")
	public void getList(Pager pager, Model model)throws Exception{
		List<QnaVO> ar = qnaService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		log.info("Pager====== : {}", pager);
		
	}
	
	@GetMapping("add")
	public void add() throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO) throws Exception{
		int result = qnaService.add(qnaVO);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO, Model model) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("qnaVO", qnaVO);
	}
	
}