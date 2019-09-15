package com.isadora.ControleDeTarefas.services;

import com.isadora.ControleDeTarefas.domain.Project;
import com.isadora.ControleDeTarefas.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository; //injecao de dependencia, baixo acoplamento entre as classes

    public Project saveOrUpdate(Project project){

        return projectRepository.save(project);
    }


}
