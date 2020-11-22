package console;

import java.sql.*;
import java.util.Scanner;

public class MyPage {
	public static Scanner sc = new Scanner(System.in);

	/* return false if logout, return true else */
	public static boolean display(Connection conn, Statement stmt, User user) {
		while (true) {
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 정보수정");
			System.out.println("2: Pasword 수정");
			System.out.println("3: 계정 삭제");
			System.out.println("4: 로그아웃");
			System.out.println("5: 되돌아가기");
			int func = sc.nextInt();

			if (func == 4)
				return false;
			if (func == 5)
				break;

			if (func == 1) {
				update(conn, stmt, user.getId(), user.getPW());
			} else if (func == 2) {
				changepwd(conn, stmt, user.getId(), user.getPW());
			} else if (func == 3) {
				drop(conn, stmt, user.getId(), user.getPW());
				return false;
			}
			Util.clearScr();
		}
		return true;
	}

	public static void update(Connection conn, Statement stmt, String id, String password) {

		Util.clearScr();
		System.out.print("What do you change? : 1.JOB 2.Address 3.Exit");
		int func = sc.nextInt();

		if (func == 3)
			return;

		if (func == 1) {
			changejob(conn, stmt, id, password);
		} else if (func == 2) {
			changeaddress(conn, stmt, id, password);
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
			System.exit(1);
		}
	}

	public static void changejob(Connection conn, Statement stmt, String id, String password) {

		System.out.print("input your new job : ");
		String newjob = sc.next();
		sc.nextLine();

		String sql = String.format("UPDATE ACCOUNT SET JOB = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'",
				newjob, id, password);
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Having changed job");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	public static void changeaddress(Connection conn, Statement stmt, String id, String password) {
		String cdaddress;
		System.out.print("input your new password : ");
		cdaddress = sc.next();
		sc.nextLine();

		String sql = String.format(
				"UPDATE ACCOUNT SET ADDRESS = '%s' where ACCOUNT.ID ='%s' and ACCOUNT.PASSWORD ='%s'", cdaddress, id,
				password);
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Having changed address");
			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
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
			System.exit(1);
		}
	}
}
