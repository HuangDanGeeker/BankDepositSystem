package com.bean;

/**
 * Created by HuangDanGeeker on 2018/3/16.
 */
public class Customer {
    public String name;
    public String no;
    public int passwd;
    public String birthday;
    public String  phone;
    public String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getPasswd() {
        return passwd;
    }

    public void setPasswd(Integer passwd) {
        this.passwd = passwd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public Customer reFormat(){
//        birthday = birthday.substring(0,4)+"-"+birthday.substring(4,6)+"-"+birthday.substring(6);
//        System.out.println(birthday);
//        return this;
//    }

}
