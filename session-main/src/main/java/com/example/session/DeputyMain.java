package com.example.session;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeputyMain implements Initializable {
    ObservableList<StudentsGrades> ListSG;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_donotpass;

    @FXML
    private Button btn_pass;


    @FXML
    private TableColumn<StudentsGrades, Integer> id_student;

    @FXML
    private TableColumn<StudentsGrades, Integer> exam_id;

    @FXML
    private TableColumn<StudentsGrades, Integer> student_grade;


    @FXML
    private TableView<StudentsGrades> t_sturdent_grade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTableGrades();
    }

    int index = -1;
    @FXML
    void getSelected(MouseEvent event) {
        index = t_sturdent_grade.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
    }

    @FXML
    void put_pass_false(ActionEvent event) throws SQLException {
        DButils manager = new DButils();
        manager.putPass("0" ,exam_id.getCellData(index).toString());
        updateTableGrades();
    }

    @FXML
    void put_pass_true(ActionEvent event) throws SQLException {
        DButils manager = new DButils();
        manager.putPass("1" ,exam_id.getCellData(index).toString());
        updateTableGrades();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) btn_back.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml ")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void updateTableGrades() {
        exam_id.setCellValueFactory(new PropertyValueFactory<StudentsGrades, Integer>("exam_details_id"));
        id_student.setCellValueFactory(new PropertyValueFactory<StudentsGrades, Integer>("student_id"));
        student_grade.setCellValueFactory(new PropertyValueFactory<StudentsGrades, Integer>("grade"));

        ListSG = DButils.getExamsDepyty();
        t_sturdent_grade.setItems(ListSG);
    }

    public void updateTableLogin() {

    }
}
