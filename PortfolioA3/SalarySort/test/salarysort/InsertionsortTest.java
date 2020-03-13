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

public class InsertionsortTest {

    Random rando = new Random();

    public InsertionsortTest() {
    }

    /**
     * Test of mergeSortTime method, of class Insertionsort.
     */
    @Test
    public void testInsertionSort() {
        Employee[] array = new Employee[10];
        for (int i = 0 ; i < array.length; i++) {
            array[i] = new Employee(rando.nextInt(100));
        }
        Insertionsort.insertionSort(array);

        System.out.println("");

        Employee[] sorted = array.clone();
        Arrays.sort(sorted);
        for (Employee i : sorted) {
            System.out.print(i + " ");
        }

        System.out.println("");
        assertArrayEquals(sorted, array);
    }

}
