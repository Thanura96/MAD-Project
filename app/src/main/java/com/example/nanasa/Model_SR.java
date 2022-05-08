package com.example.nanasa;

public class Model_SR {

    String sid, fkid, image, fname, lname, grade, age, gender, phone, email, password, addTimestamp, updateTimestamp;

    public Model_SR(String sid, String fkid, String image, String fname, String lname, String grade, String age, String gender, String phone, String email, String password, String addTimestamp, String updateTimestamp) {
        this.sid = sid;
        this.fkid = fkid;
        this.image = image;
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.addTimestamp = addTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFkid() {
        return fkid;
    }

    public void setFkid(String fkid) {
        this.fkid = fkid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddTimestamp() {
        return addTimestamp;
    }

    public void setAddTimestamp(String addTimestamp) {
        this.addTimestamp = addTimestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

