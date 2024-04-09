package com.exam.system.services.util;

import com.exam.system.dtos.ExamRequestDto;
import com.exam.system.dtos.ModuleRequestDto;
import com.exam.system.dtos.OptionRequestDto;
import com.exam.system.dtos.QuestionRequestDto;
import com.exam.system.models.Exam;
import com.exam.system.models.Module;
import com.exam.system.models.Option;
import com.exam.system.models.Question;
import org.springframework.stereotype.Service;

@Service
public class CommonMethods {
    public void updateExamFromExamReqDto(Exam exam, ExamRequestDto examRequestDto) {
        exam.setName(examRequestDto.getName());
        exam.setDescription(examRequestDto.getDescription());
    }

    public void updateModuleFromModuleReqDto(Module module, ModuleRequestDto moduleRequestDto) {
        module.setName(moduleRequestDto.getName());
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
