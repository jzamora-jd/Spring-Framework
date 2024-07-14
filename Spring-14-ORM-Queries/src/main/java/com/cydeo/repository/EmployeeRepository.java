package com.cydeo.repository;

import com.cydeo.entity.Department;
import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //Display all employees with email address ' '
    List<Employee> findByEmail(String email);

    //Display all employees with first name ' ' and last name ' ' , also show all employees with an email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName,String lastName,String email);

    //Display all employees that first name is not ' '
    List<Employee> findByFirstNameIsNot(String firstName);

    //Display all employees where last name starts with ' '
    List<Employee> findByLastNameStartsWith(String pattern);

    //Display all employees with salaries higher than ' '
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employees with salaries less than ' '
    List<Employee> findBySalaryLessThanEqual(Integer salary);

    //Display all employees that has been hired between '' and  ''
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display all employees where salaries greater and equal to '' in order
    List<Employee> findBySalaryIsGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //Display top unique 3 employees that is making less than ''
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();

    @Query("SELECT e FROM Employee e where e.email = 'sdubber7@t-online.de' ")//Employee e => Employee e = new Employee();
    Employee getEmployeeDetail();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'sdubber7@t-online.de'")
    Integer getEmployeeSalary();

    @Query("SELECT e FROM Employee e WHERE e.email=?1")
    Optional<Employee> getEmployeeDetail(String email);

    @Query("SELECT e FROM Employee e WHERE e.email=?1 AND e.salary=?2")
    Employee getEmployeeDetail(String email,int salary);

    @Query("SELECT e FROM Employee e WHERE e.salary <> ?1 ") //not equal
    List<Employee> getEmployeeSalaryNotEqual(int salary);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1") //get first name like, startsWith, endsWith
    List<Employee> getEmployeeFirstNameLike(String pattern);

    @Query("SELECT e FROM Employee e WHERE e.salary < ?1") //less than
    List<Employee> getEmployeeSalaryLessThan(int salary);

    @Query("SELECT e FROM Employee e WHERE e.salary > ?1") //greater than
    List<Employee> getEmployeeSalaryGreaterThan(int salary);

    @Query("SELECT e FROM Employee e WHERE e.hireDate > ?1") //before
    List<Employee> getEmployeeHireDateBefore(LocalDate date);

    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2") //between
    List<Employee> getEmployeeSalaryBetween(int salary1,int salary2);

    @Query("SELECT e FROM Employee e WHERE e.email IS NULL") //null
    List<Employee> getEmployeeEmailIsNull();

    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL") //when not null
    List<Employee> getEmployeeEmailIsNotNull();

    @Query("SELECT e FROM Employee e ORDER BY e.salary") //sort ascending order
    List<Employee> getEmployeeSalaryOrderAsc();

    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC ") //sort descending order
    List<Employee> getEmployeeSalaryOrderDesc();

    @Query(value = "SELECT * FROM employees WHERE salary ?1",nativeQuery = true) //native SQL Query
    List<Employee> readEmployeeDetailBySalary(int salary);

    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> getEmployeeSalary(@Param("salary") int salary);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email = 'admin@email.com' WHERE e.id=:id")
    void updateEmployeeJPQL(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email = 'admin@email.com' WHERE id=:id",nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") int id);




}
