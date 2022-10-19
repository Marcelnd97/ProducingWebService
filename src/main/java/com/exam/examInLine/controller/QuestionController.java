package com.exam.examInLine.controller;

import com.exam.examInLine.model.exam.Question;
import com.exam.examInLine.model.exam.Quiz;
import com.exam.examInLine.service.QuestionService;
import com.exam.examInLine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //Ajout de question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //Modification des question
    @PutMapping("/")
    public ResponseEntity update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //Recuperation des Questions
    @GetMapping("/")
    public ResponseEntity<?> questions(){
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    //Recupération des questions de n'importe quelle QuizId
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("quizId") Long quizId) {
       /*
       Quiz quiz = new Quiz();
        quiz.setqId(quizId);
        Set<Question> questionOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);
        */

        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions()))
        {
            list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //Recupération de toutes les questions de n'importe quelle QuizId

    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("quizId") Long quizId) {

       Quiz quiz = new Quiz();
        quiz.setqId(quizId);
        Set<Question> questionOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);

    }

    //Recuperation d'une seule question
    @GetMapping("/{questId}")
    public Question get(@PathVariable("questId") Long questId) {
        return this.questionService.getQuestion(questId);
    }

    //Suppression d'une question
    @DeleteMapping("/{questId}")
    public void deleteQuestion(@PathVariable("questId") Long questId) {
        this.questionService.deleteQestion(questId);
    }

    //Eval Quiz
//    @PostMapping("/eval-quiz")
//    public ResponseEntity<?> evalQuizzes(@RequestBody List<Question> questions){
//        System.out.println(questions);
//        return ResponseEntity.ok("Recevoir les question avec les réponses.");
//    }
}