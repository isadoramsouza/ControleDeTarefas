package com.isadora.ControleDeTarefas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//persistencia responsavel por gerar o id (auto incremento nesse caso)
    private Long id;
    private Integer PTSequence=0;
    private String projectIdentifier;

    //oneToOne with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prooject_id", nullable = false)
    @JsonIgnore //solucao problema de recursao
    private Project project;
    //oneToMany projectTasks


    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

