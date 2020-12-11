package km;

import java.sql.*;
import java.util.Scanner;

public class MyPage {
	public static Scanner sc = new Scanner(System.in);

	/* return false if logout, return true else */
	public static boolean display(Connection conn, Statement stmt, User user) {
		while (true) {
			System.out.println("�?하는 기능�?� 선�?하세요.");
			System.out.println("1: 정보수정");
			System.out.println("2: Pasword 수정");
			System.out.println("3: 계정 삭제");
			System.out.println("4: 로그아웃");
			System.out.println("5: �?��?�아가기");
			int func = sc.nextInt();

			if (func == 4)
				return false;
			if (func == 5)
				break;

			if (func == 1) {
				Util.clearScr();
				update(conn, stmt, user.getId(), user.getPW());
			} else if (func == 2) {
				Util.clearScr();
				changepwd(conn, stmt, user.getId(), user.getPW());
			} else if (func == 3) {
				Util.clearScr();
				drop(conn, stmt, user.getId(), user.getPW());
				return false;
			}
			Util.clearScr();
		}
		return true;
	}

	public static void update(Connection conn, Statement stmt, String id, String password) {
		
		Util.clearScr();
		String information;
		
		System.out.print("What do you change? (phone_number,name,membership,job,address,birthday)");
		
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
				System.out.println("Update completed");
				
				conn.commit();
			}catch(SQLException ex2) {
				System.err.println("sql error = "+ex2.getMessage());
//				sc.nextLine();
				return;
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
		
				System.out.println("Update completed");
				
				conn.commit();
			}catch(SQLException ex2) {
				System.err.println("sql error = "+ex2.getMessage());
//				sc.nextLine();
				return;
			}		
		}
		System.out.println("나가려면 q를 입력해 주세요.");

		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0)
				continue;
			if (exit.charAt(0) == 'q')
				break;
		}
	}

	public static void changepwd(Connection conn, Statement stmt, String id, String password) {
		String cdpwd;
		System.out.print("input your new password : ");
		cdpwd = sc.next();
		sc.nextLine();

		String sql = String.format(
				"UPDATE ACCOUNT SET PASSWORD = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", cdpwd, id,
				password);
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Having changed password");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
//			System.exit(1);
		}
		System.out.println("나가려면 q를 입력해 주세요.");

		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0)
				continue;
			if (exit.charAt(0) == 'q')
				break;
		}

	}

	public static void drop(Connection conn, Statement stmt, String id, String password) {

		Util.clearScr();
		String sql = String.format("DELETE FROM ACCOUNT where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", id,
				password);
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Having deleted ID");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
//			System.exit(1);
		}
		System.out.println("나가려면 q를 입력해 주세요.");

		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0)
				continue;
			if (exit.charAt(0) == 'q')
				break;
		}

	}
}
