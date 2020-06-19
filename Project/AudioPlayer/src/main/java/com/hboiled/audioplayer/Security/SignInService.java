/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Security;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 61406
 */
public class SignInService {

    private final PWManager PWMan = new PWManager(new HashGen(), new SaltGen());
    private List<User> users = new ArrayList<>();
    private final File usersPath = new File("data/users.csv");

    public SignInService() {
        // insertUsers();        
    }

    public boolean attemptLogin(String username, String password) {

        User user = searchUser(username);

        if (user == null) {
            return false;
        }

        String enteredPwSecured = PWMan.SecurePW(password, user.getSalt());
        String securedPw = user.getSecurePW();

        System.out.println(enteredPwSecured + "\n" + securedPw);
        System.out.println("\n"+new String(user.getSalt()));
        
        boolean PWCheckOutcome = securedPw.equals(enteredPwSecured);

        return PWCheckOutcome;
    }

    public boolean attemptRegister(String username, String password) {

        if (searchUser(username) == null) {

            byte[] salt = PWMan.getSalt();
            String securedPW = PWMan.SecurePW(password, salt);

            User newUser = new User(username, salt, securedPW);
            users.add(newUser);

            saveUserList();
            return true;
        }

        // username in use already
        return false;
    }

    private User searchUser(String name) {
        for (User u : users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }

        return null;
    }

    public final void insertUsers() {

        if (usersPath.exists()) {
            try (CSVReader reader = new CSVReader(new FileReader(usersPath))) {

                // use Reader to read all values into a list
                List<String[]> data = reader.readAll();

                // insert users into list
                for (String[] row : data) {
                    String name = row[0];
                    byte[] salt = row[1].getBytes();
                    String securedPW = row[2];

                    users.add(new User(name, salt, securedPW));
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    private void saveUserList() {

        try (FileWriter writer = new FileWriter(usersPath, false)) {

            for (User u : users) {

                String name = u.getUsername();
                String salt = new String(u.getSalt());
                String securedPW = u.getSecurePW();

                writer.write(String.format("%s,%s,%s\n", name, salt, securedPW));

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
        
    public List<User> getUsers() {
        return users;
    }
}
