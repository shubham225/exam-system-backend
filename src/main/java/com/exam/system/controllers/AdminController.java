package com.exam.system.controllers;

import com.exam.system.dtos.*;
import com.exam.system.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/exam"
    )
    public List<ExamResponseDto> getAllExams(){
        return adminService.getAllExams();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/exam/{examId}"
    )
    public ExamResponseDto getExamById(@PathVariable long examId){
        return adminService.getExamById(examId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/exam"
    )
    public ExamResponseDto createExam(@RequestBody ExamRequestDto examRequestDto){
        return adminService.createExam(examRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/exam/{examId}"
    )
    public ExamResponseDto modifyExamById(@PathVariable long examId,
                                      @RequestBody ExamRequestDto examRequestDto){
        return adminService.modifyExamById(examId, examRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/exam/{examId}"
    )
    public ExamResponseDto deleteExamById(@PathVariable long examId){
        return adminService.deleteExamById(examId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/exam/{examId}/module"
    )
    public List<ModuleResponseDto> getAllModulesByExamId(@PathVariable long examId){
        return adminService.getAllModulesByExamId(examId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/module/{moduleId}"
    )
    public ModuleResponseDto getModuleById(@PathVariable long moduleId){
        return adminService.getModuleById(moduleId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/module"
    )
    public ModuleResponseDto createModule(@RequestBody ModuleRequestDto moduleRequestDto){
        return adminService.createModule(moduleRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/module/{moduleId}"
    )
    public ModuleResponseDto modifyModuleById(@PathVariable long moduleId,
                                              @RequestBody ModuleRequestDto moduleRequestDto){
        return adminService.modifyModuleById(moduleId, moduleRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/module/{moduleId}"
    )
    public ModuleResponseDto deleteModuleById(@PathVariable long moduleId){
        return adminService.deleteModuleById(moduleId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/module/{moduleId}/question"
    )
    public List<QuestionResponseDto> getAllQuestionsByModuleId(@PathVariable long moduleId){
        return adminService.getAllQuestionsByModuleId(moduleId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/question/{questionId}"
    )
    public QuestionResponseDto getQuestionById(@PathVariable long questionId){
        return adminService.getQuestionById(questionId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/question"
    )
    public QuestionResponseDto createQuestion(@RequestBody QuestionRequestDto questionRequestDto){
        return adminService.createQuestion(questionRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/question/{questionId}"
    )
    public QuestionResponseDto modifyQuestionById(@PathVariable long questionId,
                                                  @RequestBody QuestionRequestDto questionRequestDto){
        return adminService.modifyQuestionById(questionId, questionRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/question/{questionId}"
    )
    public QuestionResponseDto deleteQuestionById(@PathVariable long questionId){
        return adminService.deleteQuestionById(questionId);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/question/{questionId}/option"
    )
    public List<OptionResponseDto> getAllOptionsByQuestionId(@PathVariable long questionId){
        return adminService.getAllOptionsByQuestionId(questionId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/question/{questionId}/option"
    )
    public List<OptionResponseDto> createOptionsByQuestionId(@PathVariable long questionId,
                                                             @RequestBody List<OptionRequestDto> optionRequestDto){
        return adminService.createOptionsByQuestionId(questionId, optionRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/option/{optionId}"
    )
    public OptionResponseDto modifyOptionById(@PathVariable long optionId,
                                              @RequestBody  OptionRequestDto optionRequestDto){
        return adminService.modifyOptionById(optionId, optionRequestDto);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/option/{optionId}"
    )
    public OptionResponseDto deleteOptionById(@PathVariable long optionId){
        return adminService.deleteOptionById(optionId);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/exam/{examId}/assign"
    )
    public UserResponseDto assignExamsToUsers(@PathVariable long examId,
                                              @RequestBody List<UserRequestDto> userRequestDto) {
        return adminService.assignExamsToUsersById(examId, userRequestDto);
    }
}
