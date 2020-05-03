/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 61406
 */
public class UserDatabase {
    private List<User> users;
    private final String file = "users.txt";
    
    public UserDatabase() {
        // this.users = users;
        // modify constructor to fetch user list
        users = new ArrayList<>();
        loadUsers();        
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public boolean add(User user) {
        if (!doesUserExist(user)) {
            users.add(user);
            commitToFile();
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
        // append true?
        System.out.println("trying to save");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false)) ) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        // 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)) ) {
            users = (List<User>) ois.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
}
