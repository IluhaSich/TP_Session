package com.example.session;

public class StudentsGrades {
    int exam_details_id;
    int student_id;
    int grade;

    public StudentsGrades(int exam_details_id, int student_id, int grade) {
        this.exam_details_id = exam_details_id;
        this.student_id = student_id;
        this.grade = grade;
    }

    public int getExam_details_id() {
        return exam_details_id;
    }

    public void setExam_details_id(int exam_details_id) {
        this.exam_details_id = exam_details_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
