package km;

import java.sql.*; // import JDBC package
import java.util.*;
import java.util.Date;
import java.text.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RatingPage {
	// ๊ณ์ ์? ์ ์  Rating
	static Scanner sc = new Scanner(System.in);
	public static void totalRating(Connection conn, String UserID) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false); // auto-commit disabled
			// Create a statement object
			stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select MOVIE.Title, RATING.STARS FROM MOVIE, RATING WHERE RATING.ACCOUNT_ID = '" + UserID
					+ "' and rating.movie_id = movie.id";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			String ans1;
			String ans2;
			System.out.println("Title | Stars");
			while (rs.next()) {
				ans1 = rs.getString(1);
				ans2 = rs.getString(2);
				System.out.println(ans1 + "|" + ans2);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n๋๊ฐ๋ ค๋ฉด q๋ฅผ ์๋ ฅํด ์ฃผ์ธ์.");
		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0) continue;
			if (exit.charAt(0) == 'q') break;
		}
	}

	// Admin
	public static void AdminRating(Connection conn) {
		Statement stmt = null;
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
			while (rs.next()) {
				ans1 = rs.getString(1);
				ans2 = rs.getString(2);
				ans3 = rs.getString(3);
				System.out.println(i + "|" + ans1 + "|" + ans2 + "|" + ans3);
				i++;

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("๋๊ฐ๋ ค๋ฉด q๋ฅผ ์๋ ฅํด ์ฃผ์ธ์.");
		while (true) {
			String exit = sc.nextLine();
			if (exit.length() == 0) continue;
			if (exit.charAt(0) == 'q') break;
		}
	}

	public static void showMovieDetail(Connection conn, int id) {
		/* detail */
		ResultSet rs = null;
		try {
			// Create a statement object
			Statement stmt = conn.createStatement();
			// Q1: Complete your query.
			String sql = "select Title, mType, runtime, Start_year, Num_of_votes, Director, "
					+ "Writer, Company, Descriptions" + " from movie" + " where movie.id = " + id;
			rs = stmt.executeQuery(sql);

			String ans1;
			String ans2;
			String ans3;
			String ans4;
			String ans6;
			String ans7;
			String ans8;
			String ans9;
			String ans10;
			Date date = null;

			while (rs.next()) {
				ans1 = rs.getString(1);
				ans2 = rs.getString(2);
				ans3 = rs.getString(3);
				ans4 = rs.getString(4);

				SimpleDateFormat sDate = new SimpleDateFormat("yyyy-mm-dd");
				try {
					date = sDate.parse(ans4);
					//System.out.println(ans4);
					//System.out.println("date: " + date);
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

				System.out.print("Title : " + ans1);
				System.out.println(" | Movie type : " + ans2);
				System.out.print("Runtime : " + ans3 + " minutes");
				System.out.println(" | Start Year : " + Ddate.format(date)); // ์ฑ๊ณต
				System.out.println("the number of vote : " + ans6);
				System.out.print("Director : " + ans7);
				System.out.println(" | Writer : " + ans8);
				System.out.println("company : " + ans9);
				System.out.println("Discription");
				System.out.println(ans10);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean evaluateMovie(Connection conn, int id, String UserID) {
		// ํ?๊ฐ
		Statement stmt = null;
		String sql = null;
		char rating;
		String grade;
		ResultSet rs = null;
		int res;

		try {
			while (true) {
				stmt = conn.createStatement();
				System.out.print("ํ?๊ฐ ํ์๊ฒ ์ต๋๊น? (y/n)");
				rating = sc.next().charAt(0);
				sc.nextLine();
				if (rating == 'n' || rating == 'N')
					return false;
				// ํ?๊ฐ ํ๋ค
				else if (rating == 'y' || rating == 'Y') {
					System.out.print("๋ช์ ??");
					grade = sc.next();
					sc.nextLine();
					// rating ํ์?ด๋ธ์?์ ํน์ ์กฐ๊ฑด์? ๊ฐ์?ด ์กด์ฌ ํ๋๊ฐ?
					sql = "select * from rating where Account_id = '" + UserID + "' and movie_id = " + id;
					rs = stmt.executeQuery(sql);
					res = 0;

					// ์กด์ฌ ํ๋ค๋ฉด ๊ทธ ๊ฐ์? ์๋?ฐ์?ดํธ
					if (rs.next() == true) {
						// exist
						sql = "update rating set stars = " + grade + " where Account_id = '" + UserID
								+ "' and MOVIE_ID = " + id;
						// System.out.println(sql);
						res = stmt.executeUpdate(sql);
						// System.out.println(res);
						if (res != 0)
							System.out.println("๊ฐฑ์ ๋?์์ต๋๋ค.");
					}
					// ์์ผ๋ฉด ์๊ฐ ํ ๋น
					else {
						// not exist
						sql = "select max(Rating_id) from rating";
						rs = stmt.executeQuery(sql);
						rs.next();
						int maxnum = rs.getInt(1);
						maxnum = maxnum + 1;
						sql = "insert into rating values( " + maxnum + ", '" + UserID + "', " + grade + ", " + id + ")";
						//System.out.println(sql);
						res = stmt.executeUpdate(sql);
						if (res != 0)
							System.out.println("์ถ๊ฐ ๋?์์ต๋๋ค.");
					}
					break;
				} else {
					System.out.println("๋ค์ ์ ํ?ํด ์ฃผ์ธ์");
				}
			}

			// ์ค์ ๋ฐฉ์ง๋ฅผ ์ํ ๋กค๋ฐฑ
			
			conn.commit();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static void rerateMovie(Connection conn, int id, String UserID) // title -> movie_id(int)
	{
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn.setAutoCommit(false); // auto-commit disabled
			// Create a statement object
			stmt = conn.createStatement();
			String ans5 = null; // rating


			String sql = "select avg(rating.stars) from movie, rating where movie.id = " + id
					+ " and movie.id = rating.MOVIE_ID";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ans5 = rs.getString(1);
				// System.out.println("Rating : "+ans5);
			}

			/* re-calculateRating */
			// ์ ?ํ๊ฑฐ๋ ๋ค๋ฅด๋ฉด
			int res = 0;
			// Ratingํ?๊ท  ๋น๊ต?ํ๊ธฐ
			double a4, a5;
			String ans4_2 = null;
			sql = "select rating from movie where ID = " + id;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ans4_2 = rs.getString(1);
			}
			a4 = Double.parseDouble(ans4_2);
			a5 = Double.parseDouble(ans5);
			// System.out.println("a4(movie์? ์ ?ํ๊ฑฐ) : "+a4);
			// System.out.println("a5(ํ?๊ท ) : "+a5);

			if (a4 != a5) {
				sql = "UPDATE MOVIE  SET RATING = " + ans5 + " where ID = " + id;
				// System.out.println(sql);
				res = stmt.executeUpdate(sql);
				//System.out.println("Rating : " + ans5);

			} else {
				//System.out.println("Rating : " + ans5);
			}
			// ์ค์ ๋ฐฉ์ง๋ฅผ ์ํ ๋กค๋ฐฑ
			conn.commit();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
