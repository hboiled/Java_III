/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortSearchTests;

import SortSearch.Sorter;
import com.hboiled.audioplayer.Playlist.Playlist;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 61406
 */
public class SorterTests {

    @Test
    public void MergeSort_Works() {
        List<Playlist> p1 = genList();
        List<Playlist> p2 = genList();
        
        Collections.sort(p2);
        Sorter.sort(p1);
        
        boolean match = true;
        
        // compare the names, as each playlist is a different object
        for (int i = 0; i < p1.size(); i++) {
            if (!p1.get(i).toString().equals(p2.get(i).toString())) {
                match = false;
                break;
            }
        }
        
        Assertions.assertTrue(match);
    }

    private List<Playlist> genList() {
        List<Playlist> list = new ArrayList<>();
        list.add(new Playlist("q"));
        list.add(new Playlist("p"));
        list.add(new Playlist("z"));
        list.add(new Playlist("a"));
        list.add(new Playlist("e"));
        list.add(new Playlist("v"));
        return list;
    }
}
