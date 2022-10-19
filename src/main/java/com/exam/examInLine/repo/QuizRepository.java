package com.exam.examInLine.repo;

import com.exam.examInLine.model.exam.Category;
import com.exam.examInLine.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(Category category);

    public List<Quiz> findByActive(Boolean b);

    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
}
