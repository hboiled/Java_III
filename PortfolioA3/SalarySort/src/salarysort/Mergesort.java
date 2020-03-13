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
public class Mergesort {
    
    public static void mergeSortTime(Employee[] array) {
        Instant start = Instant.now();
        mergeSort(array, 0, array.length - 1);
        Instant finish = Instant.now();        
        System.out.println("Merge sort method -");        
        long timeMSElapsed = Duration.between(start, finish).toMillis();
        long timeSecElapsed = Duration.between(start, finish).toSeconds();        
        System.out.println(timeMSElapsed + " : milliseconds.");
        System.out.println(timeSecElapsed + " : seconds.");
    }

    public static void mergeSort(Employee[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }
    
    private static void merge(Employee[] arr, int left, int mid, int right) {
    // arr length of left and right sub arrs
    int leftLen = mid - left + 1;
    int rightLen = right - mid;
    // sub arrs to contain halves
    Employee[] leftArr = new Employee[leftLen];
    Employee[] rightArr = new Employee[rightLen];

    // populate sub arrs with sorted vals from arr
    for (int i = 0; i < leftLen; i++)
        leftArr[i] = arr[left+i];
    for (int i = 0; i < rightLen; i++)
        rightArr[i] = arr[mid+i+1];

    // keep track of sub arr indexes
    int lIndex = 0;
    int rIndex = 0;

    // sub arrs put back into arr
    for (int i = left; i < right + 1; i++) {        
        if (lIndex < leftLen && rIndex < rightLen) {
            // insert into arr the lowest val of leftArr and rightArr
            if (leftArr[lIndex].getSalary() < rightArr[rIndex].getSalary()) {
                arr[i] = leftArr[lIndex];
                lIndex++;
            }
            else {
                arr[i] = rightArr[rIndex];
                rIndex++;
            }
        }
        // if leftArr still has vals remaining insert the rest into the arr
        else if (lIndex < leftLen) {
            arr[i] = leftArr[lIndex];
            lIndex++;
        }
        // if rightArr vals remaining insert rest into arr
        else if (rIndex < rightLen) {
            arr[i] = rightArr[rIndex];
            rIndex++;
        }
    }
}
}
