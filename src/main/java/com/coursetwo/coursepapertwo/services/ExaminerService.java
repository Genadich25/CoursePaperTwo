package com.coursetwo.coursepapertwo.services;

import com.coursetwo.coursepapertwo.data.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
