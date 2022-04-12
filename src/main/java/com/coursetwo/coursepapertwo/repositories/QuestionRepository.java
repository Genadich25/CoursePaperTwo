package com.coursetwo.coursepapertwo.repositories;

import com.coursetwo.coursepapertwo.data.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
