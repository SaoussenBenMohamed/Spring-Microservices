package tn.vermeg.pfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import tn.vermeg.pfe.entites.Question;
import tn.vermeg.pfe.entites.User;
import tn.vermeg.pfe.repository.QuestionRepository;
import tn.vermeg.pfe.repository.UserRepository;
import tn.vermeg.pfe.services.QuestionService;

@CrossOrigin(origins = "*" )
@RestController
public class QuestionController {
	
	
	QuestionService questionService;
	@Autowired
	UserRepository userRepository ;
	@Autowired
	QuestionRepository questionRepository;
	
	
	
	/*
	 * @PostMapping("/create") public <QuestionCreateRequest> Question
	 * createQuestion(@RequestBody Question question){ return
	 * questionService.saveQuestion(question);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	 @PostMapping("/create")
	    public Question createQuestion( User user, @RequestBody Question questionCreateRequest){
	      User user1 = userRepository.findByUsername(user.getUsername());
	                
	        Question question = new Question();
	        question.setCreator(user1);
	        question.setContent(questionCreateRequest.getContent());
	        
	        

	       
	      
	        return questionRepository.save(question);
	    }
	       
}
