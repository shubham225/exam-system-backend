package com.exam.system.services;

import com.exam.system.dtos.exam.ExamRequestDto;
import com.exam.system.dtos.exam.ExamResponseDto;
import com.exam.system.dtos.module.ModuleRequestDto;
import com.exam.system.dtos.module.ModuleResponseDto;
import com.exam.system.dtos.option.OptionRequestDto;
import com.exam.system.dtos.option.OptionResponseDto;
import com.exam.system.dtos.question.QuestionRequestDto;
import com.exam.system.dtos.question.QuestionResponseDto;
import com.exam.system.dtos.user.UserRequestDto;
import com.exam.system.dtos.user.UserResponseDto;
import com.exam.system.dtos.user.UserSignupRequestDto;
import com.exam.system.dtos.user.UserSignupResponseDto;
import com.exam.system.enums.QuestionType;
import com.exam.system.exceptions.DataValidationError;
import com.exam.system.models.*;
import com.exam.system.models.Module;
import com.exam.system.services.util.CommonMethods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<ExamResponseDto> examResponseDto = new ArrayList<>();

        for (Exam exam : exams) {
            examResponseDto.add(new ExamResponseDto(exam));
        }

        return examResponseDto;
    }

    public ExamResponseDto getExamById(long examId) {
        Exam exam = examService.getExamById(examId);
        return new ExamResponseDto(exam);
    }

    public ExamResponseDto createExam(ExamRequestDto examRequestDto) {
        Exam exam = examService.createNewExam(examRequestDto.getExamName(),
                                              examRequestDto.getDescription());
        return new ExamResponseDto(exam);
    }

    public ExamResponseDto modifyExamById(long examId, ExamRequestDto examRequestDto) {
        Exam exam = examService.getExamById(examId);
        commonMethods.updateExamFromExamReqDto(exam, examRequestDto);
        exam = examService.modifyExam(exam);

        return new ExamResponseDto(exam);
    }

    public ExamResponseDto deleteExamById(long examId) {
        Exam exam = examService.deleteExam(examId);
        return new ExamResponseDto(exam);
    }

    public List<ModuleResponseDto> getAllModulesByExamId(long examId) {
        List<Module> modules = moduleService.getAllModulesByExamId(examId);
        List<ModuleResponseDto> moduleResponseDto = new ArrayList<>();

        for (Module module : modules) {
            ModuleResponseDto responseDto = new ModuleResponseDto(module);
            responseDto.setExamId(examId);
            moduleResponseDto.add(responseDto);
        }

        return moduleResponseDto;
    }

    public ModuleResponseDto getModuleById(long moduleId) {
        Module module = moduleService.getModuleById(moduleId);

        return new ModuleResponseDto(module);
    }

    public ModuleResponseDto createModule(ModuleRequestDto moduleRequestDto) {
        Module module = moduleService.createNewModule(moduleRequestDto.getExamId(),
                                                      moduleRequestDto.getModuleName(),
                                                      moduleRequestDto.getDescription());
        return new ModuleResponseDto(module);
    }

    public ModuleResponseDto modifyModuleById(long moduleId, ModuleRequestDto moduleRequestDto) {
        Module module = moduleService.getModuleById(moduleId);
        commonMethods.updateModuleFromModuleReqDto(module, moduleRequestDto);
        module = moduleService.modifyModule(module);

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

        validateQuestion(questionRequestDto);

        Question question = questionService.createNewQuestion(questionRequestDto.getModuleId(),
                                                              questionRequestDto.getQuestionText(),
                                                              questionType);

        Set<OptionResponseDto> optionsDto = createOptionsByQuestionId(question.getId(), questionRequestDto.getOptions());

        question = questionService.getQuestionById(question.getId());
        QuestionResponseDto responseDto = new QuestionResponseDto(question);
        responseDto.setOptions(optionsDto);

        return responseDto ;
    }

    private void validateQuestion(QuestionRequestDto questionRequestDto) {
        Set<OptionRequestDto> optionsDto = questionRequestDto.getOptions();

        if(optionsDto.size() < 2) {
            throw new DataValidationError("Question should have at least 2 options");
        }

        boolean found = false;

        for(OptionRequestDto o : optionsDto) {
            if(o.isAnswer()) {
                found = true;
                break;
            }
        }

        if(!found) throw new DataValidationError("Question should have at least one answer");

    }

    public QuestionResponseDto modifyQuestionById(long questionId, QuestionRequestDto questionRequestDto) {
        Question question = questionService.getQuestionById(questionId);
        commonMethods.updateQuestionFromQuestionReqDto(question, questionRequestDto);

        validateQuestion(questionRequestDto);
        Set<Option> toDelete = new HashSet<>();
        // Handle Deleted Options
        for(Option option : question.getOptions()) {
            boolean optionFound = false;

            for(OptionRequestDto optionDto : questionRequestDto.getOptions()) {
                if(option.getId() == optionDto.getId()) {
                    optionFound = true;
                    break;
                }
            }

            if(!optionFound) {
                optionService.deleteOption(option.getId());
            }
        }
//        question.getOptions().removeAll(toDelete);
        question = questionService.modifyQuestion(question);

        // Handle New / Modified Options
        for(OptionRequestDto optionDto : questionRequestDto.getOptions()) {
            if(optionDto.getId() == 0)
                optionService.createNewOption(question.getId(), optionDto.getOptionText(), optionDto.isAnswer());
            else
                modifyOptionById(optionDto.getId(), optionDto);
        }

        question = questionService.getQuestionById(questionId);

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

    public Set<OptionResponseDto> createOptionsByQuestionId(long questionId, Set<OptionRequestDto> optionRequestDto) {
        Set<OptionResponseDto> optionResponseDtos = new HashSet<>();

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

        option = optionService.modifyOption(option);

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