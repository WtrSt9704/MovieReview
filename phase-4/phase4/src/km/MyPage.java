package km;

import java.sql.*;
import java.util.Scanner;

public class MyPage {
	public static Scanner sc = new Scanner(System.in);

	/* return false if logout, return true else */
	public static void updateInfo(Connection conn, Statement stmt, String id, String password) {
		String information;
		information = sc.next();
		String sql = "UPDATE ACCOUNT SET"
		String sql = String.format("UPDATE ACCOUNT SET %s = ? where ACCOUNT.id ='%s' and ACCOUNT.password = '%s'",information,id,password);
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
