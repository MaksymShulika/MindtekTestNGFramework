package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishConnection() throws SQLException {

        connection = DriverManager.getConnection(
                ConfigReader.getProperty("ElarDBURL"),
                ConfigReader.getProperty("ElarDBUserName"),
                ConfigReader.getProperty("ElarDBPassword")
        );

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }

    public static List<Map<String, Object>> runQuery(String query) throws SQLException {

        resultSet = statement.executeQuery(query);

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> tableData = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int columIndex = 1; columIndex <= metaData.getColumnCount(); columIndex++) {
                rowData.put(metaData.getColumnName(columIndex), resultSet.getString(metaData.getColumnName(columIndex)));
            }
            tableData.add(rowData);
        }

        return tableData;
    }

    public static void closeDataBase() throws SQLException {

        if (connection!=null){
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (resultSet!=null){
            resultSet.close();
        }
    }
}
