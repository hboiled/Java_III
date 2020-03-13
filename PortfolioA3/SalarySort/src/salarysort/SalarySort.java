/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salarysort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import salarysort.Employee.Employee;

/**
 *
 * @author 30018308
 */
public class SalarySort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rando = new Random();                        
        
        // create array of 100 thousand ints
        
        int SIZE = 100_000;
        Employee[] nums = new Employee[SIZE];
        
        for (int z = 0; z < 10; z++) {
            for (int i = 0; i < nums.length; i++) {
            nums[i] = new Employee(rando.nextInt(100_000_000));
        }
        Employee[] numsInsertion = nums.clone();
        Employee[] numsMerge = nums.clone();

        System.out.println("On an array of size 100,000");
        Mergesort.mergeSortTime(numsMerge);
        builtInSort(nums);
        Insertionsort.insertionSortTime(numsInsertion);        
        }
        
    }        
    
    public static void builtInSort(Employee[] arr) {
        Instant start = Instant.now();
        Arrays.sort(arr);
        Instant finish = Instant.now();
        System.out.println("Built in arrays sort method -");
        long timeMSElapsed = Duration.between(start, finish).toMillis();
        long timeSecElapsed = Duration.between(start, finish).toSeconds();        
        System.out.println(timeMSElapsed + " : milliseconds.");
        System.out.println(timeSecElapsed + " : seconds.");
    }


}
