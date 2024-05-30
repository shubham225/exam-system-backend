package com.exam.system.services;

import com.exam.system.exceptions.ExamNotFoundException;
import com.exam.system.models.Exam;
import com.exam.system.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        List<Exam> examList = examRepository.findAll();

        if(examList.isEmpty())
            throw new ExamNotFoundException();

        return examList;
    }

    public Exam getExamById(long id) {
        Optional<Exam> examOptional = examRepository.findById(id);

        if(examOptional.isEmpty())
            throw new ExamNotFoundException();

        return examOptional.get();
    }

    public Exam createNewExam(String name,
                              String description,
                              int duration) {
        Exam exam = new Exam();
        exam.setName(name);
        exam.setDescription(description);
        exam.setDuration(duration);

        exam = examRepository.save(exam);

        return exam;
    }

    public Exam modifyExam(Exam exam) {
        exam = examRepository.save(exam);
        return exam;
    }

    public Exam deleteExam(long id) {
        Optional<Exam> examOptional = examRepository.findById(id);

        if(examOptional.isEmpty())
            throw new ExamNotFoundException();

        Exam exam = examOptional.get();
        examRepository.delete(exam);

        return exam;
    }
}
