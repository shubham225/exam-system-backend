package com.exam.system.services;

import com.exam.system.exceptions.OptionNotFoundException;
import com.exam.system.models.Module;
import com.exam.system.models.Question;
import com.exam.system.models.Option;
import com.exam.system.repositories.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    private final OptionRepository optionRepository;
    private final QuestionService questionService;

    public OptionService(OptionRepository optionRepository,
                           QuestionService questionService) {
        this.optionRepository = optionRepository;
        this.questionService = questionService;
    }

    public List<Option> getAllOptionsByQuestionId(long questionId) {
        List<Option> optionList = optionRepository.findAllByQuestionId(questionId);

        if(optionList.isEmpty())
            throw new OptionNotFoundException();

        return optionList;
    }

    public Option getOptionById(long id) {
        Optional<Option> optionOptional = optionRepository.findById(id);

        if(optionOptional.isEmpty())
            throw new OptionNotFoundException();

        return optionOptional.get();
    }

    public Option createNewOption(long questionId,
                                  String optionText,
                                  boolean isAnswer) {
        Option option = new Option();
        option.setOptionText(optionText);
        option.setAnswer(isAnswer);
        option.setCreatedOn(new Date());
        option.setActive(true);

        Question question = questionService.getQuestionById(questionId);
        option.setQuestion(question);

        option = optionRepository.save(option);

        return option;
    }

    public Option modifyOption(Option option) {
        option = optionRepository.save(option);
        return option;
    }

    public Option deleteOption(long id) {
        Optional<Option> optionOptional = optionRepository.findById(id);

        if(optionOptional.isEmpty())
            throw new OptionNotFoundException();

        Option option = optionOptional.get();
        optionRepository.delete(option);

        return option;
    }
}
