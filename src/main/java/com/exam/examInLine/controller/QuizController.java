package com.exam.examInLine.controller;

import com.exam.examInLine.model.exam.Category;
import com.exam.examInLine.model.exam.Quiz;
import com.exam.examInLine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //Ajout de Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));

        //return new ResponseEntity<>(quizService.addQuiz(quiz1), HttpStatus.CREATED);
    }

    //Modification des quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //Recuperation des Quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //Recuperation d'un seul quiz
    @GetMapping("/{quizId}")
    public Quiz quiz(@PathVariable("quizId")Long quizId){
        return this.quizService.getQuiz(quizId);
    }

    //Suppression de quiz
    @DeleteMapping("/{quizId}")
    public void delete(@PathVariable("quizId") Long quizId) {
        this.quizService.deleteQuiz(quizId);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long  cid){
        Category category =  new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }
    //Get active quizzes of categories

    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }

}
