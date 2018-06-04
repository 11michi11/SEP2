package server.model.persistance;

import java.sql.*;
import java.util.ArrayList;

public class Database implements DBInterface{

    private String url;
    private String user;
    private String pw;
    private Connection connection;

    /**
     * Constructor.
     *
     * @param driver the name of database driver
     * @param url    the full url for the database
     * @param user   the username for database
     * @param pw     the password for database
     * @throws ClassNotFoundException if driver cannot be loaded
     */
    public Database(String driver, String url, String user, String pw)
            throws ClassNotFoundException {
        this.url = url;
        this.user = user;
        this.pw = pw;
        connection = null;
        Class.forName(driver);
    }

    private void openDatabase() throws SQLException {
        connection = DriverManager.getConnection(url, user, pw);
    }

    private void closeDatabase() throws SQLException {
        connection.close();
    }

    @Override
    public ArrayList<Object[]> query(String sql, Object... statementElements)
            throws SQLException {
        openDatabase();

        PreparedStatement statement = null;
        ArrayList<Object[]> list = null;
        ResultSet resultSet = null;
        if (sql != null && statement == null) {
            statement = connection.prepareStatement(sql);
            if (statementElements != null) {
                for (int i = 0; i < statementElements.length; i++)
                    statement.setObject(i + 1, statementElements[i]);
            }
        }
        resultSet = statement.executeQuery();
        list = new ArrayList<Object[]>();
        while (resultSet.next()) {
            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
            for (int i = 0; i < row.length; i++) {
                if (resultSet.getMetaData().getColumnType(i+1) == Types.ARRAY) {
                    row[i] = resultSet.getArray(i+1).getArray();
                }
                else {
                    row[i] = resultSet.getObject(i + 1);
                }
            }
            list.add(row);
        }
        if (resultSet != null)
            resultSet.close();
        if (statement != null)
            statement.close();
        closeDatabase();
        return list;
    }

    @Override
    public int update(String sql, Object... statementElements)
            throws SQLException {
        openDatabase();
        PreparedStatement statement = connection.prepareStatement(sql);
        if (statementElements != null) {
            for (int i = 0; i < statementElements.length; i++)
                statement.setObject(i + 1, statementElements[i]);
        }

        int result = statement.executeUpdate();

        closeDatabase();
        return result;
    }

    @Override
    public int[] updateAll(ArrayList<String> sqlList) throws SQLException {
        if (sqlList == null)
            return null;

        openDatabase();
        int[] results = new int[sqlList.size()];
        for (int i = 0; i < sqlList.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(sqlList.get(i));
            results[i] = statement.executeUpdate();
        }
        closeDatabase();
        return results;
    }

}
