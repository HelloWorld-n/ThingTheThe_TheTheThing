package com.onboarding.onboarding.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ComponentScan;

@Repository
@Service
@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
