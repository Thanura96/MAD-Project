package com.example.nanasa;

public class Model_mkasg {

    String id, image, number, subject, deadLine, description, addTimeStamp, updateTimestamp;

    public Model_mkasg(String id, String image, String number, String subject, String deadLine, String description, String addTimeStamp, String updateTimestamp) {
        this.id = id;
        this.image = image;
        this.number = number;
        this.subject = subject;
        this.deadLine = deadLine;
        this.description = description;
        this.addTimeStamp = addTimeStamp;
        this.updateTimestamp = updateTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
