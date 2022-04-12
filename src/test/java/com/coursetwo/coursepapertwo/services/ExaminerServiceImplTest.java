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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    private ExaminerServiceImpl service;
    private Question ques1;
    private Question ques2;
    private Question ques3;
    private Question ques4;
    private Question ques5;
    private Question ques6;
    private Set<Question> questionSet1;
    private Set<Question> questionSet2;


    @Mock
    public JavaQuestionService jService;

    @Mock
    public MathQuestionService mathService;

    @BeforeEach
    public void setUp(){
        ques1 = new Question("Какая погода?", "Ясно");
        ques2 = new Question("Как дела?", "Отлично");
        ques3 = new Question("Какой курс?", "3");
        ques4 = new Question("Вопрос1", "Ответ1");
        ques5 = new Question("Вопрос2", "Ответ2");
        ques6 = new Question("Вопрос3", "Ответ3");
        questionSet1 = new HashSet<>(Set.of(ques1, ques2, ques3));
        questionSet2 = new HashSet<>(Set.of(ques4, ques5, ques6));
        service = new ExaminerServiceImpl(jService, mathService);
    }

    @Test
    public void getQuestions(){
        assertNotNull(jService);
        assertNotNull(mathService);

        Mockito.when(jService.getAll()).thenReturn(questionSet1);
        Mockito.when(mathService.getAll()).thenReturn(questionSet2);

        Assertions.assertEquals(jService.getAll().size(), 3);
        Assertions.assertEquals(mathService.getAll().size(), 3);
        Collection<Question> questions = service.getQuestions(1);
        Assertions.assertEquals( questions.size(),1);

        Mockito.when(jService.getRandomQuestion()).thenReturn(ques1);
        Mockito.when(mathService.getRandomQuestion()).thenReturn(ques2);
        questions = service.getQuestions(2);
        Assertions.assertTrue(questions.contains(ques1));
        Assertions.assertTrue(questions.contains(ques2));
    }

    @Test
    public void getQuestionsBadRequestException(){
        assertNotNull(jService);
        assertNotNull(mathService);

        Mockito.when(jService.getAll()).thenReturn(questionSet1);
        Mockito.when(mathService.getAll()).thenReturn(questionSet2);

        Assertions.assertThrows(BadRequestException.class, () -> service.getQuestions(-1));
        Assertions.assertThrows(BadRequestException.class, () -> service.getQuestions(100));
    }
}
