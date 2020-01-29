package com.example.dmawarehouse.DB_Objects;

public class _UserRegister {
    private String _username;
    private String _password;
    private String _email;
    private double _credit;

    public _UserRegister(String _username, String _password, String _email, double _credit){
        this._username = _username;
        this._password = _password;
        this._email = _email;
        this._credit = _credit;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
    public String get_username(){
        return _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
    public String get_password(){
        return _password;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
    public String get_email(){
        return _email;
    }

    public void set_credit(double _credit) { this._credit = _credit; }
    public double get_credit(){
        return _credit;
    }

}
