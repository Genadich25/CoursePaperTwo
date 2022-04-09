package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final JavaQuestionService qService;

    public ExaminerServiceImpl(JavaQuestionService qService) {
        this.qService = qService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> randomQuestions = new HashSet<>();
        if(amount > qService.getAll().toArray().length || amount <= 0){
            throw new BadRequestException();
        }
        for (int i = 0; i < amount;) {
            Question ques = qService.getRandomQuestion();
            if(!randomQuestions.contains(ques) || randomQuestions.toArray().length == 0) {
                randomQuestions.add(ques);
                i++;
            }
        }
        return randomQuestions;
    }
}
