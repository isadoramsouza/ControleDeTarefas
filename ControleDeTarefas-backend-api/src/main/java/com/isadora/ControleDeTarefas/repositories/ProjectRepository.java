package com.isadora.ControleDeTarefas.repositories;

import com.isadora.ControleDeTarefas.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //acesso aos dados
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String id);

}
