package com.coursetwo.coursepapertwo.repositories;

import com.coursetwo.coursepapertwo.data.Question;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> questions = new HashSet<>(Set.of(
            new Question("Название какого государства скрывается в математическом выражении А3?", "Куб А – Куба"),
            new Question("В каком европейском городе находится памятник нулю?", "В центре Будапешта, столицы Венгрии"),
            new Question("Сколько раз в году встает солнце?", "365"),
            new Question("Как называется функция, графиком которой является парабола?", "Квадратичная"),
            new Question("Чему равен 1 пуд?", "16кг"),
            new Question("Может ли страус назвать себя птицей? ", "он же не умеет говорить")));

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
}
