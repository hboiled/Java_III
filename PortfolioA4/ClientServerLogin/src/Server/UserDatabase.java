/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 61406
 */
public class UserDatabase {
    private List<User> users;
    
    public UserDatabase() {
        // this.users = users;
        // modify constructor to fetch user list
        users = new ArrayList<>();
        
    }
    
    public boolean add(User user) {
        if (!doesUserExist(user)) {
            users.add(user);
            //commitToFile();
            return true;
        } 
        
        return false;
    }
    
    private boolean doesUserExist(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        
        return false;
    }

    private void commitToFile() {
        // save user list
    }
}
