package com.example.nodeis_app.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Employee {

    private Object _id;
    private String fullName;
    private String email;
    private Object position;
//    private ArrayList<Employee> position;

    @Override
    public String toString() {
        return "Employee{" +
                "_id=" + _id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object email) {
        this.position = position;
    }

}
