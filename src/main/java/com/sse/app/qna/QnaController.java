package com.sse.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sse.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController //컨틀롤러 어노테이션을 이걸 쓰면 이 컨트롤러의 모든 메서드에 @responsebody가 적용되는셈
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
	
	@CrossOrigin //cors 허용 어노테이션
	@GetMapping("list")
	public List<QnaVO> getList(Pager pager)throws Exception{
		
		List<QnaVO> ar = qnaService.getList(pager);

		return ar;
		
	}
	
	@GetMapping("add")
	public void add(QnaVO qnaVO) throws Exception{
		
	}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult, MultipartFile[] attaches) throws Exception{
		
		if(bindingResult.hasErrors()) {
			log.error("writer가 비어있단다");;
			return "qna/add";
		}
		
		int result = qnaService.add(qnaVO,attaches);
		return "redirect:./list";
	}
	
//	{}안은 파라미터로 오는 변수명 url 형식으로 파라미터를 적자(restful 방식), @PathVariable이랑 같이 쓰면 됨!
//	/ 뒤에 변수는 여러개 와도 괜찮음, 변수명만 잘 맞춰서 받으면 됨
	@GetMapping("detail/{boardNum}/{name}")
//	required 속성은 false면 boardNum이 필수로 안 와도 됨, value는 default값
	public QnaVO getDetail(@PathVariable(name = "boardNum") Long bn, @PathVariable String name, QnaVO qnaVO) throws Exception{
		
		log.info("보드넘:{}",bn);
		log.info("네임:{}",name);
		qnaVO = qnaService.getDetail(qnaVO);
		
		return qnaVO;
	}
	
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO,Model model) throws Exception {
		
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file", qnaFileVO);
		
		return "fileDownView";
	}
	
}