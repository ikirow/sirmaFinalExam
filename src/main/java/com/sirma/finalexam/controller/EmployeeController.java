package com.sirma.finalexam.controller;

import com.sirma.finalexam.model.Employee;
import com.sirma.finalexam.service.CSVReaderService;
import com.sirma.finalexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private CSVReaderService csvReaderService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void initialize() {
        List<Employee> employeeProjects = csvReaderService.read(null, "src/main/resources/static/employee_data.csv");
        employeeService.initialize(employeeProjects);
    }

    @GetMapping("/longest-working-pairs")
    public Map<String, Map<Integer, Integer>> getLongestWorkingPairs() {
        return employeeService.findOverlappingWorkingPairs();
    }
}