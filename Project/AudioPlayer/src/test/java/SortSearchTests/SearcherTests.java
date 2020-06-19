package SortSearchTests;


import SortSearch.Searcher;
import com.hboiled.audioplayer.Security.HashGen;
import com.hboiled.audioplayer.Security.PWManager;
import com.hboiled.audioplayer.Security.SaltGen;
import javax.swing.DefaultListModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 61406
 */
public class SearcherTests {    
    
    @Test
    public void bSearch_ShouldWorkOnSortedListModel() {
        // bsearch tests
        DefaultListModel<String> songs = new DefaultListModel<String>();
        songs.add(0, "a");
        songs.add(1, "b");
        songs.add(2, "c");
        songs.add(3, "d");
        songs.add(4, "e");
        
        int expected = Searcher.bSearch(songs, "a");
        int actual = 0;
        
        Assertions.assertEquals(actual, expected);
    }
    
}
