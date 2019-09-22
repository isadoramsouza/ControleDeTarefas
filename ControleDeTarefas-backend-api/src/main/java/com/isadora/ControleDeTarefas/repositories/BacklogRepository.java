package com.isadora.ControleDeTarefas.repositories;

import com.isadora.ControleDeTarefas.domain.Backlog;
import com.isadora.ControleDeTarefas.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String id);
}
