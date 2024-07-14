package com.cydeo.bootstrap;

import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DataGenerator(RegionRepository regionRepository,DepartmentRepository departmentRepository,EmployeeRepository employeeRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ----------- REGION START -----------");
        System.out.println(regionRepository.findByCountry("Canada"));
        System.out.println(regionRepository.findDistinctByCountry("Canada"));
        System.out.println(regionRepository.findByCountryContaining("United"));
        System.out.println(regionRepository.findByCountryContainingOrderByCountry("Asia"));
        System.out.println(regionRepository.findTop2ByCountry("Canada"));
        System.out.println(" ----------- REGION START -----------");


        System.out.println("-----------------DEPARTMENT START-----------------");

        System.out.println("findByDepartment:" + departmentRepository.findByDepartment("Toys"));
        System.out.println("findByDivisionIs:" + departmentRepository.findByDivisionIs("Outdoors"));
        System.out.println("findDistinctTop3ByDivisionContains:" + departmentRepository.findDistinctTop3ByDivisionContains("Hea"));

        System.out.println("-----------------DEPARTMENT END-----------------");

        System.out.println("-----------------EMPLOYEE START-----------------");

        System.out.println("getEmployeeDetail:" + employeeRepository.getEmployeeDetail());
        System.out.println("getEmployeeSalary:" + employeeRepository.getEmployeeSalary());



        System.out.println("-----------------EMPLOYEE  END-----------------");




    }
}
