package com.example.session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DButils {

    private Connection connection;

    public DButils() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "123456789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "123456789");
        } catch (Exception e) {
            return null;
        }
    }


    public String authenticate(String username, String password) {
        try {
            String query = "SELECT role FROM users WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            closeConnection();
        }

        return "not found";
    }

    public String getId(String username, String password) {
        try {
            String query = "SELECT user_id FROM users WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            closeConnection();
//        }

        return "not found";
    }


    public String getTeacherId(int id) {
        try {
            String query = "SELECT teacher_id FROM teachers WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(id));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("teacher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            closeConnection();
//        }

        return "not found";
    }

    public String getAttempt() {
        try {
            String query = "SELECT COALESCE(MAX(attempt_num), 0) AS max_attempt FROM exam_details WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(Info.id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int maxAttempt = resultSet.getInt("max_attempt");
                return String.valueOf(maxAttempt);
            } else {
                System.out.println("Не найдено ни одной записи для данного student_id");
                return "0";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            closeConnection();
//        }

        return "0";
    }

    public String createExam() {
        try {
            String query = "INSERT INTO exam_details (subject_id, teacher_id, student_id,grade, attempt_num) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(Info.subject_id));
            statement.setString(2, String.valueOf(Info.teacher_id));
            statement.setString(3, String.valueOf(Info.id));
            statement.setString(4, String.valueOf(-1));
            statement.setString(5, String.valueOf(Info.attempt_num));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Student успешно зарегистрирован";

            } else {
                return "Ошибка при регистрации пользователя";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя";

        }
//        finally {
//            closeConnection();
//        }
    }

    public String getcourse() {
        try {
            String query = "SELECT course_id FROM students WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(Info.id));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("course_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return "not found";
    }

    public String user_Registration(String username, String password, String role) {
        try {
            String query = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "User успешно зарегистрирован";

            } else {
                return "Ошибка при регистрации пользователя";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя";

        }
    }

    public String student_Registration(int course_id, int user_id) {
        try {
            String query = "INSERT INTO students(course_id,user_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course_id);
            statement.setInt(2, user_id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Student успешно зарегистрирован";

            } else {
                return "Ошибка при регистрации пользователя(Student)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя(Student)";

        } finally {
            closeConnection();
        }
    }

    public String deputy_Registration(int user_id) {
        try {
            String query = "INSERT INTO deputys(user_id) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Deputy успешно зарегистрирован";

            } else {
                return "Ошибка при регистрации пользователя(Deputy)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя(Deputy)";

        } finally {
            closeConnection();
        }
    }

    public String teacher_Registration(int subject_id, int user_id) {
        try {
            String query = "INSERT INTO students(course_id,user_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, subject_id);
            statement.setInt(2, user_id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Teacher успешно зарегистрирован";

            } else {
                return "Ошибка при регистрации пользователя(Teacher)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя(Teacher)";

        } finally {
            closeConnection();
        }
    }

    public int getLastUserId() {
        try {
            String query = "Select Max(user_id) From users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<Users> getDatausers() {
        Connection conn = ConnectDb();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Users(Integer.parseInt(rs.getString("user_id")), rs.getString("login"),
                        rs.getString("password"), rs.getString("role")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ObservableList<Subjects> getDataSubjects() {
        Connection conn = ConnectDb();
        ObservableList<Subjects> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from subjects where course_id = ?");
            int course_id = Info.course;
            ps.setString(1, String.valueOf(course_id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Subjects(Integer.parseInt(rs.getString("subjects_id")), rs.getString("subject"), Integer.parseInt(rs.getString("course_id"))));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public static ObservableList<Exams> getDataExams() {
        Connection conn = ConnectDb();
        ObservableList<Exams> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from exam_details where teacher_id = ?"); //
            ps.setString(1, String.valueOf(Info.teacher_id_));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Exams(Integer.parseInt(rs.getString("exam_details_id")),
                        Integer.parseInt(rs.getString("subject_id")),
                        Integer.parseInt(rs.getString("teacher_id")),
                        Integer.parseInt(rs.getString("student_id")),
                        Integer.parseInt(rs.getString("grade")),
                        Integer.parseInt(rs.getString("attempt_num"))
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ObservableList<Courses> getDataCourses() {
        Connection conn = ConnectDb();
        ObservableList<Courses> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from courses"); //
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Courses(Integer.parseInt(rs.getString("course_id")),
                        Integer.parseInt(rs.getString("subjects_id"))
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ObservableList<StudentsGrades> getExamsDepyty() {
        Connection conn = ConnectDb();
        ObservableList<StudentsGrades> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from exam_details where pass is null"); //
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new StudentsGrades(Integer.parseInt(rs.getString("exam_details_id")),
                        Integer.parseInt(rs.getString("student_id")),
                        Integer.parseInt(rs.getString("grade"))
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void putGrade(String grade, String id) throws SQLException {
        if (Integer.parseInt(grade) > 1 && Integer.parseInt(grade) <= 5) {
            String sql = "update exam_details set grade= '" + grade + "' where exam_details_id = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        }
    }

    public void putPass(String pass, String id) throws SQLException {
        String sql = "update exam_details set pass= '" + pass + "' where exam_details_id = '" + id + "'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute();
    }

    public void createCourse() throws SQLException {
        String sql = "insert into courses(subjects_id) Values(?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, (getMaxCourse() + 1));
        st.execute();
    }

    public int getMaxCourse() throws SQLException {
        String query = "Select Max(subjects_id) From courses";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    public void createSubject(String subject, int course) throws SQLException {
        String sql = "insert into subjects(subject,course_id) Values(?,?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, subject);
        st.setInt(2, course);
        st.execute();
    }

    public void deleteCourse(Integer index) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, String.valueOf(index));
        st.execute();
    }

    public void deletesubject(Integer indexsubject) throws SQLException {
        String sql = "DELETE FROM subjects WHERE subjects_id = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, String.valueOf(indexsubject));
        st.execute();
    }
}


