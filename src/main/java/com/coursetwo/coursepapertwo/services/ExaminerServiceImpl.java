package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final JavaQuestionService javaService;
    private final MathQuestionService mathService;

    public ExaminerServiceImpl(JavaQuestionService qService, MathQuestionService mathService) {
        this.javaService = qService;
        this.mathService = mathService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> randomQuestions = new HashSet<>();
        if(amount > javaService.getAll().toArray().length + mathService.getAll().toArray().length || amount <= 0 ){
            throw new BadRequestException();
        }
        int lengthJavaRepository = javaService.getAll().size();
        int lengthMathRepository = mathService.getAll().size();
        for (int i = 0; i < amount;) {
            double randomCount = Math.random();
            if(randomCount < 0.5 && lengthJavaRepository >= 0){
                Question ques = javaService.getRandomQuestion();
                if(!randomQuestions.contains(ques) || randomQuestions.toArray().length == 0) {
                    randomQuestions.add(ques);
                    i++;
                    lengthJavaRepository--;
                }
            } else if(randomCount > 0.5 && lengthMathRepository >= 0){
                Question ques = mathService.getRandomQuestion();
                if(!randomQuestions.contains(ques) || randomQuestions.toArray().length == 0) {
                    randomQuestions.add(ques);
                    i++;
                    lengthMathRepository--;
                }
            }
        }
        return randomQuestions;
    }
}
