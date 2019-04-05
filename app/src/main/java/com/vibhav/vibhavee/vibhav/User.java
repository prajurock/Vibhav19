package com.vibhav.vibhavee.vibhav;

import org.json.JSONObject;

/**
 * Created by Belal on 9/5/2017.
 */


//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private String username, email, id, mobile, adm_no, branch;
    private Integer coins;
    private JSONObject event;


    public User(String username, String email, String id, String mobile, String adm_no, String branch, Integer coins) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.mobile= mobile;
        this.adm_no= adm_no;
        this.branch= branch;
        this.coins=coins;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }
    public String getAdm_no(){
        return adm_no;
    }
    public String getBranch() {
        return branch;
    }
    public Integer getCoins() {
        return coins;
    }

}
