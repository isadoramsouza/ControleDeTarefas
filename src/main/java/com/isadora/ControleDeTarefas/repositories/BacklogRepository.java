package com.isadora.ControleDeTarefas.repositories;

import com.isadora.ControleDeTarefas.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
}
