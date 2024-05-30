package com.exam.system.services;

import com.exam.system.dtos.exam.exam.ExamByUserIdRequestDto;
import com.exam.system.dtos.exam.exam.ExamByUserIdResponseDto;
import com.exam.system.exceptions.ExamNotFoundException;
import com.exam.system.models.*;
import com.exam.system.models.Module;
import com.exam.system.repositories.ExamRepository;
import com.exam.system.repositories.StudentExamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentExamService {
    private final UserService userService;
    private final StudentModuleService studentModuleService;
    private final StudentQuestionService studentQuestionService;
    private final StudentExamRepository studentExamRepository;

    public StudentExamService(UserService userService,
                              StudentModuleService studentModuleService,
                              StudentQuestionService studentQuestionService,
                              StudentExamRepository studentExamRepository,
                              ExamRepository examRepository) {
        this.userService = userService;
        this.studentModuleService = studentModuleService;
        this.studentQuestionService = studentQuestionService;
        this.studentExamRepository = studentExamRepository;
    }

    public void assignExamsToUserId(User student, Exam exam) {
        // Exams will be created once admin has assigned it to student, for now assigning all exams to the student

        // Assign Exam
        StudentExam studentExam = new StudentExam(student, exam);
        studentExamRepository.save(studentExam);

        //Assign Modules
        int moduleSequence = 0;
        for(Module module : exam.getModules()) {
            moduleSequence++;
            StudentModule studentModule = studentModuleService.createNewStudentModule(studentExam, module, moduleSequence);

            //Assign Questions
            int questionSequence = 0;
            for(Question question : module.getQuestions()) {
                questionSequence++;
                studentQuestionService.createNewStudentQuestion(studentModule, question, questionSequence);
            }
        }
    }

    public List<StudentExam> getAllExamsByUserId(long userId) {
        List<StudentExam> examResponseList = new ArrayList<>();

        User student = userService.getUserById(userId);

        return studentExamRepository.findAllByStudent(student);
    }

    public StudentExam getStudentExamById(long examId) {
        Optional<StudentExam> exam = studentExamRepository.findById(examId);

        if(exam.isEmpty())
            throw new ExamNotFoundException();

        return exam.get();
    }

    public boolean isExamAssignedToStudent(Exam exam, User student) {
        Optional<StudentExam> examOptional = studentExamRepository.findByExamAndStudent(exam, student);

        if(examOptional.isEmpty())
            return false;

        return true;
    }

    public StudentExam saveStudentExam(StudentExam exam) {
        return studentExamRepository.save(exam);
    }
}
