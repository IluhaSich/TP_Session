package com.example.session;

public class Students {
    int student_id, course_id, user_id;

    public Students(int student_id, int course_id, int user_id) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.user_id = user_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
