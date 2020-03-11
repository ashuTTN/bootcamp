package com.example.androidstorage2;

import android.view.View;

public class ModelClass {
    int id;
    String Name;
    String Mobile;
    String Address;
    View.OnClickListener listener;
    View.OnClickListener listener1;

    public ModelClass(String name, String mobile, String address) {
        Name = name;
        Mobile = mobile;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
