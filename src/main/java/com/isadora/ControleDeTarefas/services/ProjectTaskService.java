package com.isadora.ControleDeTarefas.services;

import com.isadora.ControleDeTarefas.domain.Backlog;
import com.isadora.ControleDeTarefas.domain.Project;
import com.isadora.ControleDeTarefas.domain.ProjectTask;
import com.isadora.ControleDeTarefas.repositories.BacklogRepository;
import com.isadora.ControleDeTarefas.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private BacklogRepository backlogRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);

        int backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        backlog.setPTSequence(backlogSequence);

        if(projectTask.getPriority()==null || projectTask.getPriority()==0){
            projectTask.setPriority(3);//baixa
        }

        if(projectTask.getStatus()==null || projectTask.getStatus().equals("") ){
            projectTask.setStatus("TO_DO");//baixa
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

}
