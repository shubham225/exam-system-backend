package com.exam.system.repositories;

import com.exam.system.models.Option;
import com.exam.system.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    @Override
    public List<Option> findAll();
    public List<Option> findAllByQuestionId(long id);
}
