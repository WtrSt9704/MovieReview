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
	
	public static void login(Connection conn,Statement stmt) {
		String id;
		String password;
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
			// Q3: Complete your query.
			sql = "SELECT ID,PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID ='"+id+"' AND ACCOUNT.PASSWORD ='"+password+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) { 
				mypage1(conn,stmt,id,password);
				
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
	public static void update(Connection conn, Statement stmt,String id,String password) {
	
		clearScr();
		System.out.print("What do you change? : 1.JOB 2.Address 3.Exit");
		int func = sc.nextInt();
		
		
		if(func==3) return;
		
		if(func==1) {
			changejob(conn,stmt,id,password);
		}
		else if(func==2)
		{
			changeaddress(conn,stmt,id,password);
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
	
	public static void changejob(Connection conn, Statement stmt,String id,String password) {
	
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
	public static void drop(Connection conn, Statement stmt,String id,String password) {
		
		clearScr();
		String sql = String.format("DELETE FROM ACCOUNT where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'",id,password);
		try {	conn.setAutoCommit(false);
		stmt = conn.createStatement();
			int res = stmt.executeUpdate(sql);
			System.out.println("Having deleted ID");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = "+ex2.getMessage());
			System.exit(1);
		}
	}
}

