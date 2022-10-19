package com.exam.examInLine.service;

import com.exam.examInLine.model.exam.Question;
import com.exam.examInLine.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public  Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionsOfQuiz(Quiz quiz);
    public void deleteQestion(Long quesId);
}