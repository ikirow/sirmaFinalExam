package com.sirma.finalexam.model;

import java.util.Date;

public class Employee {
    private Long id;
    private Long projectId;
    private Date projectAssignmentStartDate;
    private Date projectAssignmentEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Date getProjectAssignmentStartDate() {
        return projectAssignmentStartDate;
    }

    public void setProjectAssignmentStartDate(Date projectAssignmentStartDate) {
        this.projectAssignmentStartDate = projectAssignmentStartDate;
    }

    public Date getProjectAssignmentEndDate() {
        return projectAssignmentEndDate;
    }

    public void setProjectAssignmentEndDate(Date projectAssignmentEndDate) {
        this.projectAssignmentEndDate = projectAssignmentEndDate;
    }
}
