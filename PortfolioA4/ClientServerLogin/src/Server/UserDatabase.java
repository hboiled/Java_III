/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        users = new ArrayList<>();
        loadUsers();
        setUpAdmin();
    }

    private void setUpAdmin() {
        for (User u : users) {
            if (u.getUsername().equals("admin") && !u.isAdmin()) {
                u.makeAdmin();
            }
        }
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
        // overwrite the whole list
        if (doesFileExist(file)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false))) {
                oos.writeObject(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean doesFileExist(String file) {
        File newFile = new File(file);

        if (newFile.exists()) {
            return true;
        } else {
            try {
                return newFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    private void loadUsers() {
        // 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (List<User>) ois.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
