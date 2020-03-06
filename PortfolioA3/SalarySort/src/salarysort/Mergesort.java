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
    
    private static void merge(Employee[] array, int left, int mid, int right) {
    // calculating lengths
    int lengthLeft = mid - left + 1;
    int lengthRight = right - mid;

    // creating temporary subarrays
    int leftArray[] = new int [lengthLeft];
    int rightArray[] = new int [lengthRight];

    // copying our sorted subarrays into temporaries
    for (int i = 0; i < lengthLeft; i++)
        leftArray[i] = array[left+i].getSalary();
    for (int i = 0; i < lengthRight; i++)
        rightArray[i] = array[mid+i+1].getSalary();

    // iterators containing current index of temp subarrays
    int leftIndex = 0;
    int rightIndex = 0;

    // copying from leftArray and rightArray back into array
    for (int i = left; i < right + 1; i++) {
        // if there are still uncopied elements in R and L, copy minimum of the two
        if (leftIndex < lengthLeft && rightIndex < lengthRight) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                array[i] = new Employee(leftArray[leftIndex]);
                leftIndex++;
            }
            else {
                array[i] = new Employee(rightArray[rightIndex]);
                rightIndex++;
            }
        }
        // if all the elements have been copied from rightArray, copy the rest of leftArray
        else if (leftIndex < lengthLeft) {
            array[i] = new Employee(leftArray[leftIndex]);
            leftIndex++;
        }
        // if all the elements have been copied from leftArray, copy the rest of rightArray
        else if (rightIndex < lengthRight) {
            array[i] = new Employee(rightArray[rightIndex]);
            rightIndex++;
        }
    }
}
}
