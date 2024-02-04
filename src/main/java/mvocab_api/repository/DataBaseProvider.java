package mvocab_api.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseProvider {
    public static Connection connectToDb() {
        String url = "jdbc:mariadb://milaxycloud:3306/mvocab";
        String username = "root";
        String password = "kubop4elA@%@mdd8tH";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnectFromDb(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeSQLRequest(String sqlRequest) {
        ResultSet resultSet = null;
        try {
            Connection connection = DataBaseProvider.connectToDb();
            resultSet = connection.createStatement().executeQuery(sqlRequest);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void execSQLReq(Connection connection, String sqlRequest) {
        try {
            //Connection connection = DataBaseProvider.connectToDb();
            connection.createStatement().execute(sqlRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
