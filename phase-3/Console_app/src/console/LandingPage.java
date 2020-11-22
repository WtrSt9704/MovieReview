package console;

import java.sql.*;
import java.util.Scanner;

public class LandingPage {
	public static Scanner sc = new Scanner(System.in);
	/* return true if success login, return false if terminated*/
	public static boolean display(Connection conn, Statement stmt, User user) {
		while (true) {
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 가입");
			System.out.println("2: 로그인");
			System.out.println("3: 종료");
			int func = sc.nextInt();
			sc.nextLine(); // flush the buffer
			if(func==3) break;
			
			if(func==1) {
				signup(conn,stmt);
			}
			else if(func==2)
			{
				if (login(conn, stmt, user)) {
					return true;
				};
			}
			
			Util.clearScr();
		}
		return false;
	}
	
	
	public static void signup(Connection conn,Statement stmt) {
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql ="";
			Util.clearScr();
		
			String id;
			String password;
			String phonenumber;
			String name;
			int membership_grade;//멤버쉽은 그냥
			char gender;
			int age;
			String birthday;
			String job;
			String address;
			
			int res=0;
			
			System.out.print("ID : ");
			id = sc.next();
			sc.nextLine();
			System.out.print("Password : ");
			password = sc.next();
			sc.nextLine();
			System.out.print("Phonenumber : ");
			phonenumber=sc.next();
			sc.nextLine();
			System.out.print("Name : ");
			name=sc.next();
			sc.nextLine();
			System.out.print("What is your membership grade? : ");
			membership_grade = sc.nextInt();
			sc.nextLine();
			System.out.print("Gender : ");
			gender=sc.next().charAt(0);
			sc.nextLine();
			System.out.print("Age : ");
			age = sc.nextInt();
			sc.nextLine();
			System.out.print("Birthday : ");
			birthday = sc.next();
			sc.nextLine();
			System.out.print("Job : ");
			job = sc.next();
			sc.nextLine();
			System.out.print("Address : ");
			address = sc.nextLine();
			sc.nextLine();
			
			sql = "INSERT INTO ACCOUNT VALUES('"+id+"', '"+password+"', '"+phonenumber+"', '"+name+"', "+membership_grade+", '"+gender+"', "+age+", TO_DATE('"+birthday+"','yyyy-mm-dd'), '"+job+"', '"+address+"')";
			res= stmt.executeUpdate(sql);
			if(res != 0)
				 System.out.println("Siun up completed.");
			conn.commit();
		}catch(SQLException ex2) {
			System.err.println("sql error = "+ ex2.getMessage());
			System.exit(1);
		}	
	}
	
	public static boolean login(Connection conn, Statement stmt, User user) {
		String id;
		String password;
		String sql ="";
		ResultSet rs;
		Util.clearScr();
		System.out.print("ID : ");
		id = sc.next();
		user.setId(id);
		sc.nextLine();
		System.out.print("Password : ");
		password=sc.next();
		user.setPW(password);
		sc.nextLine();
						
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			// Q3: Complete your query.
			sql = "SELECT ID,PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"' AND ACCOUNT.PASSWORD ='"+password+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) { 
				rs.close();
				return true;
			}
			else {
				System.out.println("login failed");
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
