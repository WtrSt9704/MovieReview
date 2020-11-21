/**************************************************
 * Copyright (c) 2020 KNU DEAL Lab. To Present
 * All rights reserved. 
 **************************************************/
 // package name 

import java.sql.*; // import JDBC package
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lab6JDBC {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	public static void main(String[] args) {
		
		Connection conn = null; // Connection object
		Statement stmt = null;	// Statement object
		Scanner scan = new Scanner(System.in);
		int select_menu;
		int complete = 0;
		String ID;
		String password;
		String ph;
		String name;
		int membership;
		String gender;
		int age;
		String birthday;
		String job;
		String Address;
		String statement;
		
		
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object 
			System.out.println("complete....wait....");
		}catch(ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try{
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD); 
		}
		catch(SQLException ex) 
		{
			ex.printStackTrace();
			System.err.println("DB error");
			System.exit(1);
		}
		System.out.println("Select menu");
			System.out.println("3. 평가 내역 확인");		//등록
			System.out.println("4. 평균 평가 내역 확인");
			select_menu = scan.nextInt();
			String id  = "afss1";
			
			if(select_menu == 3)
			{
				// 계정의 전체 Rating	
				totalRating(conn, stmt, id);
			}
			else if(select_menu == 4)
			{
					
				AverageRating(conn, stmt, id);
			}
			else
			{
				showMovieInfo(conn, stmt, 60, id);
			}
		
		
		
		
	}
	//계정의 전제 Rating
	public static void totalRating(Connection conn, Statement stmt, String UserID) 
	{
		
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select MOVIE.Title, RATING.STARS FROM MOVIE, RATING WHERE RATING.ACCOUNT_ID = '"+UserID+"' and rating.movie_id = movie.id";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			String ans1;
			String ans2;
			System.out.println("Title | Stars");
			while(rs.next()) {
					ans1 = rs.getString(1);
					ans2 = rs.getString(2);
					System.out.println(ans1+"|"+ans2);
				
			}

			
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Release database resources.
				try {
					// Close the Statement object.
					stmt.close(); 
					// Close the Connection object.
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	//Admin
	public static void AdminRating(Connection conn, Statement stmt) 
	{
		
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select Rating.Account_id, movie.title, rating.Stars from movie,"
					+ " Rating where rating.Movie_id = movie.id";
			rs = stmt.executeQuery(sql);
			String ans1;
			String ans2;
			String ans3;
			int i = 1;
			System.out.println("Number|Account |Title | Stars");
			while(rs.next()) {
					ans1 = rs.getString(1);
					ans2 = rs.getString(2);
					ans3 = rs.getString(3);
					System.out.println(i+"|"+ans1+"|"+ans2+"|"+ans3);
				i++;
				
			}

			
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Release database resources.
				try {
					// Close the Statement object.
					stmt.close(); 
					// Close the Connection object.
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//계정의 평균 Rating
	public static void AverageRating(Connection conn, Statement stmt, String UserID) 
	{
		
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select avg(rating.stars) FROM MOVIE, RATING WHERE RATING.ACCOUNT_ID = '"+UserID+"' and rating.movie_id = movie.id";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			double ans1;
			while(rs.next())
			{
				ans1 = rs.getDouble(1);
				System.out.println(UserID+"'s Average : "+ans1);
			}

			
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Release database resources.
				try {
					// Close the Statement object.
					stmt.close(); 
					// Close the Connection object.
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	// 무비 정보 보기
	public static void showMovieInfo(Connection conn, Statement stmt, int id, String UserID) 		//title -> movie_id(int)
	{
		
		ResultSet rs = null;
		Scanner sn = new Scanner(System.in);
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select Title, mType, runtime, Start_year, Rating, Num_of_votes, Director, "
					+ "Writer, Company, Descriptions"
					+" from movie"
					+" where movie.id = "+id;
			rs = stmt.executeQuery(sql);
			String ans1;
			String ans2 ;
			String ans3;
			String ans4;
			String ans5;
			String ans6;
			String ans7;
			String ans8;
			String ans9;
			String ans10;
	
			char rating;
			String grade;
			
			while(rs.next())
			{
				ans1 = rs.getString(1);
				ans2 = rs.getString(2);
				ans3 = rs.getString(3);
				ans4 = rs.getString(4);
				ans5 = rs.getString(5);
				ans6 = rs.getString(6);
				ans7 = rs.getString(7);
				ans8 = rs.getString(8);
				ans9 = rs.getString(9);
				ans10 = rs.getString(10);
				
				System.out.print("Title : "+ans1);
				System.out.println("|Movie type : "+ans2);
				System.out.print("Runtime : "+ans3);
				System.out.println("|Start Year : "+ans4);
				System.out.print("Rating : "+ans5);
				System.out.println("|the number of vote : "+ans6);
				System.out.print("Director : "+ans7);
				System.out.println("|Writer : "+ans8);
				System.out.println("company : "+ans9);
				System.out.println("Discription");
				System.out.println(ans10);
			}
			String aa;
			//PreparedStatement pstmt = null;
			//평가
			while(true)
			{
			System.out.print("평가 하시겠습니까? (y/n)");
			rating = sn.next().charAt(0);
			if(rating == 'n' || rating == 'N')
				break;
			//평가 한다
			else if(rating == 'y' || rating == 'Y')
				{	
					System.out.print("몇점?");
					grade = sn.next();
					//rating 테이블에서 특정조건의 값이 존재 하는가?
					sql = "select * from rating where Account_id = '"+UserID+"' and movie_id = "+id;
					rs = stmt.executeQuery(sql);
					int res = 0;	
					
					//존재 한다면 그 값을 업데이트
					if(rs.next() == true)
					{
						// exist
						sql = "update rating set stars = "+grade+" where Account_id = '"+UserID+"' and MOVIE_ID = "+id;
						System.out.println(sql);
						res = stmt.executeUpdate(sql);
						System.out.println(res);
						if(res != 0)
							System.out.println("갱신되었습니다.");
					}
					//없으면 새값 할당
					else
					{
						//not exist
						sql = "select max(Rating_id) from rating";
						rs = stmt.executeQuery(sql);
						 rs.next();
						 int maxnum = rs.getInt(1);
						 maxnum = maxnum+1;
						 sql = "insert into rating values( "+maxnum
								 +", '"+UserID+"', "+grade+", "+id+")";
						 System.out.println(sql);
						 res = stmt.executeUpdate(sql);
						 if(res != 0)
							 System.out.println("추가 되었습니다.");
					}
					
					
					break;
						}
			else
			{
				System.out.println("다시 선택해 주세요");
			}
					
				}
			
			
		
			//실수 방지를 위한 롤백
			conn.rollback();
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Release database resources.
				try {
					// Close the Statement object.
					stmt.close(); 
					// Close the Connection object.
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
/*	public static final String DEPARTMENT = "DEPARTMENT";
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String PROJECT = "PROJECT";
	public static final String DEPT_LOACTIONS = "DEPT_LOCATIONS";
	public static final String WORK_ON = "WORK_ON";
	
	public static void main(String[] args) {
		Connection conn = null; // Connection object
		Statement stmt = null;	// Statement object
		
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object 
			System.out.println("Success!");
		}catch(ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try{
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD); 
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
			
			doTask1(conn, stmt);
			doTask2(conn, stmt);
		
		
		
		
		
		
	}
	
	public static void doTask1(Connection conn, Statement stmt) {
		String sql1 = "DROP TABLE DEPT_LOCATIONS CASCADE CONSTRAINT";
		String sql2 = "DROP TABLE DEPENDENT CASCADE CONSTRAINT";
		String sql3 = "DROP TABLE WORKS_ON CASCADE CONSTRAINT";
		String sql4 = "DROP TABLE  PROJECT CASCADE CONSTRAINT";
		String sql5 = "DROP TABLE EMPLOYEE CASCADE CONSTRAINT";
		String sql6 = "DROP TABLE DEPARTMENT CASCADE CONSTRAINT";
		String sql = "";
		int depart_Cnt = 1;
		int em_cnt = 1;
		int work_cnt = 1;
		int project_cnt = 1;
		int dependent_cnt = 1;
		int location_cnt = 1;
	


		// Make a connection

		
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			///Create a statement object
			stmt = conn.createStatement();
			// Let's execute an SQL statement.
			int res = 0;
			
			res = stmt.executeUpdate(sql1); 
			if(res == 0) 
				System.out.println("DEPT_LOCATINS was successfully dropped.");
			
			res = stmt.executeUpdate(sql2); 
			
			if(res == 0) 
				System.out.println("DEPENDENT was successfully dropped.");

			res = stmt.executeUpdate(sql3); 
			if(res == 0) 
				System.out.println("WORK_ON was successfully dropped.");
			
			res = stmt.executeUpdate(sql4); 
			if(res == 0) 
				System.out.println("PROJECT was successfully dropped.");
			
			res = stmt.executeUpdate(sql5); 
			if(res == 0) 
				System.out.println("EMPLOYEE was successfully dropped.");
			
			res = stmt.executeUpdate(sql6); 
			if(res == 0) 
				System.out.println("DEPARTMENT was successfully dropped.");
			
			StringBuffer sb = new StringBuffer();
			//DEPARTMENT
			sb.append("CREATE TABLE DEPARTMENT( ");
			sb.append("Dname VARCHAR(15) NOT NULL, ");
			sb.append("Dnumber NUMBER NOT NULL,");
			sb.append("Mgr_ssn CHAR(9) DEFAULT '888665555'NOT NULL, ");
			sb.append("Mgr_start_date DATE,");
			sb.append("PRIMARY KEY (Dnumber), ");
			sb.append("UNIQUE (Dname))");
			sql = sb.toString();
			// Try 'CREATE TABLE ...'
			res = stmt.executeUpdate(sql); 
			if(res == 0) 
				System.out.println("DEPARTMENT was successfully created.");
			sql = "";
			sb.setLength(0);
			
			//EMPLOYEE
			sb.append("CREATE TABLE EMPLOYEE(");
			sb.append("Fname VARCHAR(15) NOT NULL,");
			sb.append(" Minit CHAR,");
			sb.append("Lname VARCHAR(15) NOT NULL,");
			sb.append("Ssn CHAR(9), ");
			sb.append("Bdate Date, ");
			sb.append("Address VARCHAR(30), ");
			sb.append("Sex CHAR, ");
			sb.append("Salary NUMBER(10,2), ");
			sb.append("Super_ssn CHAR(9), ");
			sb.append("Dno NUMBER DEFAULT 1 NOT NULL, ");
			sb.append("PRIMARY KEY (Ssn)");
			sb.append(")");
			sql = sb.toString();
			res = stmt.executeUpdate(sql);
			if(res == 0) 
				System.out.println("EMPLOYEE was successfully created.");
			sb.setLength(0);
			sql = "";
			
			
			
			//PROJECT
			sb.append("CREATE TABLE PROJECT(");
			sb.append("Pname VARCHAR(15), ");
			sb.append("Pnumber NUMBER NOT NULL, ");
			sb.append("Plocation VARCHAR(15), ");
			sb.append("Dnum NUMBER NOT NULL, ");
			sb.append("PRIMARY KEY(Pnumber),");
			sb.append("FOREIGN KEY(Dnum) REFERENCES DEPARTMENT(Dnumber)");
			sb.append(")");
			sql = sb.toString();
			res = stmt.executeUpdate(sql);
			if(res == 0) 
				System.out.println("PROJECT was successfully created.");
			sb.setLength(0);
			sql = "";
			
			//WORK_ON
			sb.append("CREATE TABLE WORKS_ON(");
			sb.append("Essn CHAR(9) NOT NULL, ");
			sb.append("Pno NUMBER NOT NULL, ");
			sb.append("Hours DECIMAL(3,1), ");
			sb.append("PRIMARY KEY(Essn, Pno), ");
			sb.append("FOREIGN KEY(Essn) REFERENCES EMPLOYEE(Ssn), ");
			sb.append("FOREIGN KEY(Pno) REFERENCES PROJECT(Pnumber))");
			sql = sb.toString();
			res = stmt.executeUpdate(sql);
			if(res == 0) 
				System.out.println("WORK_ON was successfully created.");
			sb.setLength(0);
			sql = "";
			
			//DEPENDENT
			sb.append("CREATE TABLE DEPENDENT(");
			sb.append("Essn CHAR(9) NOT NULL, ");
			sb.append("Dependent_name VARCHAR(15) NOT NULL, ");
			sb.append("Sex CHAR, ");
			sb.append("Bdate DATE, ");
			sb.append("Relationship VARCHAR(8), ");
			sb.append("PRIMARY KEY(Essn, Dependent_name), ");
			sb.append("FOREIGN KEY(Essn) REFERENCES EMPLOYEE(Ssn))");
			sql = sb.toString();
			res = stmt.executeUpdate(sql);
			if(res == 0) 
				System.out.println("DEPENDENT was successfully created.");
			sb.setLength(0);
			sql = "";
			//
			
			sb.append("CREATE TABLE DEPT_LOCATIONS(");
			sb.append("Dnumber NUMBER NOT NULL,");
			sb.append("Dlocation VARCHAR(15) NOT NULL,");
			sb.append("PRIMARY KEY(Dnumber,Dlocation),");
			sb.append("FOREIGN KEY(Dnumber) REFERENCES DEPARTMENT(Dnumber))");
			sql = sb.toString();
			res = stmt.executeUpdate(sql);
			if(res == 0) 
				System.out.println("DEPT_LOCATIONS was successfully created.");
			sb.setLength(0);
			
			
			
			// Make the table permanently stored by a commit.
			//conn.commit();	
			
			try {
				File file  = new File("company.txt");
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line = "";
				
				
				int i = 0;
				StringTokenizer token;
				while((line = reader.readLine())!= null)
				{
					
					switch(line)
					{
					case "$DEPARTMENT":
					{
						line = reader.readLine();
						token = new StringTokenizer(line);
						sb.append("INSERT INTO DEPARTMENT VALUES(");
						token = new StringTokenizer(line);
						String Dname = token.nextToken("#");
						sb.append("'"+Dname+"',");
						String Dnumber = token.nextToken("#");
						sb.append(Dnumber+",");
						String Mgr_ssn = token.nextToken("#");
						sb.append("'"+Mgr_ssn+"',");
						String Mgr_start_date = token.nextToken("#");
						sb.append("TO_DATE('"+Mgr_start_date+"','yyyy-mm-dd'))");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
						//System.out.println(sb);
							System.out.println("Department"+depart_Cnt+" success");
							depart_Cnt++;
						sb.setLength(0);
						
						
						break;
					}
					case "$EMPLOYEE":
					{
						line = reader.readLine();
						token = new StringTokenizer(line);
						sb.append("INSERT INTO EMPLOYEE VALUES(");
						String fname = token.nextToken("#");
						sb.append("'"+fname+"',");
						String Mname = token.nextToken("#");
						sb.append("'"+Mname+"',");
						String Lname = token.nextToken("#");
						sb.append("'"+Lname+"',");
						String Ssn = token.nextToken("#");
						sb.append("'"+Ssn+"',");
						String Bdate = token.nextToken("#");
						sb.append("TO_DATE('"+Bdate+"','yyyy-mm-dd'),");
						String address  = token.nextToken("#");
						sb.append("'"+address+"',");
						String sex = token.nextToken("#");
						sb.append("'"+sex+"',");
						String salary = token.nextToken("#");
						sb.append(salary+",");
						String super_ssn = token.nextToken("#");
						if(super_ssn == "NULL")
							sb.append(super_ssn+",");
						else
							sb.append("'"+super_ssn+"',");
						String Dno = token.nextToken("#");
						sb.append(Dno+")");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
						//System.out.println(sb);
						System.out.println("employee"+em_cnt+" success");
						em_cnt++;
						sb.setLength(0);
						
						
						
						break;
					}
					case "$PROJECT":
					{
						line = reader.readLine();
						sb.append("INSERT INTO PROJECT VALUES(");
						token = new StringTokenizer(line);
						String Pname = token.nextToken("#");
						sb.append("'"+Pname+"',");
						String Pnumber= token.nextToken("#");
						sb.append(Pnumber+",");
						String Plocation= token.nextToken("#");
						sb.append("'"+Plocation+"',");
						String Dnum = token.nextToken("#");
						sb.append(Dnum+")");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
						//System.out.println(sb);
						System.out.println("project"+project_cnt+" success");
						project_cnt++;
						sb.setLength(0);
						break;
					}
					case "$DEPT_LOCATIONS":
					{
						line = reader.readLine();
						sb.append("INSERT INTO DEPT_LOCATIONS VALUES(");
						token = new StringTokenizer(line);
						String Dnumber = token.nextToken("#");
						sb.append(Dnumber+",");
						String Dlocation = token.nextToken("#");
						sb.append("'"+Dlocation+"')");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
					//	System.out.println(sb);
						System.out.println("dept_locations"+location_cnt+" success");
						location_cnt++;
						sb.setLength(0);
						break;
					}
					case "$WORKS_ON":
					{
						line = reader.readLine();
						sb.append("INSERT INTO WORKS_ON VALUES(");
						token = new StringTokenizer(line);
						String Essn = token.nextToken("#");
						sb.append("'"+Essn+"',");
						String Pno = token.nextToken("#");
						sb.append(Pno+",");
						String Hours = token.nextToken("#");
						sb.append(Hours+")");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
						//System.out.println(sb);
						System.out.println("work_on"+work_cnt+" success");
						work_cnt++;
						sb.setLength(0);
						break;
					}
					case "$DEPENDENT":
					{
						line = reader.readLine();
						sb.append("INSERT INTO DEPENDENT VALUES(");
						token = new StringTokenizer(line);
						String Essn = token.nextToken("#");
						sb.append("'"+Essn+"',");
						String dependent_name = token.nextToken("#");
						sb.append("'"+dependent_name+"',");
						String sex = token.nextToken("#");
						sb.append("'"+sex+"',");
						String Bdate = token.nextToken("#");
						sb.append("TO_DATE('"+Bdate+"','yyyy-mm-dd'),");
						String rel = token.nextToken("#");
						sb.append("'"+rel+"')");
						sql = sb.toString();
						res = stmt.executeUpdate(sql);
						//System.out.println(sb);
						System.out.println("dependent"+dependent_cnt+" success");
						dependent_cnt++;
						sb.setLength(0);
						break;
					}
					default:
					{
						System.out.println("error");
					}
					}
				}
				//add constraint 
				
			}catch(FileNotFoundException e)
			{
				
			}catch (IOException e) {
				System.out.println(e);
			}
			

		}catch(SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		
		
	}
		
	public static void doTask2(Connection conn, Statement stmt) {
		
		ResultSet rs = null;
		int pandan = 1;
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "SELECT MIN(salary) from EMPLOYEE, DEPENDENT where EMPLOYEE.sex = 'M' and Ssn = Essn and Relationship = 'Spouse'"
					+ "UNION SELECT MIN(salary) from EMPLOYEE, DEPENDENT where EMPLOYEE.sex = 'F' and Ssn = Essn and Relationship = 'Spouse'";
			rs = stmt.executeQuery(sql);
			System.out.println("<< query 1 result >>");
			while(rs.next()) {
				// Fill out your code	
				String i1 = rs.getString(1);
				if(pandan == 1)
				System.out.println("Male : "+i1);
				else
					System.out.println("FeMale : "+i1);
				pandan++;
				
			}
			//rs.close();
			
			
			// Q2: Complete your query.
			sql = "SELECT Fname, Lname, Salary from EMPLOYEE where Dno > all (select Dno from Employee where Dno = 1)" ;
			rs = stmt.executeQuery(sql);
			System.out.println("<< query 2 result >>");
			System.out.println("Fname Lname Salary");
			while(rs.next()) {
				String q2_1 = rs.getString(1); 		//fname			
				String q2_2 = rs.getString(2);		//Lname
				String q2_3 = rs.getString(3);		//salary
				
				System.out.println(q2_1+" "+q2_2+" "+q2_3);
			}
			//rs.close();
						
			// Q3: Complete your query.
			sql = "select Pname, Plocation, Fname, Dname from project, department full outer join employee on department.Dnumber = employee.Dno where Plocation = 'Bellaire'  order by Dname";		
			rs = stmt.executeQuery(sql);
			System.out.println("<< query 3 result >>");
			while(rs.next()) {
				String q3_1 = rs.getString(1);		//Pname
				String q3_2 = rs.getString(2);		//Plocation
				String q3_3 = rs.getString(3);		//Fname
				String q3_4 = rs.getString(4);		//Dname
				System.out.println(q3_1+" "+q3_2+" "+q3_3+" "+q3_4);
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Release database resources.
				try {
					// Close the Statement object.
					stmt.close(); 
					// Close the Connection object.
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}*/
}
