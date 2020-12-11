package km;

import java.util.Scanner;
import java.sql.*;

public class FunctionPage {
	public static Scanner sc = new Scanner(System.in);

	public static boolean isAdmin(Connection conn, User user) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();

			String sql = "select membership_grade from account where id = '" + user.getId() + "'";
			rs = stmt.executeQuery(sql);
			int grade = 0;
			while (rs.next()) {
				grade = rs.getInt(1);
			}

			if (grade != 3) {
				return false;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * return: false if logout, true if terminated
	 */
	public static boolean doFunctions(Connection conn, Statement stmt, User user) {
		if (isAdmin(conn, user)) {
			while (true) {
				Util.clearScr();
				System.out.println("์?ํ๋ ๊ธฐ๋ฅ์? ์ ํ?ํ์ธ์.");
				System.out.println("1: ์?์?๋ฌผ ์ ์ฒด ์กฐํ");
				System.out.println("2: ์ ๋ชฉ์ผ๋ก ์?์?๋ฌผ ๊ฒ์");
				System.out.println("3: ์กฐ๊ฑด์ผ๋ก ์?์?๋ฌผ ๊ฒ์");
				System.out.println("4: ํ์? ์ ์ฒด ํ?๊ฐ ๋ด์ญ ํ์?ธ");
				System.out.println("5: ๋ง์?ดํ์?ด์ง");
				System.out.println("6: ์๋ก์ด ์?์?๋ฌผ ๋ฑ๋ก?");
				System.out.println("7: ๋ก๊ทธ์์");
				System.out.println("8: ์ข๋ฃ");

				int func = sc.nextInt();
				/* logout */
				if (func == 7)
					return false;
				/* terminate */
				if (func == 8)
					break;

				sc.nextLine(); // flush the buffer

				if (func == 1) {
					SearchingPage.retrieveAll(conn, user, true);
				} else if (func == 2) {
					Util.clearScr();
					System.out.println("๊ฒ์ํ  ์?์?๋ฌผ ์ ๋ชฉ์? ์๋ ฅํด์ฃผ์ธ์: ");
					String stitle = sc.nextLine();
					SearchingPage.retrieveTitle(conn, stitle, user, true);
				} else if (func == 3) {
					SearchingPage.retrieveFilter(conn, user, true);
				} else if (func == 4) {
					Util.clearScr();
					RatingPage.AdminRating(conn);
				} else if (func == 5) {
					Util.clearScr();
					if (!MyPage.display(conn, stmt, user)) {
						return false;
					}
				} else if (func == 6) {
					AdminFunc.movieEnroll(conn, stmt, user.getId());
				} 
			}
		} else {
			while (true) {
				Util.clearScr();
				System.out.println("์?ํ๋ ๊ธฐ๋ฅ์? ์ ํ?ํ์ธ์.");
				System.out.println("1: ์?์?๋ฌผ ์ ์ฒด ์กฐํ");
				System.out.println("2: ์ ๋ชฉ์ผ๋ก ์?์?๋ฌผ ๊ฒ์");
				System.out.println("3: ์กฐ๊ฑด์ผ๋ก ์?์?๋ฌผ ๊ฒ์");
				System.out.println("4: ํ?๊ฐ ๋ด์ญ ํ์?ธ");
				System.out.println("5: ๋ง์?ดํ์?ด์ง");
				System.out.println("6: ๋ก๊ทธ์์");
				System.out.println("7: ์ข๋ฃ");

				int func = sc.nextInt();
				if (func == 6)
					return false;
				if (func == 7)
					break;

				sc.nextLine(); // flush the buffer

				if (func == 1) {
					SearchingPage.retrieveAll(conn, user, false);
				} else if (func == 2) {
					Util.clearScr();
					System.out.println("๊ฒ์ํ  ์?์?๋ฌผ ์ ๋ชฉ์? ์๋ ฅํด์ฃผ์ธ์: ");
					String stitle = sc.nextLine();
					SearchingPage.retrieveTitle(conn, stitle, user, false);
				} else if (func == 3) {
					SearchingPage.retrieveFilter(conn, user, false);
				} else if (func == 4) {
					Util.clearScr();
					RatingPage.totalRating(conn, user.getId());
				} else if (func == 5) {
					Util.clearScr();
					if (!MyPage.display(conn, stmt, user)) {
						return false;
					}
				}
			}
		}

		return true;
	}
}
