/**************************************************
 * Copyright (c) 2020 KNU DEAL Lab. To Present
 * All rights reserved. 
 **************************************************/
package lab6; // package name 

// import JDBC package
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
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
	
	//	String sql = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,?,?,?)" ;
		//PreparedStatement ps = conn.prepareStatement(sql);
		clearScr();
	
		stmt = conn.createStatement();
		
		
		
		String id;
		String password;
		String phonenumber;
		String name;
		int membership_grade;//멤버쉽은 그냥
		String gender;
		int age;
		String birthday;
		String job;
		String address;
		String newdate;
		String tmp2;
		
		int tmp=0;
		int res=0;
		
		
		System.out.print("ID(필수): ");//필수 입력값
		id = sc.next();
		
		System.out.print("Password(필수) : ");//필수 입력값
		password = sc.next();

		System.out.print("Phonenumber(필수)(xxx-xxxx-xxxx): ");//필수 입력값
		phonenumber=sc.next();
		
		
		sc.nextLine();
		System.out.print("Name : ");
		name=sc.nextLine();
		
		/*if(name.equals(""))
		{
			name = "NULL";
		}*/
		
		System.out.print("What is your membership grade?(필수) :0.basic 1. primium 2.prime ");//필수입력값
		membership_grade = sc.nextInt();
		
		//sc.nextLine();
		System.out.print("Gender(M or F) :");
		gender = sc.nextLine();
	/*	if(gender.equals(""))
		{
			gender="";
			
		}*/
	
		
		sc.nextLine();
		System.out.print("Birthday(yyyy-mm-dd) : ");
		birthday = sc.nextLine();
		if(birthday.equals(""))
		{
			age = 0;
			newdate = "NULL";
			
		}
		else
		{
			tmp2=birthday.substring(0, 4);
			age = 2020 - Integer.valueOf(tmp2);
		
			newdate ="TO_DATE('"+birthday+"','yyyy-mm-dd')";
						
		}
		
		System.out.print("Job : ");
		job = sc.nextLine();
		/*if(job.equals(""))
		{
			job="NULL";
			
		}*/
		
		System.out.print("Address : ");
		address = sc.nextLine();
		/*if(job.equals(""))
		{
			address="NULL";
			
		}*/
	
		String sql;
		
		if(age==0) {
			 sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d , '%s', NULL, %s, '%s', '%s')", id,password,phonenumber,name,membership_grade,gender,newdate,job, address) ;
		}
		else
		{
			 sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d , '%s', %d, %s, '%s', '%s')", id,password,phonenumber,name,membership_grade,gender,age,newdate,job, address) ;
		}
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
	//conn.setAutoCommit(false);
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
	
	String newdate;
	
	int res=0;
	
	movieid = count+1;
	System.out.print("Title(필수) : ");//필수
	title = sc.next();

	sc.nextLine();
	System.out.print("mtype(필수) : knuOriginal,movie,tvSeries 중 입력 ");
	mtype=sc.next();

	sc.nextLine();
	System.out.print("runtime(필수) : ");
	runtime=sc.nextInt();

	sc.nextLine();
	System.out.print("startyear(필수,yyyy-mm-dd) : ");
	startyear= sc.next();

	sc.nextLine();
	System.out.print("endyear(yyyy-mm-dd) : ");
	endyear= sc.nextLine();
	
	if(endyear.equals(""))
	{
		newdate = "NULL";
		
	}
	else
	{
		newdate ="TO_DATE('"+endyear+"','yyyy-mm-dd')";				
	}
	
	
	System.out.print("director : ");
	director = sc.nextLine();
	
	System.out.print("writer : ");
	writer = sc.nextLine();
	
	System.out.print("company : ");
	company = sc.nextLine();
	
	System.out.print("descriptons : ");
	descriptions = sc.nextLine();
	
	
	sql = String.format("INSERT INTO MOVIE VALUES(%d,'%s','%s',%d,TO_DATE('%s','yyyy-mm-dd'),%s,'%s',0,0,'%s','%s','%s','%s')",
			movieid,title,mtype,runtime,startyear,newdate,id,director,writer,company,descriptions);
	res= stmt.executeUpdate(sql);
	
	
		//conn.commit();
	
	genreof(conn,stmt,movieid);
	
	int check=1;
	while(true)
	{
		System.out.print("버젼 추가하시겠습니까?(0입력시 종료)");
		check=sc.nextInt();
		if(check==0)
		{
			break;
		}
		else
		{
			version(conn,stmt,movieid,title);
		}
	}
	check=1;
	sc.nextLine();
	if(mtype.equals("tvSeries"))
	{
		while(true)
		{
			System.out.print("에피소드 추가하시겠습니까?(0입력시 종료)");
			check=sc.nextInt();
			if(check==0)
			{
				break;
			}
			else
			{
				episode(conn,stmt,movieid);
			}
		}
	}
}
	catch(SQLException ex2) {
		System.err.println("sql error = "+ ex2.getMessage());
		System.exit(1);
	}
}

public static void episode(Connection conn, Statement stmt, int movieid)
{
	String sql="";
	ResultSet rs;

	
	try {
	conn.setAutoCommit(false);
	stmt = conn.createStatement();

	String eptitle;
	int epnum;
	String seasontitle;
	int seasonnum;
	
	System.out.print("ep title : ");
	eptitle = sc.next();
	
	System.out.print("ep num :");
	epnum= sc.nextInt();
	
	sc.nextLine();
	System.out.print("season title : ");
	seasontitle = sc.nextLine();
	
	System.out.print("season num");
	seasonnum=sc.nextInt();
	
	int res=0;
	sql =String.format("insert into episode values(%d,'%s',%d,'%s',%d)", movieid,eptitle,epnum,seasontitle,seasonnum);
	res= stmt.executeUpdate(sql);
	
	if(res != 0)
		 System.out.println("episode upload");
	conn.commit();
	}
	
	catch(SQLException ex2) {
		System.err.println("sql error = "+ ex2.getMessage());
		System.exit(1);
	}
}
public static void version(Connection conn, Statement stmt,int movieid, String title)
{	
	
	String sql="";
	ResultSet rs;

	
	try {
		conn.setAutoCommit(false);
	stmt = conn.createStatement();

	String region;
	String language;
	int original;
	
	System.out.print("Region : ");
	region = sc.next();
	
	System.out.print("language :");
	language = sc.next();
	
	System.out.print("Is orginal title? : 0.no 1.yes");
	original = sc.nextInt();
	
	int res=0;
	sql =String.format("insert into version values(%d,'%s','%s','%s',%d)", movieid,title,region,language,original);
	res= stmt.executeUpdate(sql);
	
	if(res != 0)
		 System.out.println("version completed");
	conn.commit();
	}
	catch(SQLException ex2) {
		System.err.println("sql error = "+ ex2.getMessage());
		System.exit(1);
	}
	
}
public static void genreof(Connection conn, Statement stmt,int movieid)
{	
	String genre="";
	String sql="";
	ResultSet rs;
	int genrenum;
	
	try {
	conn.setAutoCommit(false);
	stmt = conn.createStatement();
	System.out.println("input genre : Action, Adventure, Comedy, Fantasy, Romance, Sci-fi, Thiller");
	genre = sc.next();
	sql =String.format("insert into genre_of values(%d,'%s')", movieid,genre);
	
	int res=0;

	res= stmt.executeUpdate(sql);
	
	if(res != 0)
		 System.out.println("genre completed");
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
	
}

