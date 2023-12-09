package com.example.session;

public class Courses {
    int course_id,subjects_id;

    public Courses(int course_id, int subjects_id) {
        this.course_id = course_id;
        this.subjects_id = subjects_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(int subjects_id) {
        this.subjects_id = subjects_id;
    }
}
