package com.exam.system.repositories;

import com.exam.system.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Override
    public List<Exam> findAll();
}
