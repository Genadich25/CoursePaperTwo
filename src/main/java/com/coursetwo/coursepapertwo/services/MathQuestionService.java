package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import com.coursetwo.coursepapertwo.exceptions.NotFoundQuestionException;
import com.coursetwo.coursepapertwo.repositories.MathQuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final MathQuestionRepository repository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") MathQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        Question ques = new Question(question, answer);
        if(repository.getAll().contains(ques)){
            throw new BadRequestException();
        }
        repository.add(ques);
        return ques;
    }

    @Override
    public Question add(Question question) {
        if(question == null || repository.getAll().contains(question)){
            throw new BadRequestException();
        }
        repository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(question == null){
            throw new BadRequestException();
        }
        if (repository.getAll().contains(question)) {
            repository.remove(question);
            return question;
        }
        throw new NotFoundQuestionException();
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Object[] arrQuestions = repository.getAll().toArray();
        int randomIndex = new Random().nextInt(arrQuestions.length);
        Question ques = (Question) arrQuestions[randomIndex];
        return ques;
    }
}
