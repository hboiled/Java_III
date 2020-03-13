/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salarysort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import salarysort.Employee.Employee;


/**
 *
 * @author 30018308
 */
public class MergesortTest {
    Random rando = new Random();
    
    public MergesortTest() {
    }
    
    /**
     * Test of mergeSortTime method, of class Mergesort.
     */
    @Test
    public void testMergeSort() {        
        // employee array set up
        Employee[] array = new Employee[10];
        for (int i = 0 ; i < array.length; i++) {
            array[i] = new Employee(rando.nextInt(100));
        }
        for (Employee e : array) {
            System.out.print(e + " ");
        }
        System.out.println("");
        // clone employee array before sorting, to be used with built in sort
        Employee[] sorted = array.clone();
        
        for (Employee e : sorted) {
            System.out.print(e + " ");
        }
        System.out.println("");
        
        // mergesort call on initial employee array
        Mergesort.mergeSort(array, 0, array.length - 1);
        
        for (Employee i : array) {
            System.out.print(i + " ");
        }
        
        System.out.println("");
        
        // sort the cloned array
        Arrays.sort(sorted);
        for (Employee e : sorted) {
            System.out.print(e + " ");
        }
        
        // arrays must still be equal after sorting to demonstrate the mergesort works correctly
        assertArrayEquals(sorted, array);
    }
    
}
