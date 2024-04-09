package com.exam.system.services;

import com.exam.system.exceptions.ModuleNotFoundException;
import com.exam.system.models.Exam;
import com.exam.system.models.Module;
import com.exam.system.repositories.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ExamService examService;

    public ModuleService(ModuleRepository moduleRepository,
                         ExamService examService) {
        this.moduleRepository = moduleRepository;
        this.examService = examService;
    }

    public List<Module> getAllModulesByExamId(long examId) {
        List<Module> moduleList = moduleRepository.findAllByExamId(examId);

        if(moduleList.isEmpty())
            throw new ModuleNotFoundException();

        return moduleList;
    }

    public Module getModuleById(long id) {
        Optional<Module> moduleOptional = moduleRepository.findById(id);

        if(moduleOptional.isEmpty())
            throw new ModuleNotFoundException();

        return moduleOptional.get();
    }

    public Module createNewModule(long examId,
                                  String name,
                                  String description) {
        Module module = new Module();
        module.setName(name);
        module.setDescription(description);
        module.setCreatedOn(new Date());
        module.setActive(true);

        Exam exam = examService.getExamById(examId);
        module.setExam(new HashSet<>());
        module.getExam().add(exam);

        module = moduleRepository.save(module);

        return module;
    }

    public Module modifyModule(Module module) {
        module = moduleRepository.save(module);
        return module;
    }

    public Module deleteModule(long id) {
        Optional<Module> moduleOptional = moduleRepository.findById(id);

        if(moduleOptional.isEmpty())
            throw new ModuleNotFoundException();

        Module module = moduleOptional.get();

        moduleRepository.delete(module);

        return module;
    }
}
