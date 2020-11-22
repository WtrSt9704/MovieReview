package console;

import java.sql.*;
import java.util.Scanner;

public class LandingPage {
	public static Scanner sc = new Scanner(System.in);

	/* return true if success login, return false if terminated */
	public static boolean display(Connection conn, Statement stmt, User user) {
		while (true) {
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 가입");
			System.out.println("2: 로그인");
			System.out.println("3: 종료");
			int func = sc.nextInt();
			sc.nextLine(); // flush the buffer
			if (func == 3)
				break;

			if (func == 1) {
				signup(conn, stmt);
			} else if (func == 2) {
				if (login(conn, stmt, user)) {
					return true;
				}
			}

			Util.clearScr();
		}
		return false;
	}

	public static void signup(Connection conn, Statement stmt) {
		try {
			conn.setAutoCommit(false);

			// String sql = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,?,?,?)" ;
			// PreparedStatement ps = conn.prepareStatement(sql);
			Util.clearScr();

			stmt = conn.createStatement();

			String id;
			String password;
			String phonenumber;
			String name;
			int membership_grade;// 멤버쉽은 그냥
			String gender;
			int age;
			String birthday;
			String job;
			String address;
			String newdate;
			String tmp2;

			int tmp = 0;
			int res = 0;

			System.out.print("ID(필수): ");// 필수 입력값
			id = sc.next();

			System.out.print("Password(필수) : ");// 필수 입력값
			password = sc.next();

			System.out.print("Phonenumber(필수)(xxx-xxxx-xxxx): ");// 필수 입력값
			phonenumber = sc.next();

			sc.nextLine();
			System.out.print("Name : ");
			name = sc.nextLine();

			/*
			 * if(name.equals("")) { name = "NULL"; }
			 */

			System.out.print("What is your membership grade?(필수) :0.basic 1. primium 2.prime ");// 필수입력값
			membership_grade = sc.nextInt();

			// sc.nextLine();
			System.out.print("Gender(M or F) :");
			gender = sc.nextLine();
			/*
			 * if(gender.equals("")) { gender="";
			 * 
			 * }
			 */

			sc.nextLine();
			System.out.print("Birthday(yyyy-mm-dd) : ");
			birthday = sc.nextLine();
			if (birthday.equals("")) {
				age = 0;
				newdate = "NULL";

			} else {
				tmp2 = birthday.substring(0, 4);
				age = 2020 - Integer.valueOf(tmp2);

				newdate = "TO_DATE('" + birthday + "','yyyy-mm-dd')";

			}

			System.out.print("Job : ");
			job = sc.nextLine();
			/*
			 * if(job.equals("")) { job="NULL";
			 * 
			 * }
			 */

			System.out.print("Address : ");
			address = sc.nextLine();
			/*
			 * if(job.equals("")) { address="NULL";
			 * 
			 * }
			 */

			String sql;

			if (age == 0) {
				sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d , '%s', NULL, %s, '%s', '%s')",
						id, password, phonenumber, name, membership_grade, gender, newdate, job, address);
			} else {
				sql = String.format("INSERT INTO ACCOUNT VALUES('%s','%s','%s','%s', %d , '%s', %d, %s, '%s', '%s')",
						id, password, phonenumber, name, membership_grade, gender, age, newdate, job, address);
			}
			res = stmt.executeUpdate(sql);

			if (res != 0)
				System.out.println("Siun up completed.");
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

	public static boolean login(Connection conn, Statement stmt, User user) {
		String id;
		String password;
		String sql = "";
		ResultSet rs;
		Util.clearScr();
		System.out.print("ID : ");
		id = sc.next();
		user.setId(id);
		sc.nextLine();
		System.out.print("Password : ");
		password = sc.next();
		user.setPW(password);
		sc.nextLine();

		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			// Q3: Complete your query.
			sql = "SELECT ID,PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID ='" + id + "' AND ACCOUNT.PASSWORD ='" + password
					+ "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rs.close();
				return true;
			} else {
				System.out.println("login failed");
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("나가려면 q를 입력해 주세요.");

		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0)
				continue;
			if (exit.charAt(0) == 'q')
				break;
		}

		return false;
	}
}
