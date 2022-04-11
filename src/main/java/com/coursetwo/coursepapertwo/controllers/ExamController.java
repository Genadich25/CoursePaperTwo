package com.coursetwo.coursepapertwo.controllers;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.services.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/")
public class ExamController {
    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/get")
    public Collection<Question> generateQuestions(@RequestParam("amount") int amount){
        return examinerService.getQuestions(amount);
    }
}
