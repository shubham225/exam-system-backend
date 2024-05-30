package com.exam.system.services;

import com.exam.system.exceptions.ModuleNotFoundException;
import com.exam.system.models.Module;
import com.exam.system.models.StudentExam;
import com.exam.system.models.StudentModule;
import com.exam.system.repositories.StudentModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentModuleService {

    private final StudentModuleRepository studentModuleRepository;

    public StudentModuleService(StudentModuleRepository studentModuleRepository) {
        this.studentModuleRepository = studentModuleRepository;
    }

    public StudentModule createNewStudentModule(StudentExam exam, Module module, int sequence) {
        StudentModule studentModule = new StudentModule(exam, module, sequence);
        studentModule = studentModuleRepository.save(studentModule);

        return studentModule;
    }

    public List<StudentModule> getAllModulesByExam(StudentExam exam) {
        return studentModuleRepository.findAllByExam(exam);
    }

    public StudentModule getModuleById(long moduleId) {
        Optional<StudentModule> moduleOptional = studentModuleRepository.findById(moduleId);

        if(moduleOptional.isEmpty())
            throw new ModuleNotFoundException();

        return moduleOptional.get();
    }
}
