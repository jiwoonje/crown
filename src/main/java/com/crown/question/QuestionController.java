package com.crown.question;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.crown.answer.AnswerDTO;
import com.crown.member.Member;
import com.crown.member.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequestMapping("/question")	// 하위 @GetMapping, @PostMapping 의 prefix 가 적용됨 
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	// @Autowired : 타입으로 객체를 주입함. 동일한 타입이 주입될 수 있다. , JUnit Test 
	
	// 생성자를 통한 객체 주입 : 
	//private final CsRepository CsRepository; 
	
	private final QuestionService questionService; 
	
	//http://localhost:8585/Cs/list
	@GetMapping("/list")
	//@ResponseBody
	public String list(Model model, 
			@RequestParam(value = "page", defaultValue="0") int page,
			@RequestParam(value = "kw", defaultValue = "") String kw
			
			) {
		
		//Model : 서버의 데이터를 client view 페이지로 전송 
		// 메소드 인풋 값으로 선언되면 객체가 자동으로 생성됨 
		
		// client 요청에 대한 비즈니스 로직 처리 : Cs 테이블의 list 를 출력 하라는 요청 
		//List<Cs> CsList = CsService.getList();  
		
		//페이징 처리된 객체를 받음 
		Page<Question> paging = questionService.getList(page, kw); 

		// paging 에 등록 되어 있는 중요 메소드 출력
		/*
		System.out.println("전체 레코드 수 : " + paging.getTotalElements());
		System.out.println("페이지당 출력 레코드 갯수 : " + paging.getSize());
		System.out.println("전체 페이지 갯수 : " + paging.getTotalPages());
		
		System.out.println("현재 요청 페이지 번호 : " + paging.getNumber());
		System.out.println("이전페이지 존재여부 : " + paging.hasPrevious());
		System.out.println("다음 페이지 존재여부 : " + paging.hasNext());
		*/ 

		 
		// model 에 담아서 client view 페이지로 전송 					
		//model.addAttribute("CsList", CsList); 
		
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		
		
		//templates/Cs_list.html  
		// thymeleaf 라이브러리 설치시 view 페이지가 위치할 곳, .html 
		return "getQuestionList" ; 	
	}
	
	//상세 글 조회 
	// http://localhost:8585/Cs/detail/{id}
	@GetMapping("/detail/{id}")
	public String detail (Model model, 
			@PathVariable("id") Integer id , 
			AnswerDTO answerDTO
			) {
		
		System.out.println(id);
		// 백엔드의 로직 처리 
		Question question = 
				questionService.getQuestion(id); 
		
		System.out.println(question.getTitle());
		System.out.println(question.getContent());
		
		
		// model 에 담아서 client로 전송 
		model.addAttribute("question", question); 
			
		return "getQuestion"; 
	}
	
	// 질문 등록 하기 : 글 등로 뷰 페이지만 전송
		// http://localhost:8585/question/create
		@GetMapping("/create")
		public String questionCreate(QuestionDTO QuestionDTO) {
			return "question_form";
		}

		// 질문등록 DB에 값을 받아서 저장
		// 인증된 사용자만 접근 가능
		// <=== Spring Security 설정 :SecurityConfig.java ,
		// @EnableMethodSecurity(prePostEnabled=true)
		// @PreAuthorize("isAuthenticated()")
		@PostMapping("/create")
		public String questionCreate(
//				@RequestParam("title") String title, 
//				@RequestParam("content") String content
				@Valid QuestionDTO QuestionDTO, BindingResult bindingResult) {

			Member member = new Member();
			System.out.println("###현재 로그온한 계정 : " + member.getName());

			if (bindingResult.hasFieldErrors()) {
				return "question_form";
			}

			/*
			 * System.out.println("제목 : " + title); System.out.println("내용 : " + content);
			 */


			questionService.create(QuestionDTO.getTitle(), QuestionDTO.getContent(), member);

			return "redirect:/question/list";
		}

		// 질문 정보를 가져와서 뷰 페이지로 값을 넣어줌
		// http://localhost:8585/question/modify/1
		@GetMapping("/modify/{id}")
		public String questionModify(QuestionDTO QuestionDTO, @PathVariable("id") Integer id, Principal principal) {
			// pincipal.getName(); : 현재 로그인한 사용자 정보를 출력
			// 넘어오는 id 변수를 가져와서 수정할 question 객체를 끄집어 옴
			// save() - insert
			// save() - update <== 기존의 DB의 레코드(Question)를 끄집어 내서 수정

			Question q = questionService.getQuestion(id);

			/*
			 * System.out.println("컨트롤러에서 제목 출력 : " + q.gettitle());
			 * System.out.println("컨트롤러에서 내용 출력 : " + q.getContent());
			 */

			// q 에 저장된 title , content 필드의 값을 QuestionDTO에 넣어서 클라이언트로 전송
			QuestionDTO.setTitle(q.getTitle());
			QuestionDTO.setContent(q.getContent());

			return "question_form";
		}

		// 질문 수정된 내용을 받아서 DB에 저장 , save() 기존의 question 객체를 끄집어내서 수정후 저장
		// http://localhost:8585/question/modify/1
		@PostMapping("/modify/{id}")
		public String questionModify(@Valid QuestionDTO QuestionDTO, BindingResult bindingResult,
				@PathVariable("id") Integer id, Principal principal) {

			// QuestionDTO 에 주입된 값을 확인
			if (bindingResult.hasErrors()) {
				return "question_form";
			}

			// 수정된 값을 DB에 저장하는 Service 메소드 호출
			// 수정할 Question 객체를 끄집어냄
			Question q = questionService.getQuestion(id);
			questionService.modify(q, QuestionDTO.getTitle(), QuestionDTO.getContent());

			// 수정 이후에 이동할 페이지
			return String.format("redirect:/question/detail/%s", id);
		}

		// 삭제 요청에 대한 처리
		@GetMapping("/delete/{id}")
		public String questionDelete(@PathVariable("id") Integer id, Principal principal) {
			// id 값을 가지고 Question 객체
			Question q = questionService.getQuestion(id);

			// URL를 사용해서 삭제 할 수 없도록 한다.
			// 현재 로그온한 계정과 DB의 저장된 username 과 같지 않을때 예외 발생
			if (!principal.getName().equals(q.getMid().getId())) {
				// 예외를 강제로 발생 김
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제할 권한이 없습니다. ");
			}

			// 삭제됨
			questionService.questionDelete(q);

			return "redirect:/";
		}

//		// 추천 기능 추가
//		@PreAuthorize("isAuthenticated()")
//		@GetMapping("/vote/{id}")
//		public String questioVoter(
//				// client 에서 넘어오는 값을 처리
//				@PathVariable("id") Integer id, Principal principal) {
//
//			// 서비스에 로직을 처리
//			// id 는 question 테이블의 id 컬럼의 값 으로 question 객체를 끄집어냄.
//			Question question = questionService.getQuestion(id);
//			// question voter 필드에 값을 주입 : Set<member>
//			// principal.getName() 으로 member 객체를 가지고 와야 함.
//			Member member = memberService.getUser(principal.getName());
//
//			// 투표 완료됨
//			questionService.vote(question, member);
//
//			// 뷰페이지로 전송
//
//			return String.format("redirect:/question/detail/%s", id);
//		}
		
}

