package com.exam.system.services;

import com.exam.system.enums.QuestionType;
import com.exam.system.exceptions.QuestionNotFoundException;
import com.exam.system.models.Module;
import com.exam.system.models.Question;
import com.exam.system.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModuleService moduleService;

    public QuestionService(QuestionRepository questionRepository,
                         ModuleService moduleService) {
        this.questionRepository = questionRepository;
        this.moduleService = moduleService;
    }

    public List<Question> getAllQuestionsByModuleId(long moduleId) {
        List<Question> questionList = questionRepository.findAllByModuleId(moduleId);

        if(questionList.isEmpty())
            throw new QuestionNotFoundException();

        return questionList;
    }

    public Question getQuestionById(long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if(questionOptional.isEmpty())
            throw new QuestionNotFoundException();

        return questionOptional.get();
    }

    public Question createNewQuestion(long moduleId,
                                      String questionText,
                                      QuestionType questionType) {
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setQuestionType(questionType);
        question.setCreatedOn(new Date());
        question.setActive(true);

        Module module = moduleService.getModuleById(moduleId);
        question.setModule(module);

        question = questionRepository.save(question);

        return question;
    }

    public Question deleteQuestion(long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if(questionOptional.isEmpty())
            throw new QuestionNotFoundException();

        Question question = questionOptional.get();
        questionRepository.delete(question);

        return question;
    }
}
