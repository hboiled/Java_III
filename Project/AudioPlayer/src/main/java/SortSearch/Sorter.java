/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortSearch;

import com.hboiled.audioplayer.Playlist.Playlist;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 61406
 */
public class Sorter {

    public static void sort(List<Playlist> list) {
        if (list.size() < 2) {
            return;
        }
        int mid = list.size() / 2;
        List<Playlist> left = new ArrayList<Playlist>(list.subList(0, mid));
        List<Playlist> right = new ArrayList<Playlist>(list.subList(mid, list.size()));

        sort(left);
        sort(right);
        merge(left, right, list);
    }

    private static void merge(
            List<Playlist> left, List<Playlist> right, List<Playlist> list) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).toString().compareTo(right.get(rightIndex).toString()) < 0) {
                list.set(listIndex++, left.get(leftIndex++));
            } else {
                list.set(listIndex++, right.get(rightIndex++));
            }
        }
        while (leftIndex < left.size()) {
            list.set(listIndex++, left.get(leftIndex++));
        }
        while (rightIndex < right.size()) {
            list.set(listIndex++, right.get(rightIndex++));
        }
    }
}
