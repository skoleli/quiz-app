package com.example.quiz_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_app.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

   
}
