package com.exam.system.repositories;

import com.exam.system.models.Module;
import com.exam.system.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    public List<Question> findAll();
    public List<Question> findAllByModuleId(long id);
}
