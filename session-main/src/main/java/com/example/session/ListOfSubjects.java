package com.example.session;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListOfSubjects implements Initializable {
    ObservableList<Courses> ListC;
    ObservableList<Subjects> ListS;

    @FXML
    private Button btn_AddSubject;

    @FXML
    private Button btn_create_Course;

    @FXML
    private TableColumn<Courses, Integer> c_course_id;

    @FXML
    private TableColumn<Subjects, String> c_course_id_in_subj;

    @FXML
    private TableColumn<Courses, Integer> c_subjectS_ID_inCourses;

    @FXML
    private TableColumn<Subjects, Integer> c_subject_id;

    @FXML
    private TableColumn<Subjects, String> c_subject_name;

    @FXML
    private Button goBack;

    @FXML
    private TextField lbl_course;

    @FXML
    private TextField lbl_subject;

    @FXML
    private TableView<Courses> t_courses;

    @FXML
    private TableView<Subjects> t_subjects;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void createCourse(ActionEvent event) throws SQLException {
        DButils utils = new DButils();
        utils.createCourse();
        updateCourses();
    }

    @FXML
    void createSubject(ActionEvent event) throws SQLException {
        String subject = lbl_subject.getText().trim();
        String course = lbl_course.getText().trim();
        if(course.isEmpty() || subject.isEmpty()) return;
        DButils utils = new DButils();
        utils.createSubject(subject, Integer.parseInt(course));
        updateSubjects();
    }

    Integer index = -1;
    Integer indexsubject = -1;
    @FXML
    void getSelected(MouseEvent event) {
        index = t_courses.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        Info.course = (c_subjectS_ID_inCourses.getCellData(index));
        updateSubjects();
    }

    @FXML
    void getSelectedSubject(MouseEvent event) {
        indexsubject = t_subjects.getSelectionModel().getSelectedIndex();
        if (indexsubject <= -1) {
            return;
        }
    }

    @FXML
    void deletecourse(ActionEvent event) throws SQLException {
        try {


        DButils utils = new DButils();
        utils.deleteCourse(c_course_id.getCellData(index));
        updateCourses();
        }catch (Exception e){
            System.out.println("КУРС не должен содержать ПРЕДМЕТ(Ы), если вы хотите его удалить! Удалите все предметы, после чего сможете удалить курс");
        }
    }

    @FXML
    void deletesubject(ActionEvent event) throws SQLException {
        DButils utils = new DButils();
        utils.deletesubject(c_subject_id.getCellData(indexsubject));
        updateSubjects();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCourses();
    }

    public void updateCourses() {
        c_course_id.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("course_id"));
        c_subjectS_ID_inCourses.setCellValueFactory(new PropertyValueFactory<Courses, Integer>("subjects_id"));

        ListC = DButils.getDataCourses();
        t_courses.setItems(ListC);
    }

    public void updateSubjects() {
        c_subject_id.setCellValueFactory(new PropertyValueFactory<Subjects, Integer>("subjects_id"));
        c_subject_name.setCellValueFactory(new PropertyValueFactory<Subjects, String>("subject"));
        c_course_id_in_subj.setCellValueFactory(new PropertyValueFactory<Subjects, String>("course_id"));

        ListS = DButils.getDataSubjects();
        t_subjects.setItems(ListS);
    }

}
