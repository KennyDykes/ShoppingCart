package com.example.kenny.shoppingcart;

/**
 * Created by Kenny on 6/19/2015.
 * User class that defines and intializes username,password, and accessor type
 */
public class User {
    private String username;
    private String password;
    private String usertype;
   public User() {   }

   public User(String entry_name, String entry_pass,String type)
    {
        this.username=entry_name;
        this.password=entry_pass;
        this.usertype=type;
    }
    public String GetUsername()
    {
        return this.username;
    }
    public String GetPassword()
    {
        return this.password;
    }
    public String GetType()    { return this.usertype;}

    public void SetUsername(String user)
    { this.username=user;}

    public void SetPassword(String pass)
    {this.password=pass;}
    public void SetType(String level)
    {this.usertype=level;}

}
