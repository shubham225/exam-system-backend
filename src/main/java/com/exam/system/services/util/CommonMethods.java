package com.exam.system.services.util;

import com.exam.system.dtos.admin.exam.ExamRequestDto;
import com.exam.system.dtos.admin.module.ModuleRequestDto;
import com.exam.system.dtos.admin.option.OptionRequestDto;
import com.exam.system.dtos.admin.question.QuestionRequestDto;
import com.exam.system.models.Exam;
import com.exam.system.models.Module;
import com.exam.system.models.Option;
import com.exam.system.models.Question;
import org.springframework.stereotype.Service;

@Service
public class CommonMethods {
    public void updateExamFromExamReqDto(Exam exam, ExamRequestDto examRequestDto) {
        exam.setName(examRequestDto.getExamName());
        exam.setDescription(examRequestDto.getDescription());
        exam.setDuration(examRequestDto.getDuration());
    }

    public void updateModuleFromModuleReqDto(Module module, ModuleRequestDto moduleRequestDto) {
        module.setName(moduleRequestDto.getModuleName());
        module.setDescription(moduleRequestDto.getDescription());
    }

    public void updateQuestionFromQuestionReqDto(Question question, QuestionRequestDto questionRequestDto) {
        question.setQuestionText(questionRequestDto.getQuestionText());
    }

    public void updateOptionFromOptionReqDto(Option option, OptionRequestDto optionRequestDto) {
        option.setOptionText(optionRequestDto.getOptionText());
        option.setAnswer(optionRequestDto.isAnswer());
    }
}
