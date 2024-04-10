package com.exam.system.services;

import com.exam.system.dtos.*;
import com.exam.system.enums.QuestionType;
import com.exam.system.models.*;
import com.exam.system.models.Module;
import com.exam.system.services.util.CommonMethods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final ExamService examService;
    private final ModuleService moduleService;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final UserService userService;
    private final CommonMethods commonMethods;

    public AdminService(ExamService examService, ModuleService moduleService, QuestionService questionService, OptionService optionService, UserService userService, CommonMethods commonMethods) {
        this.examService = examService;
        this.moduleService = moduleService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.userService = userService;
        this.commonMethods = commonMethods;
    }

    public List<ExamResponseDto> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        List<ExamResponseDto> examResponseDtos = new ArrayList<>();

        for (Exam exam : exams) {
            examResponseDtos.add(new ExamResponseDto(exam));
        }

        return examResponseDtos;
    }

    public ExamResponseDto getExamById(long examId) {
        Exam exam = examService.getExamById(examId);
        return new ExamResponseDto(exam);
    }

    public ExamResponseDto createExam(ExamRequestDto examRequestDto) {
        Exam exam = examService.createNewExam(examRequestDto.getName(),
                examRequestDto.getDescription());
        return new ExamResponseDto(exam);
    }

    public ExamResponseDto modifyExamById(long examId, ExamRequestDto examRequestDto) {
        Exam exam = examService.getExamById(examId);
        commonMethods.updateExamFromExamReqDto(exam, examRequestDto);

        return new ExamResponseDto(exam);
    }

    public ExamResponseDto deleteExamById(long examId) {
        Exam exam = examService.deleteExam(examId);
        return new ExamResponseDto(exam);
    }

    public List<ModuleResponseDto> getAllModulesByExamId(long examId) {
        List<Module> modules = moduleService.getAllModulesByExamId(examId);
        List<ModuleResponseDto> moduleResponseDtos = new ArrayList<>();

        for (Module module : modules) {
            ModuleResponseDto responseDto = new ModuleResponseDto(module);
            responseDto.setExamId(examId);
            moduleResponseDtos.add(responseDto);
        }

        return moduleResponseDtos;
    }

    public ModuleResponseDto getModuleById(long moduleId) {
        Module module = moduleService.getModuleById(moduleId);

        return new ModuleResponseDto(module);
    }

    public ModuleResponseDto createModule(ModuleRequestDto moduleRequestDto) {
        Module module = moduleService.createNewModule(moduleRequestDto.getExamId(),
                                                      moduleRequestDto.getName(),
                                                      moduleRequestDto.getDescription());
        return new ModuleResponseDto(module);
    }

    public ModuleResponseDto modifyModuleById(long moduleId, ModuleRequestDto moduleRequestDto) {
        Module module = moduleService.getModuleById(moduleId);
        commonMethods.updateModuleFromModuleReqDto(module, moduleRequestDto);

        return new ModuleResponseDto(module);
    }

    public ModuleResponseDto deleteModuleById(long moduleId) {
        Module module = moduleService.deleteModule(moduleId);

        return new ModuleResponseDto(module);
    }

    public List<QuestionResponseDto> getAllQuestionsByModuleId(long moduleId) {
        List<Question> questions = questionService.getAllQuestionsByModuleId(moduleId);
        List<QuestionResponseDto> questionResponseDtos = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponseDto responseDto = new QuestionResponseDto(question);
            questionResponseDtos.add(responseDto);
        }

        return questionResponseDtos;
    }

    public QuestionResponseDto getQuestionById(long questionId) {
        Question question = questionService.getQuestionById(questionId);

        return new QuestionResponseDto(question);
    }

    public QuestionResponseDto createQuestion(QuestionRequestDto questionRequestDto) {
        // TODO : handle Enums
        QuestionType questionType = QuestionType.SINGLE_CORRECT;
        Question question = questionService.createNewQuestion(questionRequestDto.getModuleId(),
                                                              questionRequestDto.getQuestionText(),
                                                              questionType);
        return new QuestionResponseDto(question);
    }

    public QuestionResponseDto modifyQuestionById(long questionId, QuestionRequestDto questionRequestDto) {
        Question question = questionService.getQuestionById(questionId);
        commonMethods.updateQuestionFromQuestionReqDto(question, questionRequestDto);

        return new QuestionResponseDto(question);
    }

    public QuestionResponseDto deleteQuestionById(long questionId) {
        Question question = questionService.deleteQuestion(questionId);

        return new QuestionResponseDto(question);
    }

    public List<OptionResponseDto> getAllOptionsByQuestionId(long questionId) {
        List<Option> options = optionService.getAllOptionsByQuestionId(questionId);
        List<OptionResponseDto> optionResponseDtos = new ArrayList<>();

        for (Option option : options) {
            OptionResponseDto responseDto = new OptionResponseDto(option);
            optionResponseDtos.add(responseDto);
        }

        return optionResponseDtos;
    }

    public List<OptionResponseDto> createOptionsByQuestionId(long questionId, List<OptionRequestDto> optionRequestDto) {
        List<OptionResponseDto> optionResponseDtos = new ArrayList<>();

        for(OptionRequestDto requestDto : optionRequestDto) {
            Option option = optionService.createNewOption(questionId,
                                                          requestDto.getOptionText(),
                                                          requestDto.isAnswer());
            optionResponseDtos.add(new OptionResponseDto(option));
        }
        return optionResponseDtos;
    }

    public OptionResponseDto modifyOptionById(long optionId, OptionRequestDto optionRequestDto) {
        Option option = optionService.getOptionById(optionId);
        commonMethods.updateOptionFromOptionReqDto(option, optionRequestDto);

        return new OptionResponseDto(option);
    }

    public OptionResponseDto deleteOptionById(long optionId) {
        Option option = optionService.deleteOption(optionId);

        return new OptionResponseDto(option);
    }

    public UserSignupResponseDto adminSignup(UserSignupRequestDto userSignupRequestDto) {
        User user = userService.createUser(userSignupRequestDto, "ADMIN");

        return new UserSignupResponseDto(user);
    }

    public UserResponseDto assignExamsToUsersById(long examId, List<UserRequestDto> userRequestDto) {
        return null;
    }
}