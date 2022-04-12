package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import com.coursetwo.coursepapertwo.exceptions.NotFoundQuestionException;
import com.coursetwo.coursepapertwo.repositories.JavaQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private JavaQuestionService service;
    private Question ques1;
    private Question ques2;
    private Set<Question> questionSet;

    @Mock
    public JavaQuestionRepository repository;

    @BeforeEach
    public void setUp(){
        ques1 = new Question("Какая погода?", "Ясно");
        ques2 = new Question("Как дела?", "Отлично");
        service = new JavaQuestionService(repository);
        questionSet = new HashSet<>(Set.of(
                new Question("Как дела?", "Отлично"),
                new Question("Вопрос2", "Ответ2"),
                new Question("Вопрос3", "Ответ3")
        ));
    }

    @Test
    public void add(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        service.add(ques1);
        questionSet.add(ques1);
        Assertions.assertEquals(service.getAll().size(), questionSet.size());
        Assertions.assertTrue(service.getAll().contains(ques1));
    }

    @Test
    public void addQuestion(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        service.add(ques1);
        questionSet.add(ques1);
        Assertions.assertEquals(service.getAll().size(), questionSet.size());
        Assertions.assertTrue(service.getAll().contains(ques1));
    }

    @Test
    public void remove(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        Assertions.assertTrue(service.getAll().contains(ques2));
        service.remove(ques2);
        questionSet.remove(ques2);
        Assertions.assertEquals(service.getAll().size(), questionSet.size());
        Assertions.assertFalse(service.getAll().contains(ques2));
    }

    @Test
    public void getRandomQuestion(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        Question question = service.getRandomQuestion();
        questionSet.add(question);
        Assertions.assertTrue(service.getAll().contains(question));
    }

    @Test
    public void addBadRequestException(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        Assertions.assertThrows(BadRequestException.class,() -> service.add(null));
        Assertions.assertThrows(BadRequestException.class,() -> service.add(ques2));
    }

    @Test
    public void removeBadRequestException(){
        Assertions.assertThrows(BadRequestException.class,() -> service.remove(null));
    }

    @Test
    public void removeNotFoundQuestionException(){
        Mockito.when(repository.getAll()).thenReturn(questionSet);
        Assertions.assertThrows(NotFoundQuestionException.class,() -> service.remove(ques1));
    }

}
