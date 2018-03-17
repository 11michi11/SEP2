package utility.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MyDatabase {

	private String url;
	private String user;
	private String password;
	private Connection con;
	
	public MyDatabase(String driver, String url, String user, String password) throws SQLException, ClassNotFoundException 
	{
		this.url = url;
		this.user = user;
		this.password = password;
		con = null;
		Class.forName(driver);
		//con = DriverManager.getConnection(url, user, password);
	}

	public void openDatabase() throws SQLException
	{
		con = DriverManager.getConnection(url,user,password);
        System.out.println("Database open ok");
	}
	
	public void closeDatabase() throws SQLException
	{
		con.close();
	}
	
	public ArrayList<Object[]> query(String sql) throws SQLException
	{
		openDatabase();
		
		Statement statement = null;
		ArrayList<Object[]> list = null;
		ResultSet resultSet = null;
		if (sql != null && statement == null)
	    {
	      statement = con.createStatement();
	      /*if (statementElements != null)
	      {
	        for (int i = 0; i < statementElements.length; i++)
	          statement.setObject(i + 1, statementElements[i]);
	      }*/
	    }
	    resultSet = statement.executeQuery(sql);
	    list = new ArrayList<Object[]>();
	    while (resultSet.next())
	    {
	      Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
	      for (int i = 0; i < row.length; i++)
	      {
	        row[i] = resultSet.getObject(i + 1);
	      }
	      list.add(row);
	    }
	    if (resultSet != null)
	      resultSet.close();
	    if (statement != null)
	      statement.close();
	    closeDatabase();
	    return list;
		
		/*Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			Object[] objs=new Object[6];
			objs[0]=rs.getInt(1);
			objs[1]=rs.getString(2);
			objs[2]=rs.getString(3);
			objs[3]=rs.getInt(4);
			objs[4]=rs.getInt(5);
			objs[5]=rs.getBoolean(6);
			list.add(objs);
		}
		return list;*/
	}
	
	
	public int update (String sql) throws SQLException
	{
		openDatabase();
	    Statement statement = con.createStatement();
	    /*if (statementElements != null)
	    {
	      for (int i = 0; i < statementElements.length; i++)
	        statement.setObject(i + 1, statementElements[i]);
	    }*/

	    int result = statement.executeUpdate(sql);

	    closeDatabase();
	    return result;
	  
		/*Statement stmt = con.createStatement();
		stmt.executeQuery(sql);
		return stmt.getUpdateCount();*/
	}
	
	public int[] updateAll(ArrayList<String> sqlList) throws SQLException
	{
		if (sqlList == null)
		      return null;

		openDatabase();
		int[] results = new int[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++)
		{
		   PreparedStatement statement = con.prepareStatement(sqlList.get(i));
		   results[i] = statement.executeUpdate();
		}
		closeDatabase();
		return results;
		
		/*int[] array = new int[sqlList.size()];
		for (int i=0;i<sqlList.size();i++)
		{
			array[i]= update(sqlList.get(i));
		}
		return array;*/
	}
}
