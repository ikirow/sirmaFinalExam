package com.sirma.finalexam.service;

import com.sirma.finalexam.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private List<Employee> employeeProjects;

    public void initialize(List<Employee> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    public Map<String, Map<Integer, Integer>> findOverlappingWorkingPairs() {
        Map<String, Map<Integer, Integer>> result = new HashMap<>();

        for (int i = 0; i < employeeProjects.size(); i++) {
            for (int j = i + 1; j < employeeProjects.size(); j++) {
                Employee emp1 = employeeProjects.get(i);
                Employee emp2 = employeeProjects.get(j);

                if (emp1.getProjectId() == emp2.getProjectId() && haveOverlap(emp1, emp2)) {
                    String pairKey = emp1.getEmpId() + "_" + emp2.getEmpId();
                    int overlapDuration = calculateOverlap(emp1, emp2);

                    // Create a map to store project ID and overlap duration
                    Map<Integer, Integer> projectOverlapMap = result.getOrDefault(pairKey, new HashMap<>());
                    projectOverlapMap.put(emp1.getProjectId(), overlapDuration);

                    result.put(pairKey, projectOverlapMap);

                    // Print to console
                    System.out.println("Overlap between employees " + emp1.getEmpId() + " and " + emp2.getEmpId() +
                            " on project " + emp1.getProjectId() + ": " + overlapDuration + " days");
                }
            }
        }

        return result;
    }

    private boolean haveOverlap(Employee emp1, Employee emp2) {
        LocalDate start1 = emp1.getDateFrom();
        LocalDate end1 = (emp1.getDateTo() == null) ? LocalDate.now() : emp1.getDateTo();

        LocalDate start2 = emp2.getDateFrom();
        LocalDate end2 = (emp2.getDateTo() == null) ? LocalDate.now() : emp2.getDateTo();

        return !(end1.isBefore(start2) || end2.isBefore(start1));
    }

    private int calculateOverlap(Employee emp1, Employee emp2) {
        LocalDate start1 = emp1.getDateFrom();
        LocalDate end1 = (emp1.getDateTo() == null) ? LocalDate.now() : emp1.getDateTo();

        LocalDate start2 = emp2.getDateFrom();
        LocalDate end2 = (emp2.getDateTo() == null) ? LocalDate.now() : emp2.getDateTo();

        LocalDate overlapStart = (start1.isAfter(start2)) ? start1 : start2;
        LocalDate overlapEnd = (end1.isBefore(end2)) ? end1 : end2;

        return (int) ChronoUnit.DAYS.between(overlapStart, overlapEnd);
    }
}


