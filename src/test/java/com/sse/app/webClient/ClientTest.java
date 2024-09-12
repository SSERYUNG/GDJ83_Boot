package com.sse.app.webClient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sse.app.comments.PostVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
class ClientTest {
	
//	여러개 가져오기 Webclient
	@Test
	void webClientListTest() {
		
//	Webclient
//		디자인 패턴 중 빌더 패턴을 사용해서 객체를 만들었다
		WebClient webClient = WebClient.builder()
//								공통 url
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build();
//		메서드 형식이 get일 때로 연습중,,,
		Flux<PostVO> res = webClient.get()
//		위에서 baseUrl을 적었으면 이거 빼고 나머지 경로 적고, 위에서 안 적었으면 여기다 다 적어도 됨
				 .uri("posts")
//				 밑에는 응답에 대한 내용
				 .retrieve()
				 .bodyToFlux(PostVO.class);
		
//		res에서 꺼낸다는 메서드 (람다식 consumer) 여기서 변수 v는 PostVO 타입이거나 그 부모타입
		res.subscribe(v->{
			log.info("postVO:{}",v);
		});
	
	}
	
//	@Test
	void webClientTest() {
		
//	1개 가져오기 Webclient
//		디자인 패턴 중 빌더 패턴을 사용해서 객체를 만들었다
		WebClient webClient = WebClient.builder()
//								공통 url
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build();
//		메서드 형식이 get일 때로 연습중,,,
		Mono<PostVO> res = webClient.get()
//		위에서 baseUrl을 적었으면 이거 빼고 나머지 경로 적고, 위에서 안 적었으면 여기다 다 적어도 됨
				 .uri("posts/1")
//				 밑에는 응답에 대한 내용
				 .retrieve()
				 .bodyToMono(PostVO.class);
		
//		res에서 꺼낸다는 메서드
		PostVO postVO = res.block();
		
		log.info("postVO:{}",postVO);
		
	}

//	@Test
	void test1() {
		
//		RestTemplate(java에서 외부 데이터 요청하기)
		
		RestTemplate restTemplate = new RestTemplate();
		
//		파라미터 생성(키 하나에 여러개의 데이터가 담을 때 사용하는 map) post 방식일 때 바디에 담는 방법임
//		get 방식일때는 그냥 url 뒤에 적기
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("postId", "1");
//		요청 객체 생성
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
		
//		요청을 전송하고 응답을 처리하자
//		restTemplate.메서드형식
//		메서드형식ForEntity,메서드형식Object 종류가 2개! 뭘써도 되긴 하는데 차이가 있음
		ResponseEntity<PostVO[]> res =
				restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", PostVO[].class);
		
		PostVO[] result = res.getBody();
		log.info("Result:{}",result.length);
		
	}

}
