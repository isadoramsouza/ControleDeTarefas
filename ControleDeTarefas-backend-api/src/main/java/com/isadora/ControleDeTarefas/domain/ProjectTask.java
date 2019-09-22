package com.isadora.ControleDeTarefas.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//persistencia responsavel por gerar o id (auto incremento nesse caso)
    private Long id;

    @Column(updatable = false, unique = true)
    private String projectSequence;

    @NotBlank(message = "Necessário incluir um resumo.")
    private String summary;

    private String accpetanceCriteria;

    private String status;

    private Integer priority;

    //manyTOne with backlog
    @ManyToOne(fetch = FetchType.EAGER) //refresh no backlog
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    @Column(updatable = false)
    private String projectIdentifier;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_at;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date update_at;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;


    @PrePersist
    protected void onCreate(){
        this.create_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_at = new Date();
    }

    public ProjectTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAccpetanceCriteria() {
        return accpetanceCriteria;
    }

    public void setAccpetanceCriteria(String accpetanceCriteria) {
        this.accpetanceCriteria = accpetanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", accpetanceCriteria='" + accpetanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                ", dueDate=" + dueDate +
                '}';
    }
}
