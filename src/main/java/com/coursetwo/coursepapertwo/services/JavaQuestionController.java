package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping( path = "/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService service;

    public JavaQuestionController(JavaQuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public Collection<Question> getAll(){
        return service.getAll();
    }

    @GetMapping(path = "/add")
    public Question add(@RequestParam String question, @RequestParam String answer){
        return service.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer){
        return service.remove(new Question(question, answer));
    }
}
