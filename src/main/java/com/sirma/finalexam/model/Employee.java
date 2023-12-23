package com.sirma.finalexam.model;

import java.time.LocalDate;

public class Employee {
    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Employee(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public long getOverlapDuration(Employee other) {
        LocalDate overlapStart = (this.dateFrom.isAfter(other.dateFrom)) ? this.dateFrom : other.dateFrom;
        LocalDate overlapEnd = (this.dateTo == null || other.dateTo == null) ? null :
                (this.dateTo.isBefore(other.dateTo)) ? this.dateTo : other.dateTo;

        if (overlapEnd != null && overlapEnd.isBefore(overlapStart)) {
            return 0; // No overlap
        }

        long overlapDuration = (overlapEnd == null) ?
                LocalDate.now().toEpochDay() - overlapStart.toEpochDay() :
                overlapEnd.toEpochDay() - overlapStart.toEpochDay();

        return overlapDuration;
    }

    public String getKey() {
        return empId + "_" + projectId;
    }
}
