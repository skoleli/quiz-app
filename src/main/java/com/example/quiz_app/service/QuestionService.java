package com.example.quiz_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_app.Question;
import com.example.quiz_app.dao.QuestionDao;

@Service
public class QuestionService {

@Autowired
QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

}
