package com.example.quiz_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_app.model.Question;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

   List<Question> findByCategory(String category);

   @Query(value="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
   List<Question> getRandomQuestionsByCategory(String category, int numQ);
}
