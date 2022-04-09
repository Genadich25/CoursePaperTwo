package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import com.coursetwo.coursepapertwo.exceptions.NotFoundQuestionException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService{

    private Set<Question> questions = new HashSet<>(Set.of(
            new Question("Как дела?", "Отлично"),
            new Question("Что такое «переменная»?", "Переменная — это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные"),
            new Question("Что означает “инициализация”?", "Инициализация — присваивание какого-то значения переменной"),
            new Question("Что такое «цикл»?", "Цикл — конструкция языка, позволяющая выполнять один и тот же код многократно в зависимости от условий."),
            new Question("Дайте определение строке", "Строка это объект, Строку можно соединять с другой строкой это называется конкатанацией, Строка это неизменяемый массив символов"),
            new Question("Что такое «метод»? ", "Метод — блок кода, который выполняет определенную функцию и позволяет себя переиспользовать в нескольких местах без необходимости снова и снова писать один и тот же код.")));

    @Override
    public Question add(String question, String answer) {
        Question ques = new Question(question, answer);
        if(questions.contains(ques)){
            throw new BadRequestException();
        }
        questions.add(ques);
        return ques;
    }

    @Override
    public Question add(Question question) {
        if(question == null || questions.contains(question)){
            throw new BadRequestException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(question == null){
            throw new BadRequestException();
        }
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new NotFoundQuestionException();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Object[] arrQuestions = questions.toArray();
        int randomIndex = new Random().nextInt(arrQuestions.length);
        Question ques = (Question) arrQuestions[randomIndex];
        return ques;
    }
}
