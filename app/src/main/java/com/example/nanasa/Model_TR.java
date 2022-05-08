package com.example.nanasa;

public class Model_TR {

    String t_id, t_image, t_fname, t_lname, t_email, t_password, t_addTimestamp, t_updateTimestamp;

    public Model_TR(String t_id, String t_image, String t_fname, String t_lname, String t_email, String t_password, String t_addTimestamp, String t_updateTimestamp) {
        this.t_id = t_id;
        this.t_image = t_image;
        this.t_fname = t_fname;
        this.t_lname = t_lname;
        this.t_email = t_email;
        this.t_password = t_password;
        this.t_addTimestamp = t_addTimestamp;
        this.t_updateTimestamp = t_updateTimestamp;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_image() {
        return t_image;
    }

    public void setT_image(String t_image) {
        this.t_image = t_image;
    }

    public String getT_fname() {
        return t_fname;
    }

    public void setT_fname(String t_fname) {
        this.t_fname = t_fname;
    }

    public String getT_lname() {
        return t_lname;
    }

    public void setT_lname(String t_lname) {
        this.t_lname = t_lname;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_addTimestamp() {
        return t_addTimestamp;
    }

    public void setT_addTimestamp(String t_addTimestamp) {
        this.t_addTimestamp = t_addTimestamp;
    }

    public String getT_updateTimestamp() {
        return t_updateTimestamp;
    }

    public void setT_updateTimestamp(String t_updateTimestamp) {
        this.t_updateTimestamp = t_updateTimestamp;
    }
}
