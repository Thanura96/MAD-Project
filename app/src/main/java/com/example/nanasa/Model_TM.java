package com.example.nanasa;

public class Model_TM {

    String id;
    String assignment;
    String studentid;
    String name;
    String subject;
    String marks;
    String comment;

    public Model_TM(String id, String assignment, String studentid, String name, String subject, String marks, String comment) {
        this.id = id;
        this.assignment = assignment;
        this.studentid = studentid;
        this.name = name;
        this.subject = subject;
        this.marks = marks;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
