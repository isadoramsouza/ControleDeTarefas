package com.isadora.ControleDeTarefas.repositories;

import com.isadora.ControleDeTarefas.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
