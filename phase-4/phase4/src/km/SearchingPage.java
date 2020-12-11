package km;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.text.*;

public class SearchingPage {
	public static String retrieve(Connection conn, User user, String stitle, String[] mtypes, String[] genres, String[] versions, boolean isAdmin) {
		String sql = "select m.id, v.title, m.rating, v.region\r\n" + "from movie m, version v, genre_of go\r\n"
				+ "where\r\n" + "    v.movie_id = m.id and go.movie_id = m.id ";
		if (stitle != null) {
			sql += " and ( (m.title like " + "'%" + stitle + "%')" + " or " + "(v.title like " + "'%" + stitle + "%') )";
		}
		
		/* mType */
		if (mtypes != null) {
			sql += " and (";
			for (int i = 0; i < mtypes.length; ++i) {
				sql += "mType=" + "'" + mtypes[i] + "'";
				if (i + 1 != mtypes.length) {
					sql += " or ";
				}
			}
			sql += ") ";
		}
		
		
		/* genre */
		if (genres != null) {
			sql += " and (";
			for (int i = 0; i < genres.length; ++i) {
				sql += "genre_name=" + "'" + genres[i] + "'";
				if (i + 1 != genres.length) {
					sql += " or ";
				}
			}
			sql += ") ";
		}
		
		/* version */
		if (versions != null) {
			sql += " and (";
			for (int i = 0; i < versions.length; ++i) {
				sql += "region=" + "'" + versions[i] + "'";
				if (i + 1 != versions.length) {
					sql += " or ";
				}
			}
			sql += ")";
		}
		
		
		/* except movies rated by user*/
		sql += "and not exists (\r\n" + "  select r.rating_id\r\n" + "  from rating r\r\n"
				+ "  where r.movie_id = m.id and r.account_id = " + "'" + user.getId() + "')";
		return sql;
//		if (Util.printMovie(conn, sql, user) != 0) {
//			Util.afterList(conn, user, isAdmin);
//		} else {
//			System.out.println("조회할 수 있는 �?화가 없습니다. 아무키나 입력해주세요.");
//			sc.nextLine();
//		}
	}
}
