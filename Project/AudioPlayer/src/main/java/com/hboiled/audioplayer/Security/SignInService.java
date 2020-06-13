/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboiled.audioplayer.Security;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
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
    
    public SignInService() {
        File file = new File("data/users.csv");
        insertUsers(file);
        for (User u : users) {
            System.out.println(u);
        }
    }

    private boolean attemptLogin(String username, String password) {

        User user = searchUser(username);

        if (user == null) {
            return false;
        }

        String enteredPwSecured = PWMan.SecurePW(password, user.getSalt());
        String securedPw = user.getSecurePW();

        //System.out.println(enteredPwSecured + "\n" + securedPw);
        boolean PWCheckOutcome = securedPw.equals(enteredPwSecured);

        return PWCheckOutcome;
    }

    private User searchUser(String name) {
        for (User u : users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }

        return null;
    }

    public final void insertUsers(File fileName) {

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

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
