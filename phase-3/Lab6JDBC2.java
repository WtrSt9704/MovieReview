/**************************************************
 * Copyright (c) 2020 KNU DEAL Lab. To Present
 * All rights reserved. 
 **************************************************/
package lab6; // package name 

import java.sql.*; // import JDBC package
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Sample Code for JDBC Practice
 * @author yksuh
 */
public class Lab6JDBC {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="knumovie";
	public static final String USER_PASSWD ="comp322";
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conn = null; // Connection object
		Statement stmt = null;
		
		/*Connect to oracle*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Success!");
		}catch(ClassNotFoundException e)
		{
			System.err.println("error = "+e.getMessage());
			System.exit(1);
		}
		try {
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.err.println("Cannot get a connection: "+ex.getMessage());
			System.exit(1);
		}
		
		while (true) {
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 가입");
			System.out.println("2: 로그인");
			System.out.println("3: 종료");
			int func = sc.nextInt();
			
			if(func==3) break;
			
			if(func==1) {
				signup(conn,stmt);
			}
			else if(func==2)
			{
				login(conn,stmt);
			}
			
			clearScr();
		}
		// Release database resources.
		try {
			// Close the Connection object.
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clearScr() {
	    for (int i = 0; i < 100; i++)
	      System.out.println("");
	}
	
	public static void signup(Connection conn,Statement stmt) {
	try {
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		String sql ="";
		clearScr();
	
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
		
		String tmp;
		
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
		System.out.print("Birthday : ");
		birthday = sc.next();
		sc.nextLine();
		System.out.print("Job : ");
		job = sc.next();
		sc.nextLine();
		System.out.print("Address : ");
		address = sc.nextLine();
		
		tmp=birthday.substring(0, 4);
		age = 2020 - Integer.valueOf(tmp);
		
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
	
	public static void login(Connection conn,Statement stmt) {
		String id;
		String password;
		int membership;
		String sql ="";
		ResultSet rs;
		clearScr();
		System.out.print("ID : ");
		id = sc.next();
		sc.nextLine();
		System.out.print("Password : ");
		password=sc.next();
		sc.nextLine();
						
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			sql = "SELECT ID,PASSWORD,MEMBERSHIP_GRADE FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"' AND ACCOUNT.PASSWORD ='"+password+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) { 
				membership = rs.getInt("MEMBERSHIP_GRADE");
				if(membership==3) {
					mypage2(conn,stmt,id,password);
				}
				else
				{
					mypage1(conn,stmt,id,password);
				}
				
			}
			else {
				System.out.println("login failed");
				}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mypage1(Connection conn,Statement stmt,String id,String password) {
		
		while (true) {	
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 정보수정");
			System.out.println("2: Pasword 수정");
			System.out.println("3: 계정 삭제");
			System.out.println("4: 로그아웃");
			int func = sc.nextInt();
			
			if(func==4) break;
			
			if(func==1) {
				update(conn,stmt,id,password);
			}
			else if(func==2)
			{
				changepwd(conn,stmt,id,password);
			}
			else if(func==3)
			{
				drop(conn,stmt,id,password);
				break;
			}
			
			clearScr();
		}
	}
	
public static void mypage2(Connection conn,Statement stmt,String id,String password) {
		
		while (true) {	
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 정보수정");
			System.out.println("2: Pasword 수정");
			System.out.println("3: 계정 삭제");
			System.out.println("4: 영상 등록");
			System.out.println("5: 영상물 수정");
			System.out.println("6: 로그아웃");
			int func = sc.nextInt();
			
			if(func==6) break;
			
			if(func==1) {
				update(conn,stmt,id,password);
			}
			else if(func==2)
			{
				changepwd(conn,stmt,id,password);
			}
			else if(func==3)
			{
				drop(conn,stmt,id,password);
				break;
			}
			else if(func==4)
			{
				movieenroll(conn,stmt,id);
			}
			else if(func==5)
			{
				clearScr();
				
				String title;
				int movieid;
				System.out.print("Write movie title to update information : ");
				title = sc.next();
				sc.nextLine();
				String sql="";
				ResultSet rs;
				try {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					
					sql = String.format("SELECT ID FROM MOVIE WHERE TITLE ='%s'", title);
					rs = stmt.executeQuery(sql);
					if(rs.next()) { 
						movieid=rs.getInt(1);
						movieupdate(conn,stmt,movieid);
					}
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			clearScr();
		}
	}

public static void movieupdate(Connection conn,Statement stmt,int movieid) {

	String information;
		
	System.out.print("What do you change information?(title, mtype,runtime,startyear,endyear,director,writer,company,descriptions)");
	information = sc.next();
	String sql = String.format("UPDATE MOVIE SET %s = ? where MOVIE.ID = %d",information,movieid);
	int res=0;
	if(information.equals("runtime"))
	{	
		int runtime;
		System.out.print("Input to change : ");
		runtime=sc.nextInt();

		try {	
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, runtime);
			
			res=ps.executeUpdate();
			System.out.print("Update completed");
			
			conn.commit();
		}catch(SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}	
	}
	else if(information.equals("startyear")||information.equals("endyear"))
	{
		String date;
		System.out.print("Input to change : ");
		date=sc.next();
		
		String newdate ="TO_DATE('"+date+"','yyyy-mm-dd')"; 

		try {	
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newdate);
			
			res=ps.executeUpdate();
			System.out.print("Update completed");
			
			conn.commit();
		}catch(SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);	
		}		
	}
	else
	{
		String data;
		System.out.print("Input to change : ");
		data=sc.next();
		
		String newdata =data; 

		try {	
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newdata);
			
			res=ps.executeUpdate();
	
			System.out.print("Update completed");
			
			conn.commit();
		}catch(SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}		
	}
}
	

public static void movieenroll(Connection conn,Statement stmt,String id) {
	String sql="";
	ResultSet rs;
	clearScr();
	int count=0;
	try {
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		
		sql = "SELECT COUNT(*) FROM MOVIE";
		rs = stmt.executeQuery(sql);
		if(rs.next()) { 
			count = rs.getInt(1);
		}
		else {
			System.out.println("Movie do not exist.");
			}
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
	conn.setAutoCommit(false);
	stmt = conn.createStatement();
	sql ="";
	clearScr();
	
	int movieid;
	String title;
	String mtype;
	int runtime;
	String startyear;
	String endyear;
	float rating;
	int numofvotes;
	String director;
	String writer;
	String company;
	String descriptions;
	
	int res=0;
	
	movieid = count+1;
	System.out.print("Title : ");
	title = sc.next();
	sc.nextLine();
	System.out.print("mtype : ");
	mtype=sc.next();
	sc.nextLine();
	System.out.print("runtime : ");
	runtime=sc.nextInt();
	sc.nextLine();
	System.out.print("startyear : ");
	startyear= sc.next();
	sc.nextLine();
	System.out.print("endyear : ");
	endyear= sc.next();
	sc.nextLine();
	System.out.print("director : ");
	director = sc.next();
	sc.nextLine();
	System.out.print("writer : ");
	writer = sc.next();
	sc.nextLine();
	System.out.print("company : ");
	company = sc.next();
	sc.nextLine();
	System.out.print("descriptons : ");
	descriptions = sc.nextLine();
	
	
	sql = String.format("INSERT INTO MOVIE VALUES(%d,'%s','%s',%d,TO_DATE('%s','yyyy-mm-dd'),TO_DATE('%s','yyyy-mm-dd'),'%s',0,0,'%s','%s','%s','%s')",
			movieid,title,mtype,runtime,startyear,endyear,id,director,writer,company,descriptions);
	res= stmt.executeUpdate(sql);
	if(res != 0)
		 System.out.println("movie enrollment completed.");
	
	conn.commit();
	}
	catch(SQLException ex2) {
		System.err.println("sql error = "+ ex2.getMessage());
		System.exit(1);
	}
}
public static void drop(Connection conn, Statement stmt,String id,String password) {
	
	clearScr();
	String sql = String.format("DELETE FROM ACCOUNT where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'",id,password);
	try {	conn.setAutoCommit(false);
	stmt = conn.createStatement();
		int res = stmt.executeUpdate(sql);
		System.out.println("Having deleted ID");
		conn.commit();
		conn.close();
		
	} catch (SQLException ex2) {
		System.err.println("sql error = "+ex2.getMessage());
		System.exit(1);
	}
}

public static void update(Connection conn, Statement stmt,String id,String password) {
	
		clearScr();
		String information;
		
		System.out.print("What do you change? (phonenumber,name,membership,job,address,birthday)");
		
		information = sc.next();
		
		String sql = String.format("UPDATE ACCOUNT SET %s = ? where ACCOUNT.id ='%s' and ACCOUNT.password = '%s'",information,id,password);
		int res=0;
		if(information.equals("membership"))
		{	
			int runtime;
			System.out.print("Input to change : ");
			runtime=sc.nextInt();

			try {	
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, runtime);
				
				res=ps.executeUpdate();
				System.out.print("Update completed");
				
				conn.commit();
			}catch(SQLException ex2) {
				System.err.println("sql error = "+ex2.getMessage());
				System.exit(1);
			}	
		}
		
		else
		{
			String data;
			System.out.print("Input to change : ");
			data=sc.next();
			
			String newdata =data; 

			try {	
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, newdata);
				
				res=ps.executeUpdate();
		
				System.out.print("Update completed");
				
				conn.commit();
			}catch(SQLException ex2) {
				System.err.println("sql error = "+ex2.getMessage());
				System.exit(1);
			}		
		}
	}

	public static void changepwd(Connection conn, Statement stmt,String id,String password) {
		String cdpwd;
		System.out.print("input your new password : ");
		cdpwd = sc.next();
		sc.nextLine();
		
		String sql = String.format("UPDATE ACCOUNT SET PASSWORD = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", cdpwd,id,password);
		try {	conn.setAutoCommit(false);
		stmt = conn.createStatement();
			int res = stmt.executeUpdate(sql);
			System.out.println("Having changed password");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
	}
	
/*	public static void changejob(Connection conn, Statement stmt,String id,String password) {
	
		System.out.print("input your new job : ");
		String newjob = sc.next();
		sc.nextLine();
		
		String sql = String.format("UPDATE ACCOUNT SET JOB = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", newjob,id,password);
		try {	conn.setAutoCommit(false);
		stmt = conn.createStatement();
			int res = stmt.executeUpdate(sql);
			System.out.println("Having changed job");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
	}
	public static void changeaddress(Connection conn, Statement stmt,String id,String password) {
		String cdaddress;
		System.out.print("input your new password : ");
		cdaddress = sc.next();
		sc.nextLine();
		
		String sql = String.format("UPDATE ACCOUNT SET ADDRESS = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", cdaddress,id,password);
		try {	conn.setAutoCommit(false);
		stmt = conn.createStatement();
			int res = stmt.executeUpdate(sql);
			System.out.println("Having changed address");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
	}
*/
}

