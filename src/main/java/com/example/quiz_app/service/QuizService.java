package com.example.quiz_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz_app.dao.QuestionDao;
import com.example.quiz_app.dao.QuizDao;
import com.example.quiz_app.model.Question;
import com.example.quiz_app.model.QuestionWrapper;
import com.example.quiz_app.model.Quiz;
import com.example.quiz_app.model.QuizResponse;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.getRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> wrappedQuestions = new ArrayList<>();

        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            wrappedQuestions.add(qw);
        }

        return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int right = 0;
        int i = 0;
        for (QuizResponse response : responses) {
            if ((response.getResponse().equals(questions.get(i).getRightAnswer()))) {
                right++;
            }

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
