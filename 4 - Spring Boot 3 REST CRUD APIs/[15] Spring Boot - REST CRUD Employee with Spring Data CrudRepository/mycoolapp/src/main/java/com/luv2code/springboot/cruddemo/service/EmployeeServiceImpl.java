package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository; //Previously: private EmployeeDAO employeeDAO

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId); //"Optional" Different pattern instead of having to check for nulls. (Feature introduced in Java 8)

        Employee theEmployee = null;

        if (result.isPresent()) { //mecvut bir değer varsa true döndürür,
            theEmployee = result.get(); //ardından verilen değeri gerçekten alabilirim.
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override //Remove @Transactional since JpaRepository provides this functionality
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override //Remove @Transactional since JpaRepository provides this functionality
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}






