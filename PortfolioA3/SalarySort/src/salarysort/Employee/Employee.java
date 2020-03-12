/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salarysort.Employee;

/**
 *
 * @author 30018308
 */
public class Employee implements Comparable<Employee>{
    private int salary;
    private String name;
    private static int COUNT = 1;
    
    public Employee(int s) {
        salary = s;
        name = "EmployeeN" + COUNT;
        COUNT++;
    }
    
    public int getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.salary, o.getSalary());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.salary;
        return hash;
    }

    @Override
    // employees are equal if they have the same salary
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.salary != other.salary) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return salary+"";
    }
    
    
}
