package com.isadora.ControleDeTarefas.services;

import com.isadora.ControleDeTarefas.domain.Backlog;
import com.isadora.ControleDeTarefas.domain.Project;
import com.isadora.ControleDeTarefas.domain.ProjectTask;
import com.isadora.ControleDeTarefas.exceptions.ProjectIdException;
import com.isadora.ControleDeTarefas.exceptions.ProjectNotFoundException;
import com.isadora.ControleDeTarefas.repositories.BacklogRepository;
import com.isadora.ControleDeTarefas.repositories.ProjectRepository;
import com.isadora.ControleDeTarefas.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);

            int backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            backlog.setPTSequence(backlogSequence);

            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
                projectTask.setPriority(3);//baixa
            }

            if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) {
                projectTask.setStatus("TO_DO");//baixa
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Projeto não encontrado.");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);
        if(project==null){
            throw new ProjectNotFoundException("Projeto não encontrado.");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);

        if(backlog==null){
            throw new ProjectNotFoundException("Projeto "+backlog_id+" não encontrado.");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if(projectTask==null){
            throw new ProjectNotFoundException("Tarefa "+pt_id+" não encontrada.");
        }

        if(!projectTask.getProjectIdentifier().equals(backlog.getProjectIdentifier())){
            throw new ProjectNotFoundException("Tarefa "+pt_id+ " não encontrada no projeto "+backlog_id+".");
        }

        return projectTask;
    }

    public ProjectTask uptadeByPrjectSequence(ProjectTask updatedProjectTask, String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);

        projectTask = updatedProjectTask;

        return projectTaskRepository.save(projectTask);

    }

    public void deletePTByProjectSequence(String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);

        projectTaskRepository.delete(projectTask);

    }


}
