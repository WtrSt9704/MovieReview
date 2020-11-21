package console;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class SearchingPage {
	public static Scanner sc = new Scanner(System.in);
	
	public static void makeMap(Map<String, String> mp, String[] collection) {
		for (int i = 0; i < collection.length; ++i) {
			mp.put(Integer.toString(i+1), collection[i]);
		}
	}
	
	public static void retrieveAll(Connection conn, User user) {
		clearScr();
		System.out.println("retrieveAll");
		
		String sql = "select title, rating\r\n"
				+ "from movie m" + " where ";
		
		printMovie(conn, sql, user);
		
		while ((sc.next().charAt(0)) != 'q') {
			sc.nextLine();
		}
	}
	
	public static void retrieveTitle(Connection conn, String stitle, User user) {
		clearScr();
		System.out.println("retrieveTitle");
		
		String sql = "select title, rating\r\n"
				+ "from movie m\r\n"
				+ "where title like " + "'%" + stitle + "%'" + " and ";
		
		printMovie(conn, sql, user);
		while ((sc.next().charAt(0)) != 'q') {
			sc.nextLine();
		}
	}
	
	public static void retrieveFilter(Connection conn, User user) {
		String choice;
		String[] choices;
		
		Map<String, String> mType = new HashMap<String, String>();
		Map<String, String> start_year = new HashMap<String, String>();
		Map<String, String> genre = new HashMap<String, String>();
		Map<String, String> version = new HashMap<String, String>();
		//Map<String, String> director = new HashMap<String, String>();
		//Map<String, String> company = new HashMap<String, String>();
		
		String[] mType_collection = {"movie", "tvSeries", "knuOriginal"};
		//String[] start_year_collection = {"movie", "tvSeries", "knuOriginal"};
		String[] genre_collection = {"Action", "Comedy", "Romance", "Thiller", "Adventure", "Sci-Fi", "Fantasy"};
		String[] version_collection = {"UA", "DE", "KR", "ES", "FR"};
		
		makeMap(mType, mType_collection);
		//makeMap(start_year, start_year_collection);
		makeMap(genre, genre_collection);
		makeMap(version, version_collection);
		
		
		
		clearScr();
		System.out.println("retrieveFilter");
		String sql = "select v.title, m.rating, v.region\r\n"
				+ "from movie m, version v, genre_of go\r\n"
				+ "where\r\n"
				+ "    v.movie_id = m.id and go.movie_id = m.id" + " and ";
		
		System.out.println("각 필터에 적용하길 원하는 내용의 숫자를 선택해주세요.");
		System.out.println("< 영상물 종류 >");
		for (int i = 0; i < mType_collection.length; ++i) {
			System.out.println("  " + (i+1) + ". " + mType_collection[i]);
		}
		
		choice = sc.nextLine();
		choice = choice.replaceAll("\\D", "");
		choices = choice.split("");
		
		sql += " and (";
		for (int i = 0; i < choices.length; ++i) {
			sql += "mType=" + "'" + mType.get(choices[i]) + "'";
			if (i+1 != choices.length) {
				sql += " or ";
			}
		}
		sql += ") ";
		
		System.out.println("< 장르 >");
		for (int i = 0; i < genre_collection.length; ++i) {
			System.out.println("  " + (i+1) + ". " + genre_collection[i]);
		}
		choice = sc.nextLine();
		choice = choice.replaceAll("\\D", "");
		choices = choice.split("");
		
		sql += " and (";
		for (int i = 0; i < choices.length; ++i) {
			sql += "genre_name=" + "'" + genre.get(choices[i]) + "'";
			if (i+1 != choices.length) {
				sql += " or ";
			}
		}
		sql += ") ";
		
		System.out.println("< 버전 >");
		for (int i = 0; i < version_collection.length; ++i) {
			System.out.println("  " + (i+1) + ". " + version_collection[i]);
		}
		choice = sc.nextLine();
		choice = choice.replaceAll("\\D", "");
		choices = choice.split("");
		
		sql += " and (";
		for (int i = 0; i < choices.length; ++i) {
			sql += "region=" + "'" + version.get(choices[i]) + "'";
			if (i+1 != choices.length) {
				sql += " or ";
			}
		}
		sql += ") ";
		clearScr();
		System.out.println(sql);
		
		printMovie(conn, sql, user);
		
		while ((sc.next().charAt(0)) != 'q') {
			sc.nextLine();
		}
	}
	
	public static void clearScr() {
	    for (int i = 0; i < 100; i++)
	      System.out.println("");
	}
	
	public static void printMovie(Connection conn, String sql, User user) {
		/* except movies rated by the user */
		sql += " not exists (\r\n"
				+ "  select r.rating_id\r\n"
				+ "  from rating r\r\n"
				+ "  where r.movie_id = m.id and r.account_id = " + "'" + user.getId() + "')";
		
		ResultSet rs = null;
	    PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException ex2) {
			System.err.println("stmt error = " + ex2.getMessage());
			System.exit(1);
		}
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString(1);
				Double rating = rs.getDouble(2);
				System.out.println("Title: " + title + ",\trating: " + rating);
	        }
	        if(rs != null) rs.close();
	        if(pstmt != null) pstmt.close();
			pstmt.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
