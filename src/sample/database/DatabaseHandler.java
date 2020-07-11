package sample.database;

import sample.entity.Task;
import sample.entity.User;

import java.sql.*;

public class DatabaseHandler {
    static final String CON_STR = "jdbc:postgresql://localhost:5432/toDoList";
    static final String LOGIN = "EmCharlotte";
    static final String PWD = "1990";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(CON_STR, LOGIN, PWD)) {
            try (Statement statement = connection.createStatement()) {
                //если никакие данные не передаются через переменную в createTable, то используем
                //statement
                // если в запросе createTable передается переменная, то используем preparedStatement

                // int result = statement.executeUpdate(create);

                //execute(update) - только когда мы хотим добавить/обновить данные
                //если считать данные - то не подойдет, так как метод просто возвращает число

                // System.out.println("result " + result);

                //если возвращает result 0, то все хорошо
            }
        }
    }

    public void signUpUser(User user) throws SQLException {
        String insert = "INSERT INTO " + Constants.USERS_TABLE + "(" + Constants.USERS_FIRSTNAME + "," +
                Constants.USERS_SURNAME + "," + Constants.USERS_LOGIN + "," + Constants.USERS_PASSWORD + "," +
                Constants.USERS_GENDER + ") VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(CON_STR, LOGIN, PWD)) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getGender());

                int res = statement.executeUpdate();
                System.out.println("res= " + res);
            }
        }
    }

    public ResultSet userSignedUpCheck (User user){
    ResultSet result = null;
        if (!user.getLogin().equals("")&&!user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Constants.USERS_TABLE + " WHERE " + Constants.USERS_LOGIN + "=?" +
                    " AND " + Constants.USERS_PASSWORD + "=?";
            try (Connection connection = DriverManager.getConnection(CON_STR, LOGIN, PWD)) {
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, user.getLogin());
                    statement.setString(2, user.getPassword());
                    result = statement.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
                System.out.println("Поле логин/пароль не может быть пустым");
        }
        return result;
    }

    public void addTask(Task task){
        String insert = "INSERT INTO " + Constants.TASKS_TABLE + "(" +Constants.USERS_ID + ","+
                Constants.TASKS_CREATED_ON + "," + Constants.TASKS_DESCRIPTION + "," + Constants.TASK + ") " +
                "VALUES (?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(CON_STR, LOGIN, PWD)) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setInt(1, task.getUserId());
                statement.setTimestamp(2, task.getDatecreated());
                statement.setString(3, task.getDescription());
                statement.setString(4, task.getTask());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAllTasks(int userId){
        String query = "SELECT COUNT(*) FROM " + Constants.TASKS_TABLE + " WHERE " + Constants.USERS_ID + "=?";
        int num=0;
        try (Connection connection = DriverManager.getConnection(CON_STR, LOGIN, PWD)) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                num = resultSet.getInt(1);}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return num;
    }
}
