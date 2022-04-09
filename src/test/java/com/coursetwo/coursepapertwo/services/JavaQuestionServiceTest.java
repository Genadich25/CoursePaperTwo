package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import com.coursetwo.coursepapertwo.exceptions.NotFoundQuestionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class JavaQuestionServiceTest {

    private JavaQuestionService service;
    private Question ques1;
    private Question ques2;
    private Set<Question> questionSet;

    @BeforeEach
    public void setUp(){
        ques1 = new Question("Какая погода?", "Ясно");
        ques2 = new Question("Как дела?", "Отлично");
        service = new JavaQuestionService();
        questionSet = (Set<Question>) service.getAll();
    }

    @Test
    public void add(){
        Assertions.assertFalse(questionSet.contains(ques1));
        service.add(ques1.getQuestion(), ques1.getAnswer());
        Assertions.assertTrue(questionSet.contains(ques1));
    }

    @Test
    public void addQuestion(){
        Assertions.assertFalse(questionSet.contains(ques1));
        service.add(ques1);
        Assertions.assertTrue(questionSet.contains(ques1));
    }

    @Test
    public void remove(){
        Assertions.assertTrue(questionSet.contains(ques2));
        service.remove(ques2);
        Assertions.assertFalse(questionSet.contains(ques2));
    }

    @Test
    public void getRandomQuestion(){
        Question question = service.getRandomQuestion();
        Assertions.assertTrue(service.getAll().contains(question));
    }

    @Test
    public void addBadRequestException(){
        Assertions.assertThrows(BadRequestException.class,() -> service.add(null));
        Assertions.assertThrows(BadRequestException.class,() -> service.add(ques2));
    }

    @Test
    public void removeBadRequestException(){
        Assertions.assertThrows(BadRequestException.class,() -> service.remove(null));
    }

    @Test
    public void removeNotFoundQuestionException(){
        Assertions.assertThrows(NotFoundQuestionException.class,() -> service.remove(ques1));
    }

}
