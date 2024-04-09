package com.exam.system.repositories;

import com.exam.system.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Override
    public List<Module> findAll();
    public List<Module> findAllByExamId(long id);
}
