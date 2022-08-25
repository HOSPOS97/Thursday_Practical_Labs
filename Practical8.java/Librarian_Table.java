
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Project1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Librarian name.");
		String lName = sc.nextLine();
		System.out.println("Enter Librarian Contact number."); 
		String lContactNumber = sc.nextLine();
		System.out.println("Enter Librarian email Id."); 
		String lemailid = sc.nextLine();
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LibraryManagementSystem","root","123456");
//			System.out.println("Database connected succesfully.");
		
			Statement s = con.createStatement();
//			s.execute("Create table Librarian(Lid int(100) auto_increment primary key, LName varchar(60) not null, LContactnumber varchar(20) , LemailId varchar(30), CONSTRAINT UC_Librarian UNIQUE (LContactnumber,LemailId))");
//			System.out.println("Table created succesfully.");
			
			PreparedStatement ps = con.
			prepareStatement("insert into Librarian(LName, LContactNumber, LemailId) values(?,?,?)");
			ps.setString(1, lName);
			ps.setString(2, lContactNumber);
			ps.setString(3, lemailid);
			ps.executeUpdate();
			ResultSet rs = s.executeQuery("Select LName, LContactNumber, LemailId from Librarian");
			System.out.println("Libararian_Name"+"\t\t"+"Libararian_ContactNumber"+"\t\t"+"Libararian_email_ID");
			while(rs.next()){
				String liName = rs.getString(1);
				String liContactNumber = rs.getString(2);
				String liemailid = rs.getString(3);
				System.out.println(liName+"\t\t"+liContactNumber+"\t"+liemailid);
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
