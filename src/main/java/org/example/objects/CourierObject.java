package org.example.objects;


public class CourierObject {
    private String login;
    private String password;
    private String firstName;

    public CourierObject(){
    }

    public CourierObject setLogin(String login){
        this.login = login;
        return this;
    }
    public CourierObject setPassword(String password){
        this.password = password;
        return this;
    }
    public CourierObject setFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}