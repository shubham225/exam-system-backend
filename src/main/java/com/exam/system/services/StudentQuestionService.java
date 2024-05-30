package com.exam.system.services;

import com.exam.system.exceptions.QuestionNotFoundException;
import com.exam.system.models.Question;
import com.exam.system.models.StudentModule;
import com.exam.system.models.StudentQuestion;
import com.exam.system.repositories.StudentQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQuestionService {

    private final StudentQuestionRepository studentQuestionRepository;

    public StudentQuestionService(StudentQuestionRepository studentQuestionRepository) {
        this.studentQuestionRepository = studentQuestionRepository;
    }

    public StudentQuestion createNewStudentQuestion(StudentModule studentModule, Question question, int sequence) {
        StudentQuestion studentQuestion = new StudentQuestion(studentModule, question, sequence);
        studentQuestion = studentQuestionRepository.save(studentQuestion);

        return studentQuestion;
    }

    public List<StudentQuestion> getAllQuestionsByModule(StudentModule module) {
        return studentQuestionRepository.findAllByModule(module);
    }

    public StudentQuestion getQuestionById(long id) {
        Optional<StudentQuestion> question = studentQuestionRepository.findById(id);

        if(question.isEmpty())
            throw new QuestionNotFoundException();

        return question.get();
    }

    public StudentQuestion saveQuestion(StudentQuestion question) {
        return studentQuestionRepository.save(question);
    }
}
