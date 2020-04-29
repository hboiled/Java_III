/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.Serializable;

/**
 *
 * @author 61406
 */
public class LoginDetails implements Serializable {

    private String username;
    private String password;
    private String request;

    public LoginDetails(String username, String password, String request) {
        this.username = username;
        this.password = password;
        this.request = request;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRequest() {
        return request;
    }

}
