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

public class InsertionsortTest {

    Random rando = new Random();

    public InsertionsortTest() {
    }

    /**
     * Test of mergeSortTime method, of class Insertionsort.
     */
    @Test
    public void testInsertionSort() {
        int[] array = {5456, 1235, 875, 897, 456, 102, 8989};
        for (int i : array) {
            System.out.print(i + " ");
        }
        Insertionsort.insertionSort(array);

        System.out.println("");

        int[] sorted = array.clone();
        Arrays.sort(sorted);
        for (int i : sorted) {
            System.out.print(i + " ");
        }

        System.out.println("");
        assertArrayEquals(sorted, array);
    }

}
