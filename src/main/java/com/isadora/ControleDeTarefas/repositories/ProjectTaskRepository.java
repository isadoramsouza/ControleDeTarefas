package com.isadora.ControleDeTarefas.repositories;

import com.isadora.ControleDeTarefas.domain.Project;
import com.isadora.ControleDeTarefas.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
}
