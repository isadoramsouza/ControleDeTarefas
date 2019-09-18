package com.isadora.ControleDeTarefas.services;

import com.isadora.ControleDeTarefas.domain.Backlog;
import com.isadora.ControleDeTarefas.domain.ProjectTask;
import com.isadora.ControleDeTarefas.repositories.BacklogRepository;
import com.isadora.ControleDeTarefas.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//        if(projectTask.getPriority()==0 || projectTask.getPriority()==null){
//            projectTask.setPriority(3);//baixa
//        }

//        if(projectTask.getStatus().equals("") || projectTask.getStatus()==null){
//            projectTask.setStatus("TO_DO");//baixa
//        }

        return projectTaskRepository.save(projectTask);
    }
}