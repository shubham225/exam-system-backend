package com.exam.system.controllers;

import com.exam.system.dtos.exam.exam.ExamByUserIdRequestDto;
import com.exam.system.dtos.exam.exam.ExamByUserIdResponseDto;
import com.exam.system.dtos.exam.module.ModuleByExamIdResponseDto;
import com.exam.system.dtos.exam.question.QuestionByModuleIdResponseDto;
import com.exam.system.dtos.exam.question.StudentQuestionResponseDto;
import com.exam.system.dtos.exam.question.UpdateQuestionByIdRequestDto;
import com.exam.system.services.OnlineTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/test")
public class ExamController {
    private final OnlineTestService onlineTestService;

    public ExamController(OnlineTestService onlineTestService) {
        this.onlineTestService = onlineTestService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "user/{userId}/exam/{examId}"
    )
    public String assignExamsToUser(@PathVariable long userId,
                                    @PathVariable long examId) {
        return onlineTestService.assignExamsToUserId(userId, examId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "user/{id}/exams"
    )
    public List<ExamByUserIdResponseDto> getAllExamsByUserId(@PathVariable long id) {
        return onlineTestService.getAllExamsByUserId(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "exam/{id}/modules"
    )
    public List<ModuleByExamIdResponseDto> getAllModulesByExamId(@PathVariable long id) {
        return onlineTestService.getModulesByExamId(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "module/{id}/questions"
    )
    public List<QuestionByModuleIdResponseDto> getAllQuestionsByModuleId(@PathVariable long id) {
        return onlineTestService.getQuestionsByModuleId(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "question/{id}"
    )
    public StudentQuestionResponseDto getQuestionById(@PathVariable long id) {
        return onlineTestService.getQuestionById(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "question/{id}"
    )
    public StudentQuestionResponseDto updateQuestionById(@PathVariable long id,
                                                         @RequestBody UpdateQuestionByIdRequestDto question) {
        return onlineTestService.updateQuestionById(id, question);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "exam/{id}"
    )
    public ExamByUserIdResponseDto updateExamById(@PathVariable long id,
                                                  @RequestBody ExamByUserIdRequestDto exam) {
        return onlineTestService.updateExamById(id, exam);
    }
}
