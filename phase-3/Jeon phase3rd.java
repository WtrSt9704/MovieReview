/**************************************************
 * Copyright (c) 2020 KNU DEAL Lab. To Present
 * All rights reserved. 
 **************************************************/
 // package name 

import java.sql.*; // import JDBC package
import java.util.*;
import java.util.Date;
import java.text.*;
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
			System.out.println("5. movie Imfo 확인");
			select_menu = scan.nextInt();
			String id  = "afss1";
			
			if(select_menu == 3)
			{
				// 계정의 전체 Rating	
				totalRating(conn, stmt, id);
			}
			else if(select_menu == 4)
			{
					
				//AverageRating(conn, stmt, id);
				AdminRating(conn, stmt);
			}
			else if(select_menu == 5)
			{
				showMovieInfo(conn, stmt, 61, id);
			}
			else
			{
				System.out.println("error");
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
	/*
	//계정의 평균 Rating -> showmovieInfo로 이관
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
	}*/
	// 무비 정보 보기
	public static void showMovieInfo(Connection conn, Statement stmt, int id, String UserID) 		//title -> movie_id(int)
	{
		
		ResultSet rs = null;
		ResultSet rs2 = null; //averageRating about movie
		Scanner sn = new Scanner(System.in);
		try {
			conn.setAutoCommit(false); // auto-commit disabled  
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select Title, mType, runtime, Start_year, Num_of_votes, Director, "
					+ "Writer, Company, Descriptions"
					+" from movie"
					+" where movie.id = "+id;
			rs = stmt.executeQuery(sql);

			
			String ans1;
			String ans2 ;
			String ans3;
			String ans4;
			String ans5 = null;			//rating
			String ans6;
			String ans7;
			String ans8;
			String ans9;
			String ans10;
			String ans4_1;
			char rating;
			String grade;
			 String transDate;
			 int yy,dd,mm;
			 Date date = null;
			 
			
			while(rs.next())
			{
				ans1 = rs.getString(1);
				ans2 = rs.getString(2);
				ans3 = rs.getString(3);
				ans4 = rs.getString(4);

				SimpleDateFormat sDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				try {
					date = sDate.parse(ans4);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SimpleDateFormat Ddate = new SimpleDateFormat("yyyy-mm-dd");
				//System.out.println(Ddate.format(date));
				
				
				ans6 = rs.getString(5);
				ans7 = rs.getString(6);
				ans8 = rs.getString(7);
				ans9 = rs.getString(8);
				ans10 = rs.getString(9);
				

				System.out.print("Title : "+ans1);
				System.out.println(" | Movie type : "+ans2);
				System.out.print("Runtime : "+ans3+" minutes");
				System.out.println(" | Start Year : "+Ddate.format(date));		//성공
				System.out.println("the number of vote : "+ans6);
				System.out.print("Director : "+ans7);
				System.out.println(" | Writer : "+ans8);
				System.out.println("company : "+ans9);
				System.out.println("Discription");
				System.out.println(ans10);
			}
			sql = "select avg(rating.stars) from movie, rating where movie.id = "+id+" and movie.id = rating.MOVIE_ID";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ans5 = rs.getString(1);
				//System.out.println("Rating : "+ans5);
			}
			
			//적힌거랑 다르면
			int res = 0;
			//Rating평균 비교하기
			double a4,a5;
			String ans4_2 = null;
			sql = "select rating from movie where ID = "+id;
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ans4_2 = rs.getString(1);
			}
			a4 = Double.parseDouble(ans4_2);
			a5 = Double.parseDouble(ans5);
			//System.out.println("a4(movie에 적힌거) : "+a4);
			//System.out.println("a5(평균) : "+a5);
			
			if(a4 != a5)
			{
				sql = "UPDATE MOVIE  SET RATING = "+ans5+" where ID = "+id;
				//System.out.println(sql);
				res = stmt.executeUpdate(sql);
				System.out.println("Rating : "+ans5);
				
			
			}
			else
			{
				System.out.println("Rating : "+ans5);
			}
			
			
		
		
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
					 res = 0;	
					
					//존재 한다면 그 값을 업데이트
					if(rs.next() == true)
					{
						// exist
						sql = "update rating set stars = "+grade+" where Account_id = '"+UserID+"' and MOVIE_ID = "+id;
						//System.out.println(sql);
						res = stmt.executeUpdate(sql);
						//System.out.println(res);
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
			conn.commit();
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
	
}