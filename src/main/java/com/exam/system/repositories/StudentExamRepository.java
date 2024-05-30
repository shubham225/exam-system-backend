package com.exam.system.repositories;

import com.exam.system.models.Exam;
import com.exam.system.models.StudentExam;
import com.exam.system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
    public List<StudentExam> findAllByStudent(User student);
    public Optional<StudentExam> findByExamAndStudent(Exam exam, User student);
}
