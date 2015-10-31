import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

public class ConnectionManager {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   //static final String DB_URL = "jdbc:mysql://localhost/testpo";
	   static final String DB_URL = "jdbc:mysql://localhost/schooldb";
	   
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "peterman";
	   
	   private static Connection conn;
	   private static Statement stmt;
	   
	   private static void OpenConnection(){
		   try{
			   System.out.println("Connecting to database...");
			   conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   }
		   catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	   }
	   
	   private static void CloseConnection(){
		   try{
			   conn.close();
		   }
		   catch(SQLException se){
			   se.printStackTrace();
		   }
	   }
	   
	   private static void PushSchools(ArrayList<School> schools){
		   TruncateSchools();		   
	   }
	   
	   private static void TruncateSchools(){
		   try{
			   OpenConnection();
			   stmt = conn.createStatement();
			   String sql;
			   sql = "truncate table schoolLocation;";
			   stmt.execute(sql);
			   
		   }
		   catch (Exception e){
			   
		   }
		   finally{
			   CloseConnection();
		   }
	   }

	   
	   public static void main(String[] args) {
	   conn = null;
	   stmt = null;
	   
	   System.out.println("Truncating Tables...");
	   TruncateSchools();
	   
	   /*try{
	      //STEP 2: Register JDBC driver
	      //Class.forName("com.mysql.jdbc.Driver");
	      OpenConnection();

	      
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "select Name from school";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String name = rs.getString("Name");


	         //Display values
	         System.out.print("Name: " + name);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }
	      catch(SQLException se2){
	      }
	      CloseConnection();
	   }
	   */
	}//end main
}
