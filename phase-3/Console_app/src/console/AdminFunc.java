package console;

import java.util.Scanner;
import java.sql.*;

public class AdminFunc {
	public static Scanner sc = new Scanner(System.in);

	public static void movieUpdate(Connection conn, int movieid) {

		String information;
		System.out.print(
				"What do you change information?(title, mtype,runtime,startyear,endyear,director,writer,company,descriptions)");
		information = sc.next();
		String sql = String.format("UPDATE MOVIE SET %s = ? where MOVIE.ID = %d", information, movieid);
		int res = 0;
		if (information.equals("runtime")) {
			int runtime;
			System.out.print("Input to change : ");
			runtime = sc.nextInt();

			try {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setInt(1, runtime);

				res = ps.executeUpdate();
				System.out.print("Update completed");

				conn.commit();
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
		} else if (information.equals("startyear") || information.equals("endyear")) {
			String date;
			System.out.print("Input to change : ");
			date = sc.next();

			String newdate = "TO_DATE('" + date + "','yyyy-mm-dd')";

			try {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, newdate);

				res = ps.executeUpdate();
				System.out.print("Update completed");

				conn.commit();
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
		} else {
			String data;
			System.out.print("Input to change : ");
			data = sc.next();

			String newdata = data;

			try {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, newdata);

				res = ps.executeUpdate();

				System.out.println("Update completed");

				conn.commit();
			} catch (SQLException ex2) {
				System.err.println("sql error = " + ex2.getMessage());
				System.exit(1);
			}
		}
	}

	public static void movieEnroll(Connection conn, Statement stmt, String id) {
		String sql = "";
		ResultSet rs;
		Util.clearScr();
		int count = 0;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			sql = "SELECT COUNT(*) FROM MOVIE";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			} else {
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
			sql = "";
			Util.clearScr();

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

			int res = 0;

			movieid = count + 1;
			System.out.print("Title : ");
			title = sc.next();
			sc.nextLine();
			System.out.print("mtype : ");
			mtype = sc.next();
			sc.nextLine();
			System.out.print("runtime : ");
			runtime = sc.nextInt();
			sc.nextLine();
			System.out.print("startyear : ");
			startyear = sc.next();
			sc.nextLine();
			System.out.print("endyear : ");
			endyear = sc.next();
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

			sql = String.format(
					"INSERT INTO MOVIE VALUES(%d,'%s','%s',%d,TO_DATE('%s','yyyy-mm-dd'),TO_DATE('%s','yyyy-mm-dd'),'%s',0,0,'%s','%s','%s','%s')",
					movieid, title, mtype, runtime, startyear, endyear, id, director, writer, company, descriptions);
			// System.out.println(sql);
			res = stmt.executeUpdate(sql);
			if (res != 0)
				System.out.println("movie enrollment completed.");

			conn.commit();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		System.out.println("나갈려면 q를 입력해 주세요.");
		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0)
				continue;
			if (exit.charAt(0) == 'q')
				break;
		}
	}
}
