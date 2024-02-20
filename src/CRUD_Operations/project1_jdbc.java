package CRUD_Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;

public class project1_jdbc {

	static Scanner scan =new Scanner(System.in);
	private static Connection connection;
    private static PreparedStatement statement;	
    static int res=0;
    
	public static void main(String[] args) {
		
		
		
		
		String url="jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password="Mr@840420";
        	
		
		try {
			connection=DriverManager.getConnection(url,username,password);
			
		boolean exit=false;
		while(!exit) {
		displayMenu();
		int n=scan.nextInt();
		if(n==1) {
			
			insertQuery(connection);
			
		}
		else if(n==2) {
			
			deleteQuery(connection);
		}
		else if(n==3) {
			
			updateQuery(connection);
		}
		else if(n==4) {
			
			display(connection);
		}
		else if(n==5) {
			
			exit=true;
			System.out.println("THANK YOU");
			
		}
		else {
			
			System.out.println("enetred wrong key :");
			
		}
		}
         } catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			try {
				scan.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			

	}
	
	private static void displayMenu() {
		
		System.out.println("For insert press key 1 :");
		System.out.println("For delete press key 2 :");
		System.out.println("For update press key 3 :");
		System.out.println("For display database press key 4 :");
		System.out.println("For exit press key 5 :");
	}

	static void insertQuery(Connection connection)throws SQLException {
		
        
		String   sql="insert into `employee` (`idemployee`,`employeename`,`dept`,`email`,`salary`) values (?,?,?,?,?)";
        

	
			statement=connection.prepareStatement(sql);
			System.out.println();
			
			System.out.println("insertion starts......");
			
			System.out.println("enter id");
			 int id=scan.nextInt();
			 System.out.println("enter name");
			 String name=scan.next();
			 System.out.println("enter email");
			 String dept=scan.next();

			 System.out.println("enter dept");
			 String email=scan.next();

			 System.out.println("enter salary");
			 int salary=scan.nextInt();
			 
			 statement.setInt(1, id);
			 statement.setString(2, name);
			 statement.setString(3, email);
			 statement.setString(4, dept);
			 statement.setInt(5, salary);
			 
			 res=statement.executeUpdate();
			 System.out.println();
			 
				System.out.println("insertion completed...");

		
		

	}
	
	static void deleteQuery(Connection connection)throws SQLException {
		
		System.out.println("enter the id");
        int id=scan.nextInt();
        System.out.println();
        
        System.out.println("deletion process starts.....");
        System.out.println();
        
		String sql="DELETE from `employee` WHERE `idemployee`=?";
		
		
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			res=statement.executeUpdate();
			 System.out.println();
			 
		     System.out.println("deletion process ends.....");
		     System.out.println();

			
		
		
	}
	
	static void updateQuery(Connection connection) throws SQLException {
	
		System.out.println("select what u wanted to update");
		
		System.out.println("For name update press key 1 :");
		System.out.println("For salary update press key 2 :");
		System.out.println("For department update press key 3 :");
		System.out.println("For display mail update press key 4 :");
		Scanner scan =new Scanner(System.in);
		
		int key=scan.nextInt();
		 System.out.println();
		 
	        System.out.println("updating details .....");
	        System.out.println();

		if(key==1) {
			System.out.println("enetr the name u wanted to update :");
			 String name = scan.next();
			 
			 System.out.println("for which id u wanted to apply");
             int id = scan.nextInt();
             
             String sql = "UPDATE employee SET employeename = ? WHERE idemployee = ?";
             
			
				statement = connection.prepareStatement(sql);
				statement.setString(1, name);
	            statement.setInt(2, id);
	            res = statement.executeUpdate();

			
			
			             
			
		}
		else if(key==2) {
			
			System.out.println("enetr the salary u wanted to increment :");
            int salary =scan.nextInt();
            
			System.out.println("for which id u wanted to apply");
            int id = scan.nextInt();
            
            String sql = "UPDATE employee SET salary = salary+? WHERE idemployee = ?";
            
			
				statement = connection.prepareStatement(sql);
				statement.setInt(1, salary);
	            statement.setInt(2, id);
	            res = statement.executeUpdate();

			
			
		}
		else if(key==3) {
			
			System.out.println("enetr the department u wanted to change to :");
			String dept=scan.next();
			
			System.out.println("for which id u wanted to apply");
			int id = scan.nextInt();
			
            String sql = "UPDATE employee SET dept = ? WHERE idemployee = ?";
            
			
				statement = connection.prepareStatement(sql);
				statement.setString(1, dept);
	            statement.setInt(2, id);
	            res = statement.executeUpdate();

			
			
		}
		else if(key==4) {
			
			System.out.println("enetr the mail u wanted to change to :");
			String mail=scan.next();
			
			System.out.println("for which id u wanted to apply");
			int id = scan.nextInt();
			
            String sql = "UPDATE employee SET email = ? WHERE idemployee = ?";
            
			
				statement = connection.prepareStatement(sql);
				statement.setString(1, mail);
	            statement.setInt(2, id);
	            res = statement.executeUpdate();

			
			
		}
		    System.out.println();
	        System.out.println("updating completed .....");
	        System.out.println();

		
		
		

	}
	
	static void display(Connection connection) throws SQLException {
		
       System.out.println("if u want to see all employee details press 1 ");
       System.out.println("if u wanted to see individual employee details press 2");
       int key=scan.nextInt();
       
       System.out.println();
       System.out.println("employee details .....");
       System.out.println();

       if(key==1) {
    	   ResultSet res=null;
    	   String sql="SELECT * from employee ";
  		 
  			Statement statement;
			statement = connection.createStatement();
			res  =  statement.executeQuery(sql);
			
	           System.out.println("-----------------------------------------------------------------------");

	         while( res.next()) {
	          int id=res.getInt("idemployee");
	          String name=res.getString("employeename");
	          String email=res.getString("email");
	          String dept=res.getString("dept");
	          int salary=res.getInt("salary");
	          
	          System.out.printf("|%-3d |%-9s |%-20s |%-15s |%-12d|\n",id,name,email,dept,salary);
	         }
	         System.out.println("-----------------------------------------------------------------------");

		
  		 
  		          
  	
       }
       else if(key==2) {
    	   
    	System.out.println("eneter the id of employee details that u wanted to see");
   		int id=scan.nextInt();
   		
   		String sql="SELECT * from employee where idemployee=? ";
           
   		ResultSet res=null;
   		
   		
   			statement=connection.prepareStatement(sql);
   			statement.setInt(1, id);
   			 res=statement.executeQuery();

   			System.out.println("-----------------------------------------------------------------");

   		       while( res.next()) {
   		        int id1=res.getInt("idemployee");
   		        String name=res.getString("employeename");
   		        String email=res.getString("email");
   		        String dept=res.getString("dept");
   		        int salary=res.getInt("salary");
                System.out.printf("|%-3d |%-9s |%-20s |%-15s |%-12d|\n",id,name,email,dept,salary);
   		       }
   		       System.out.println("-----------------------------------------------------------------");
   		       
   		      			
   		
       }
       else {
    	   System.out.println("invalid key");
       }
       System.out.println();
       System.out.println();
       System.out.println();

		
	}
		

}
