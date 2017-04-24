package objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Dao {
	final String url = "jdbc:mysql://www.papademas.net/tickets?autoReconnect=true&useSSL=false&user=fp411&password=411";
	public static Connection c= null;
	public static Statement stmt = null;

	public final String usersTable = "w_mei_users";
	public final String ticketsTable = "w_mei_tickets";
	public final String commentsTable = "w_mei_comments";

	

	/*
	 * Dao - default constructor. 
	 */
	public Dao(){
		// createTicketTable();
		// createUserTable();
		// createCommentTable();
		// modifyTable();
		// addUsers(new Users("ktang", "qwer", "kevin", "tang", 2));
		// submitTicket(new Tickets("20170420121500", "2017-04-20 12:15:00",
		// "2017-04-20 12:15:00", "open", "testTitle", "testDescription",
		// "ktang", 3, "testCategory"));
//		submitTicket(new Tickets("20170420141301", "2017-04-20 14:13:01", "2017-04-20 14:13:01", "open", "testTitle",
//				"testDescription", "ktang", 3, "testCategory"));
//		submitTicket(new Tickets("20170420141305", "2017-04-20 14:13:05", "2017-04-20 14:13:05", "open", "testTitle",
//				"testDescription", "ktang", 3, "testCategory"));
//		submitTicket(new Tickets("20170420141334", "2017-04-20 14:13:34", "2017-04-20 14:13:34", "open", "testTitle",
//				"testDescription", "ktang", 3, "testCategory"));
//		submitTicket(new Tickets("20170420141350", "2017-04-20 14:13:50", "2017-04-20 14:13:50", "open", "testTitle",
//				"testDescription", "ktang", 3, "testCategory"));
//		addComment(new Comments("20170420141334", "Testing Comments", "2017-04-20 14:47:48"));
//		updateQuery("w_mei_comments", "comment='new comment', commentDate='2017-04-20 15:05:35'", "tid='20170420141334' and pid=1");
    }
	
	/*
	 * startConnection - starts a connection with the database 
	 * using static variables to ensure only one connection is active at a time
	 */
	public void startConnection(){
		try{
			c = DriverManager.getConnection(url);
			stmt = c.createStatement();
			System.out.println("Database connection established...");
		}catch(SQLException e){
			System.out.println("Error Connecting to Database. Quitting. " + e);
			System.exit(0);
			
		}
		
	}
	
	/*
	 * closeConnection - closes the connection made by startConnection
	 */
	public void closeConnection(){
		try{
		c.close();
		stmt.close();
		System.out.println("Connection Closed");
		}catch(SQLException e){
			System.out.println("Connection cannot be closed.");
			System.exit(0);
		}
	}
	/*
	 * createBD- method that creates the necessary Database table to perform the analysis
	 */
	public void createTicketTable(){
		try{
			startConnection();
			System.out.println("Attempting to create table...");
			
			String query = "CREATE TABLE " + ticketsTable + 
					"(pid INTEGER NOT NULL AUTO_INCREMENT, " +
					"tid VARCHAR(16) UNIQUE, " +
					"dateCreated DATE, " +
					"lastUpdated DATE, " +
					"status VARCHAR(10), " +
					"title VARCHAR(150), " +
					"description TEXT, " + 
					"submitter VARCHAR(25), " + 
					"priority INTEGER, " + 
					"category VARCHAR(25), " + 
					"PRIMARY KEY ( pid ))";
			stmt.executeUpdate(query);
			System.out.println("Table " + ticketsTable + " created...");
		
		}catch (SQLException e){
			System.out.println("Attempted to create table: " + e);
		}catch(Exception e){
			System.out.print(e.getMessage());
			System.exit(0);
		} finally{
			closeConnection();
		}
	}

	public void createUserTable(){
		try{
			startConnection();
			System.out.println("Attempting to create table...");
			
			String query = "CREATE TABLE " + usersTable + 
					"(pid INTEGER NOT NULL AUTO_INCREMENT, " +
					"username VARCHAR(25) UNIQUE NOT NULL, " +
					"password VARCHAR(25) UNIQUE NOT NULL, " +
					"firstName VARCHAR(25) NOT NULL, " +
					"lastName VARCHAR(25) NOT NULL, " +
					"privilege INTEGER NOT NULL, " +
					"PRIMARY KEY ( pid ))";
			stmt.executeUpdate(query);
			System.out.println("Table " + usersTable + " created...");
		
			closeConnection();
		}catch (SQLException e){
			System.out.println("Attempted to create table: " + e);
		}catch(Exception e){
			System.out.print(e.getMessage());
			System.exit(0);
		}
	}

	public void createCommentTable(){
		try{
			startConnection();
			System.out.println("Attempting to create table...");
			
			String query = "CREATE TABLE " + commentsTable + 
					"(pid INTEGER NOT NULL AUTO_INCREMENT, " +
					"tid VARCHAR(16) NOT NULL, " +
					"comment TEXT, " +
					"commentDate DATE NOT NULL, " +
					"PRIMARY KEY ( pid ), " +
					"FOREIGN KEY (tID) REFERENCES "+ ticketsTable +"(tid))";
			stmt.executeUpdate(query);
			System.out.println("Table " + commentsTable + " created...");
		
		}catch (SQLException e){
			System.out.println("Attempted to create table: " + e);
		}catch(Exception e){
			System.out.print(e.getMessage());
			System.exit(0);
		}finally{
			closeConnection();
		}
	}
	/*
	 * insertDB - populates the table that createDB creates with the BankRecords
	 */
	public void submitTicket(Tickets t){
		try{

			System.out.println("Inserting Rows into Table: " + ticketsTable);
			 
			// The sql query that will populate the database table BankRecords
			String sql=	"INSERT INTO " + ticketsTable +" (tid, dateCreated, lastUpdated, status, title, description, submitter, priority, category) " +
					"VALUES ('" +
					 t.gettID() + "', '" +
					 t.getDateCreated() + "', '" + 
					 t.getLastUpdated() + "', '" + 
					 t.getStatus() + "', '" +
					 t.getTitle() + "', '" +
					 t.getDescription() + "', '" +
					 t.getSubmitter() + "', " +
					 t.getPriority() + ", '" + 
					 t.getCategory()+ "')";
			startConnection();
			stmt.execute(sql);
			System.out.println("Insertion complete...");
			
		}catch (SQLException e){
			System.out.println("fail to insert: " + e);
		}finally{
			closeConnection();
		}
	}

	public void addUsers(Users u){
		try{

			System.out.println("Inserting Rows into Table: " + usersTable);
			 
			// The sql query that will populate the database table BankRecords
			String sql=	"INSERT INTO " + usersTable +" (username, password, firstName, lastName, privilege) " +
					"VALUES ('" +
					 u.getUsername() + "', '" +
					 u.getPassword().hashCode() + "', '" + 
					 u.getFirstName() + "', '" + 
					 u.getLastName() + "', " + 
					 u.getPrivilege() + ")";
			startConnection();
			stmt.execute(sql);
			System.out.println("Insertion complete...");
			
		}catch (SQLException e){
			System.out.println("fail to insert: " + e);
		}finally{
			closeConnection();
		}
	}

	public void addComment(Comments c){
		try{

			System.out.println("Inserting Rows into Table: " + commentsTable);
			 
			// The sql query that will populate the database table BankRecords
			String sql=	"INSERT INTO " + commentsTable +" (tid, comment, commentDate) " +
					"VALUES ('" +
					 c.gettID()+ "', '" +
					 c.getComment() + "', '" + 
					 c.getCommentDate() + "')";
			startConnection();
			stmt.execute(sql);
			System.out.println("Insertion complete...");
			
		}catch (SQLException e){
			System.out.println("fail to insert: " + e);
		}finally{
			closeConnection();
		}
	}
	
	/**
	 * 
	 * @param table
	 * @param columNames - seperated by commas
	 * @return
	 */
	public ResultSet selectQuery(String table, String columnNames){
		String sql = "SELECT " + columnNames + " FROM " + table + " ORDER BY pid DESC";
		ResultSet rs = null;
		try{
			startConnection();
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			//closeConnection();
		}
		
		return rs;
	}
	
	public Users getUser(String username){
		String sql = "SELECT * FROM " + usersTable + "  WHERE username='" + username + "'";
		ResultSet rs = null;
		Users u=null;
		try{
			startConnection();
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
			if (rs.next()){
				u = new Users(rs.getString("username"), rs.getString("pass"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("privilege"));
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			closeConnection();
		}
		return u;
	}
	
	public Tickets getTicket(String ticketID){
		String sql = "SELECT * FROM " + ticketsTable + "  WHERE tid='" + ticketID + "'";
		ResultSet rs = null;
		Tickets tic=null;
		try{
			startConnection();
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
			if (rs.next()){
				tic = new Tickets(rs.getString("tid"), rs.getString("dateCreated"), rs.getString("lastUpdated"), 
						rs.getString("status"), rs.getString("title"), rs.getString("description"), rs.getNString("submitter"), 
						rs.getInt("priority"), rs.getString("category"));
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}finally{
			closeConnection();
		}
		return tic;
	}
	
	/*
	 * if newValue truly is a make sure to add ' before and after the string
	 * between where conditions add "and"
	 * ex) updateQuery("w_mei_tickets", "description='new description', priority=1", "tid='20170420141305' and pid=4");
	 */
	public void updateQuery(String table, String columNameAndNewValue, String where){
		String query = "UPDATE " + table +
				" SET " + columNameAndNewValue +
				" WHERE " + where;
		startConnection();
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	public void modifyTable(){
		startConnection();
		String query = "ALTER TABLE " + ticketsTable + " " + 
						"MODIFY dateCreated DATETIME; " + 
						"ALTER TABLE " + ticketsTable + " " + 
						"MODIFY lastUpdated DATETIME; " + 
						"ALTER TABLE " + commentsTable + " " + 
						"MODIFY commentDate DATETIME NOT NULL;";
		try {
			System.out.println("modifying table");
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to modify table: " + e);
		}
		closeConnection();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dao();
	}

}
