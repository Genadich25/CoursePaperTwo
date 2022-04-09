package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    private ExaminerServiceImpl service;
    private Question ques1;
    private Question ques2;
    private Question ques3;
    private Set<Question> questionSet;

    @Mock
    public JavaQuestionService jService;

    @BeforeEach
    public void setUp(){
        ques1 = new Question("Какая погода?", "Ясно");
        ques2 = new Question("Как дела?", "Отлично");
        ques3 = new Question("Какой курс?", "3");
        questionSet = new HashSet<>(Set.of(ques1, ques2, ques3));
        service = new ExaminerServiceImpl(jService);
    }

    @Test
    public void getQuestions(){
        assertNotNull(jService);

        Mockito.when(jService.getAll()).thenReturn(questionSet);
        Assertions.assertEquals(jService.getAll().size(), 3);

        Set<Question> set = (Set<Question>) service.getQuestions(1);

        Mockito.verify(jService,Mockito.times(1)).getRandomQuestion();
    }

    @Test
    public void getQuestionsBadRequestException(){
        assertNotNull(jService);

        Mockito.when(jService.getAll()).thenReturn(questionSet);

        Assertions.assertThrows(BadRequestException.class, () -> service.getQuestions(-1));
        Assertions.assertThrows(BadRequestException.class, () -> service.getQuestions(100));
    }


}
