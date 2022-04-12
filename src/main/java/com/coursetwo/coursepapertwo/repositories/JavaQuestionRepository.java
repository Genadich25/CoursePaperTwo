package com.coursetwo.coursepapertwo.repositories;

import com.coursetwo.coursepapertwo.data.Question;
import com.coursetwo.coursepapertwo.exceptions.BadRequestException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository{

    private final Set<Question> questions = new HashSet<>(Set.of(
            new Question("Как дела?", "Отлично"),
            new Question("Что такое «переменная»?", "Переменная — это ячейка в памяти компьютера, которой можно присвоить имя и в которой можно хранить данные"),
            new Question("Что означает “инициализация”?", "Инициализация — присваивание какого-то значения переменной"),
            new Question("Что такое «цикл»?", "Цикл — конструкция языка, позволяющая выполнять один и тот же код многократно в зависимости от условий."),
            new Question("Дайте определение строке", "Строка это объект, Строку можно соединять с другой строкой это называется конкатанацией, Строка это неизменяемый массив символов"),
            new Question("Что такое «метод»? ", "Метод — блок кода, который выполняет определенную функцию и позволяет себя переиспользовать в нескольких местах без необходимости снова и снова писать один и тот же код.")));

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
