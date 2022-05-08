package com.example.nanasa;

public class Model_trb {

    String  tr_id, tr_date, tr_subject, tr_titel, tr_outComes, tr_donePoints, tr_exceptionPoints, tr_addTimes, tr_updateTimes;


    public Model_trb(String tr_id, String tr_date, String tr_subject, String tr_titel, String tr_outComes, String tr_donePoints, String tr_exceptionPoints, String tr_addTimes, String tr_updateTimes) {
        this.tr_id = tr_id;
        this.tr_date = tr_date;
        this.tr_subject = tr_subject;
        this.tr_titel = tr_titel;
        this.tr_outComes = tr_outComes;
        this.tr_donePoints = tr_donePoints;
        this.tr_exceptionPoints = tr_exceptionPoints;
        this.tr_addTimes = tr_addTimes;
        this.tr_updateTimes = tr_updateTimes;
    }

    public String getTr_id() {
        return tr_id;
    }

    public void setTr_id(String tr_id) {
        this.tr_id = tr_id;
    }

    public String getTr_date() {
        return tr_date;
    }

    public void setTr_date(String tr_date) {
        this.tr_date = tr_date;
    }

    public String getTr_subject() {
        return tr_subject;
    }

    public void setTr_subject(String tr_subject) {
        this.tr_subject = tr_subject;
    }

    public String getTr_titel() {
        return tr_titel;
    }

    public void setTr_titel(String tr_titel) {
        this.tr_titel = tr_titel;
    }

    public String getTr_outComes() {
        return tr_outComes;
    }

    public void setTr_outComes(String tr_outComes) {
        this.tr_outComes = tr_outComes;
    }

    public String getTr_donePoints() {
        return tr_donePoints;
    }

    public void setTr_donePoints(String tr_donePoints) {
        this.tr_donePoints = tr_donePoints;
    }

    public String getTr_exceptionPoints() {
        return tr_exceptionPoints;
    }

    public void setTr_exceptionPoints(String tr_exceptionPoints) {
        this.tr_exceptionPoints = tr_exceptionPoints;
    }

    public String getTr_addTimes() {
        return tr_addTimes;
    }

    public void setTr_addTimes(String tr_addTimes) {
        this.tr_addTimes = tr_addTimes;
    }

    public String getTr_updateTimes() {
        return tr_updateTimes;
    }

    public void setTr_updateTimes(String tr_updateTimes) {
        this.tr_updateTimes = tr_updateTimes;
    }
}
