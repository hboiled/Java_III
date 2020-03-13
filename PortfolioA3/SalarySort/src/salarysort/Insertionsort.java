/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salarysort;

import java.time.Duration;
import java.time.Instant;
import salarysort.Employee.Employee;

/**
 *
 * @author 30018308
 */
public class Insertionsort {
    
    public static void insertionSortTime(Employee[] arr) {
        Instant start = Instant.now();
        insertionSort(arr);
        Instant finish = Instant.now();       
        System.out.println("Insertion sort method -");       
        long timeMSElapsed = Duration.between(start, finish).toMillis();
        long timeSecElapsed = Duration.between(start, finish).toSeconds();        
        System.out.println(timeMSElapsed + " : milliseconds.");
        System.out.println(timeSecElapsed + " : seconds.");
    }

    public static void insertionSort(Employee[] array) {
        if (array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++){ 
            Employee current = array[i];
            int j = i - 1;
            while (j >= 0 && current.getSalary() < array[j].getSalary()) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }
}
