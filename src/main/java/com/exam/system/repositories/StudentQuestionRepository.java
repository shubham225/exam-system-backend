package com.exam.system.repositories;

import com.exam.system.models.StudentModule;
import com.exam.system.models.StudentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, Long> {
    public List<StudentQuestion> findAllByModule(StudentModule module);
}
