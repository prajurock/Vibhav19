package com.vibhav.vibhavee.vibhav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "tempDatabase";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADM_NO = "adm_no";
    private static final String KEY_ID = "id";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_BRANCH = "branch";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_PIC = "prof_pic";
    private static final String KEY_COINS = "coins";
    private static final String sherlocked = "sherlocked";
    private static final String placement_fever = "placement_fever";
    private static final String auction_villa = "auction_villa";
    private static final String robo_soccer = "robo_soccer";
    private static final String codee = "codee";
    private static final String guest_lecture = "guest_lecture";
    private static final String pubg = "pubg";
    private static final String marketing_roadies = "marketing_roadies";
    private static final String aaviskar = "aaviskar";
    private static final String laser_maze = "laser_maze";
    private static final String buffet_money = "buffet_money";
    private static final String technovation = "technovation";
    private static final String cs_go = "cs_go";
    private static final String treasure_hunt = "treasure_hunt";




    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_ADM_NO, user.getAdm_no());
        editor.putString(KEY_BRANCH, user.getBranch());
        editor.putString(KEY_MOBILE, user.getMobile());
        editor.putInt(KEY_COINS,user.getCoins());
        editor.apply();
    }
    public void tokenSave(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }
    public void coinSave(Integer coins) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COINS, coins);
        editor.apply();
    }
    public void eventTokenSave(JSONObject token) throws JSONException {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sherlocked,token.getString("sherlocked"));
        editor.putString(placement_fever,token.getString("placement_fever"));
        editor.putString(auction_villa,token.getString("auction_villa"));
        editor.putString(robo_soccer,token.getString("robo_soccer"));
        editor.putString(codee,token.getString("codee"));
        editor.putString(guest_lecture,token.getString("guest_lecture"));
        editor.putString(pubg,token.getString("pubg"));
        editor.putString(marketing_roadies,token.getString("marketing_roadies"));
        editor.putString(aaviskar,token.getString("aaviskar"));
        editor.putString(laser_maze,token.getString("laser_maze"));
        editor.putString(buffet_money,token.getString("buffet_money"));
        editor.putString(technovation,token.getString("technovation"));
        editor.putString(cs_go,token.getString("cs_go"));
        editor.putString(treasure_hunt,token.getString("treasure_hunt"));


        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_MOBILE, null),
                sharedPreferences.getString(KEY_ADM_NO, null),
                sharedPreferences.getString(KEY_BRANCH,null),
                sharedPreferences.getInt(KEY_COINS,0)
        );
    }
    public String getToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null);
    }
    public Integer getCoins() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_COINS, 0);
    }
    public String getEventToken(String event) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(event, null);
    }
    public String getKeyPic(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PIC, null);
    }
    public void saveProfPic(String url) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PIC, url);
        editor.apply();
    }


    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        //mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
