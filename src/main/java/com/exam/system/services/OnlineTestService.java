package com.exam.system.services;

import com.exam.system.dtos.exam.exam.ExamByUserIdRequestDto;
import com.exam.system.dtos.exam.exam.ExamByUserIdResponseDto;
import com.exam.system.dtos.exam.module.ModuleByExamIdResponseDto;
import com.exam.system.dtos.exam.question.QuestionByModuleIdResponseDto;
import com.exam.system.dtos.exam.question.StudentQuestionResponseDto;
import com.exam.system.dtos.exam.question.UpdateQuestionByIdRequestDto;
import com.exam.system.enums.ExamStatus;
import com.exam.system.exceptions.ExamAlreadyAssignedException;
import com.exam.system.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OnlineTestService {
    private final StudentExamService studentExamService;
    private final StudentModuleService studentModuleService;
    private final StudentQuestionService studentQuestionService;
    private final OptionService optionService;
    private final ExamService examService;
    private final UserService userService;

    public OnlineTestService(StudentExamService studentExamService, StudentModuleService studentModuleService, StudentQuestionService studentQuestionService, OptionService optionService, ExamService examService, UserService userService) {
        this.studentExamService = studentExamService;
        this.studentModuleService = studentModuleService;
        this.studentQuestionService = studentQuestionService;
        this.optionService = optionService;
        this.examService = examService;
        this.userService = userService;
    }

    public String assignExamsToUserId(long studentId, long examId) {
        Exam exam = examService.getExamById(examId);
        User student = userService.getUserById(studentId);

        if(studentExamService.isExamAssignedToStudent(exam, student))
            throw new ExamAlreadyAssignedException();

        studentExamService.assignExamsToUserId(student, exam);

        return "done";
    }

    public List<ExamByUserIdResponseDto> getAllExamsByUserId(long userId) {
        List<ExamByUserIdResponseDto> examResponseList = new ArrayList<>();

        List<StudentExam> exams = studentExamService.getAllExamsByUserId(userId);

        for(StudentExam exam : exams) {
            examResponseList.add(new ExamByUserIdResponseDto(exam));
        }

        return examResponseList;
    }

    public List<ModuleByExamIdResponseDto> getModulesByExamId(long examId) {
        List<ModuleByExamIdResponseDto> moduleList = new ArrayList<>();
        StudentExam exam = studentExamService.getStudentExamById(examId);
        List<StudentModule> modules = studentModuleService.getAllModulesByExam(exam);

        for(StudentModule module : modules) {
            moduleList.add(new ModuleByExamIdResponseDto(module));
        }

        return moduleList;
    }

    public List<QuestionByModuleIdResponseDto> getQuestionsByModuleId(long moduleId) {
        List<QuestionByModuleIdResponseDto> questionList = new ArrayList<>();
        StudentModule module = studentModuleService.getModuleById(moduleId);

        List<StudentQuestion> questions = studentQuestionService.getAllQuestionsByModule(module);

        for(StudentQuestion question : questions) {
            questionList.add(new QuestionByModuleIdResponseDto(question));
        }

        return questionList;
    }

    public StudentQuestionResponseDto getQuestionById(long questionId) {
        StudentQuestion question = studentQuestionService.getQuestionById(questionId);

        return new StudentQuestionResponseDto(question);
    }

    public StudentQuestionResponseDto updateQuestionById(long questionId, UpdateQuestionByIdRequestDto question) {
        StudentQuestion studentQuestion = studentQuestionService.getQuestionById(questionId);
        studentQuestion.setStatus(question.getStatus());
        Option answer = null;

        if(question.getAnswer() != 0)
            answer = optionService.getOptionById(question.getAnswer());

        studentQuestion.setAnswer(answer);
        studentQuestion = studentQuestionService.saveQuestion(studentQuestion);

        return new StudentQuestionResponseDto(studentQuestion);
    }

    public ExamByUserIdResponseDto updateExamById(long id, ExamByUserIdRequestDto examDto) {
        StudentExam exam = studentExamService.getStudentExamById(id);

        if(exam.getStatus() == ExamStatus.PENDING && examDto.getStatus() == ExamStatus.IN_PROGRESS) {
            exam.setStartTime(new Date());
        } else if(exam.getStatus() == ExamStatus.IN_PROGRESS && examDto.getStatus() == ExamStatus.COMPLETED) {
            exam.setEndTime(new Date());
        }

        exam.setStatus(examDto.getStatus());

        exam = studentExamService.saveStudentExam(exam);

        return new ExamByUserIdResponseDto(exam);
    }
}
