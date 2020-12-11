package km;

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
//				System.exit(1);
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
//				System.exit(1);
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
//				System.exit(1);
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
			// conn.setAutoCommit(false);
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

			String newdate;

			int res = 0;

			movieid = count + 1;
			System.out.print("Title(필수) : ");// 필수
			title = sc.next();

			sc.nextLine();
			System.out.print("mtype(필수) : knuOriginal,movie,tvSeries 중 입력 ");
			mtype = sc.next();

			sc.nextLine();
			System.out.print("runtime(필수) : ");
			runtime = sc.nextInt();

			sc.nextLine();
			System.out.print("startyear(필수,yyyy-mm-dd) : ");
			startyear = sc.next();

			sc.nextLine();
			System.out.print("endyear(yyyy-mm-dd) : ");
			endyear = sc.nextLine();

			if (endyear.equals("")) {
				newdate = "NULL";

			} else {
				newdate = "TO_DATE('" + endyear + "','yyyy-mm-dd')";
			}

			System.out.print("director : ");
			director = sc.nextLine();

			System.out.print("writer : ");
			writer = sc.nextLine();

			System.out.print("company : ");
			company = sc.nextLine();

			System.out.print("descriptons : ");
			descriptions = sc.nextLine();

			sql = String.format(
					"INSERT INTO MOVIE VALUES(%d,'%s','%s',%d,TO_DATE('%s','yyyy-mm-dd'),%s,'%s',0,0,'%s','%s','%s','%s')",
					movieid, title, mtype, runtime, startyear, newdate, id, director, writer, company, descriptions);
			res = stmt.executeUpdate(sql);

			// conn.commit();

			genreof(conn, stmt, movieid);

			int check = 1;
			while (true) {
				System.out.print("버젼 추가하시겠습니까?(0입력시 종료)");
				check = sc.nextInt();
				if (check == 0) {
					break;
				} else {
					version(conn, stmt, movieid, title);
				}
			}
			check = 1;
			sc.nextLine();
			if (mtype.equals("tvSeries")) {
				while (true) {
					System.out.print("�?피소드 추가하시겠습니까?(0입력시 종료)");
					check = sc.nextInt();
					if (check == 0) {
						break;
					} else {
						episode(conn, stmt, movieid);
					}
				}
			}
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

	public static void episode(Connection conn, Statement stmt, int movieid) {
		String sql = "";
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
			epnum = sc.nextInt();

			sc.nextLine();
			System.out.print("season title : ");
			seasontitle = sc.nextLine();

			System.out.print("season num");
			seasonnum = sc.nextInt();

			int res = 0;
			sql = String.format("insert into episode values(%d,'%s',%d,'%s',%d)", movieid, eptitle, epnum, seasontitle,
					seasonnum);
			res = stmt.executeUpdate(sql);

			if (res != 0)
				System.out.println("episode upload");
			conn.commit();
		}

		catch (SQLException ex2) {
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

	public static void version(Connection conn, Statement stmt, int movieid, String title) {

		String sql = "";
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

			int res = 0;
			sql = String.format("insert into version values(%d,'%s','%s','%s',%d)", movieid, title, region, language,
					original);
			res = stmt.executeUpdate(sql);

			if (res != 0)
				System.out.println("version completed");
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

	public static void genreof(Connection conn, Statement stmt, int movieid) {
		String genre = "";
		String sql = "";
		ResultSet rs;
		int genrenum;

		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			System.out.println("input genre : Action, Adventure, Comedy, Fantasy, Romance, Sci-fi, Thiller");
			genre = sc.next();
			sql = String.format("insert into genre_of values(%d,'%s')", movieid, genre);

			int res = 0;

			res = stmt.executeUpdate(sql);

			if (res != 0)
				System.out.println("genre completed");
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
