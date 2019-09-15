package com.isadora.ControleDeTarefas.services;

import com.isadora.ControleDeTarefas.domain.Project;
import com.isadora.ControleDeTarefas.exceptions.ProjectIdException;
import com.isadora.ControleDeTarefas.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository; //injecao de dependencia, baixo acoplamento entre as classes

    public Project saveOrUpdate(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("ID '"+project.getProjectIdentifier().toUpperCase()+"' já existe.");
        }
    }

    public void deleteProjectByIdentifier(String id){
        Project project = projectRepository.findByProjectIdentifier(id.toUpperCase());
        if(project==null) {
            throw new ProjectIdException("Projeto '" + id + "' não existe.");
        }
        projectRepository.delete(project);
    }

    public Project findByProjectIdentifier(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);

        if (project == null) {
            throw new ProjectIdException("Projeto com ID '"+id+"' não existe.");
        } else {
            return project;
        }
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

}
