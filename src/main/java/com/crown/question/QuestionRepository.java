package com.crown.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//상위 인터페이스의 모든 메소드가 상속 되어 내려옴
	/* JpaRepository 에서 선언된 메소드가 상속되어 내려옴 
	 
	   findAll()  : select * from Cs  
	   		List<Cs> q = CsRepository.findAll() ; 
	   		 
	   findById(1) : select * from Cs where id = 1 ; 
	   		Optional<Cs> op = CsRepository.findById(1) ; 
	   
	   save()     : insert,  update 
	   delete()   : delete 
	 */
	
	// 제목으로 검색 메소드 생성  : 
		//테이블의 Primary Key 컬럼외의 컬럼은 조건으로 검색할 경우 메소드를 만들어야함.  
	//select * from Cs where subject = "?" ;  
	List<Question> findByTitle(String title); 
	
	
	// 내용으로 검색 
	// select * from Cs where content = "?" ; 
	List<Question> findByContent(String content); 
	
	//제목으로 검색 : like
	// select * from Cs where subject like %?% ; 
	List<Question> findByTitleLike(String title); 
		// List<Cs> list = CsRepository.findBySubjectLike("%JPA%");
	
	
	//내용으로 검색 : like
	// select * from content where subject like %?% ; 
	List<Question> findByContentLike(String content); 
	
	//제목 과 내용으로 검색 
	//select * from Cs where subject = "?" and content = "?"
	List<Question> findByTitleAndContent(String title, String content); 
	
	// 페이징 처리 : Page <Cs>  findAll(Pageable pageable)
	// Page : 페이징을 처리하기 위한 클래스 
	// Pageable : 페이징 처리하는 인터페이스 
	Page<Question> findAll(Pageable pageable); 
	
	@Query("select "
            + "distinct q "
            + "from Question q " 
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
	
}
