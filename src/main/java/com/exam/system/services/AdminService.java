package com.exam.system.services;

import com.exam.system.dtos.*;

import java.util.List;

public class AdminService {
    public List<ExamResponseDto> getAllExams() {
    }

    public ExamResponseDto getExamById(int examId) {
    }

    public ExamResponseDto createExam(ExamRequestDto examRequestDto) {
    }

    public ExamResponseDto modifyExamById(int examId, ExamRequestDto examRequestDto) {
    }

    public ExamResponseDto deleteExamById(int examId) {
    }

    public List<ModuleResponseDto> getAllModules() {
    }

    public ModuleResponseDto getModuleById(int moduleId) {
    }

    public ModuleResponseDto createModule(ModuleRequestDto moduleRequestDto) {
    }

    public ModuleResponseDto modifyModuleById(int moduleId, ModuleRequestDto moduleRequestDto) {
    }

    public ModuleResponseDto deleteModuleById(int moduleId) {
    
}

    public List<ModuleResponseDto> getAllModulesByExamId(int examId) {
    }

    public List<QuestionResponseDto> getAllQuestionsByModuleId(int moduleId) {
    }

    public QuestionResponseDto getQuestionById(int questionId) {
    }

    public QuestionResponseDto createQuestion(QuestionRequestDto questionRequestDto) {
    }

    public QuestionResponseDto modifyQuestionById(int questionId, QuestionRequestDto questionRequestDto) {
    }

    public QuestionResponseDto deleteQuestionById(int questionId) {
    }

    public List<OptionResponseDto> getAllOptionsByQuestionId() {
    }

    public List<OptionResponseDto> createOptionsByQuestionId(int questionId, List<OptionRequestDto> optionRequestDto) {
    }

    public OptionResponseDto modifyOptionById(int optionId, OptionRequestDto optionRequestDto) {
    }

    public OptionResponseDto deleteOptionById(int optionId) {
    }

    public UserResponseDto assignExamsToUsersById(int examId, List<UserRequestDto> userRequestDto) {
    }
