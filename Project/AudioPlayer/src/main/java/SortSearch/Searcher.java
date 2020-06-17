/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortSearch;

import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author 61406
 */
public class Searcher {
   
    public static int bSearch(DefaultListModel<String> list, String target) {
        int index = -1;
        
        int beg = 0;
        int end = list.size() - 1;
        
        while (beg <= end) {
            int mid = (beg + end)  / 2;
            
            if (list.get(mid).equals(target)) {
                index = mid;
                break;
            }
            
            if (list.get(mid).compareTo(target) > -1) {
                end = mid - 1;
            } else if (list.get(mid).compareTo(target) < 1) {                                
                beg = mid + 1;
            }
        }
        
        return index;
    }
        
}
