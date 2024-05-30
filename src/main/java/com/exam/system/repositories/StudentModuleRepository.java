package com.exam.system.repositories;

import com.exam.system.models.StudentExam;
import com.exam.system.models.StudentModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentModuleRepository extends JpaRepository<StudentModule, Long> {
    public List<StudentModule> findAllByExam(StudentExam exam);
}
