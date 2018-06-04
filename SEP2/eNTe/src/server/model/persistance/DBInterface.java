package server.model.persistance;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DBInterface {

    /**
     * Returning the result from an SQL query in the form of an Object array for
     * each row. All Object arrays are returned in an ArrayList.
     *
     * @param sql               the SQL statement to execute. Starting with "SELECT".
     * @param statementElements a number of statement elements each representing an element for a
     *                          placeholder in the SQL string.
     * @return an ArrayList with an Object[] for each row in the query result
     * @throws SQLException if something went wrong in the connection or query
     */
    ArrayList<Object[]> query(String sql, Object... statementElements) throws SQLException;

    /**
     * An SQL update.
     *
     * @param sql               the sql updates to execute. Could start with "UPDATE", "INSERT", "CREATE", ...
     * @param statementElements a number of statement elements each representing an element for a
     *                          placeholder in the SQL string.
     * @return an integer representing the number of updates given by the database
     * @throws SQLException if something went wrong in the connection or update
     */
    int update(String sql, Object... statementElements) throws SQLException;

    /**
     * A number of SQL updates.
     *
     * @param sqlList an ArrayList containing SQL updates to execute.
     * @return an integer array representing the number of updates given by the database for each statement
     * @throws SQLException if something went wrong in the connection or update
     */
    int[] updateAll(ArrayList<String> sqlList) throws SQLException;
}
