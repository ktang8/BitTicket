/*
 *Wei Shao Mei & Kevin Tang
 *ITMD411-Final Lab
 *4/28/2017
 *Dao.java
 */
import java.io.*;
import java.sql.*;
import java.text.NumberFormat;

public class Dao extends {
	
	// final variables to reduce repetitive clutter in code.
	//final String url = "jdbc:mysql://www.papademas.net:3306/tickets?"
	//		+ "autoReconnect=true&useSSL=false"
	//		+ "&user=fp411&password=411";
    final String url = "jdbc:mysql:local.db"
	final String cname = "com.mysql.jdbc.Driver";
	static Connection c= null;
	static Statement stmt = null;

	final String usersTable = "w_mei_users"
	final String ticketsTable = "w_mei_tickets"
	final String commentsTable = "w_mei_comments"
	final String rolesTable = "w_mei_roles"

	/*
	 * Dao - default constructor. 
	 */
	public Dao(){
        File file = new Fie("local.db"):
        if(file.exists()){
            System.out.println("file already exists, moving forward.");    
        }
        else{
            createTable();
            insertTable();
        }
    }
	
	/*
	 * startConnection - starts a connection with the database 
	 * using static variables to ensure only one connection is active at a time
	 */
	public void startConnection(){
		try{
			Class.forName(cname);
			c = DriverManager.getConnection(url);
			stmt = c.createStatement();
			System.out.println("Database connection established...");
		}catch(SQLException e){
			System.out.println("Error Connecting to Database. Quitting.");
			System.exit(0);
			
		}catch(Exception e){
			System.out.println(cname+" class not found");
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
			System.exit(0)
		}
	}
	
	/*
	 * createBD- method that creates the necessary Database table to perform the analysis
	 */
	public void createTables(){
		try{
			
			System.out.println("Attempting to create table...");
			
			String sql= "CREATE TABLE W_MEI_tab" +
						"(PID INT NOT NULL AUTO_INCREMENT,"+
						"ID VARCHAR(7) UNIQUE NOT NULL,"+
						"INCOME NUMERIC(8,2) NOT NULL,"+
						"PEP VARCHAR(3) NOT NULL)";
			stmt.executeUpdate(sql);
			System.out.println("Table W_MEI_tab created...");
		
	
		}catch (SQLException e){
			System.out.println("Table 'W_MEI_tab' already exist");
		}catch(Exception e){
			System.out.print(e.getMessage());
			System.exit(0)
		}
	}/*
	 * createBD- method that creates the necessary Database table to perform the analysis
	 */
	public void createTicketTable(){
		try{
			
			System.out.println("Attempting to create table...");
			
			String query = "CREATE TABLE " + ticketsTable + 
					"(pid INTEGER NOT NULL AUTO_INCREMENT, " +
					"id VARCHAR(7), " +
					"tid INTEGER UNIQUE, " +
					"dateCreated VARCHAR(), " +
					"PRIMARY KEY ( pid ))";
			stmt.executeUpdate(query);
			System.out.println("Table W_MEI_tab created...");
		
	
		}catch (SQLException e){
			System.out.println("Table 'W_MEI_tab' already exist");
		}catch(Exception e){
			System.out.print(e.getMessage());
			System.exit(0)
		}
	}
	/*
	 * insertDB - populates the table that createDB creates with the BankRecords
	 */
	public void submitTicket(BankRecords [] bankrec){
		try{

			System.out.println("Inserting Rows into Table, this may take a while...");
			
			for(int i=0;i<recs.length;i++){
				BankRecords br=bankrec[i];
				// The sql query that will populate the database table BankRecords
				String sql=	"INSERT INTO " + ticketsTable +" (ID, INCOME, PEP) VALUES('"+
							br.getId()+"', "+
							br.getIncome()+", '"+
							br.getPep()+"');";

				stmt.executeUpdate(sql);
			}
			System.out.println("Insertion complete...");
			
		}catch (SQLException e){
			System.out.println("Insertion has already been done.");
		}
	}

	public ResultSet retrieveRecords(){
	
		String sql = "SELECT id, income, pep FROM W_MEI_tab ORDER BY pep DESC, income DESC";
		ResultSet rs = null;
		try{
			
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}

		return rs;
	}
	

}

