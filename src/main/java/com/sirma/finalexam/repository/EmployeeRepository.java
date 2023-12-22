package com.sirma.finalexam.repository;

import com.sirma.finalexam.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompleted(boolean completed);
}
