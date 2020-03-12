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
        
        
        Employee[] nums = new Employee[100_00];
        
        for (int z = 0; z < 2; z++) {
            for (int i = 0; i < nums.length; i++) {
            nums[i] = new Employee(rando.nextInt(100_000_000));
        }
        Employee[] numsInsertion = nums.clone();
        Employee[] numsMerge = nums.clone();

        System.out.println("On an array of size 100,000");
        builtInSort(nums);
        Insertionsort.insertionSortTime(numsInsertion);
        Mergesort.mergeSortTime(numsMerge);
        }
        
        
//        Employee[] nums10 = new int[1_000_000];
//        
//        for (int i = 0; i < nums.length; i++) {
//            nums10[i] = rando.nextInt(100_000_000);
//        }
//        Employee[] nums10Insertion = nums10.clone();
//        Employee[] nums10Merge = nums10.clone();
//        
//        System.out.println("On an array of size 1,000,000");
//        builtInSort(nums10);
//        Insertionsort.insertionSortTime(nums10Insertion);
//        Mergesort.mergeSortTime(nums10Merge);
//        
//        int[] nums100 = new int[10_000_000];
//        
//        for (int i = 0; i < nums.length; i++) {
//            nums100[i] = rando.nextInt(100_000_000);
//        }
//        
//        System.out.println("On an array of size 10,000,000");
//        builtInSort(nums100);
//        insertionSortTime(nums100);
//        mergeSortTime(nums100);
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
