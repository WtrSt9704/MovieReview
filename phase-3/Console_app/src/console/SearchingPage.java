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
		Util.clearScr();
		System.out.println("retrieveAll");
		
		String sql = "select m.id, title, rating\r\n"
				+ "from movie m" + " where ";
		
		if (Util.printMovie(conn, sql, user) != 0) {
			Util.afterList(conn, user);
		}
	}
	
	public static void retrieveTitle(Connection conn, String stitle, User user) {
		Util.clearScr();
		System.out.println("retrieveTitle");
		String sql = "select m.id, title, rating\r\n"
				+ "from movie m\r\n"
				+ "where title like " + "'%" + stitle + "%'" + " and ";
		
		if (Util.printMovie(conn, sql, user) != 0) {
			Util.afterList(conn, user);
		}
	}
	
	public static void retrieveFilter(Connection conn, User user) {
		//sc.nextLine();
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
		
		
		
		Util.clearScr();
		System.out.println("retrieveFilter");
		String sql = "select m.id, v.title, m.rating, v.region\r\n"
				+ "from movie m, version v, genre_of go\r\n"
				+ "where\r\n"
				+ "    v.movie_id = m.id and go.movie_id = m.id ";
		
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
		sql += ") and ";
		Util.clearScr();
		//System.out.println(sql);
		
		if (Util.printMovie(conn, sql, user) != 0) {
			Util.afterList(conn, user);
		} else {
			System.out.println("조회할 수 있는 영화가 없습니다. 아무키나 입력해주세요.");
			sc.nextLine();
		}
	}
}
