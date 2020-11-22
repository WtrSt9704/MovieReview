package console;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Util {
	public static Scanner sc = new Scanner(System.in);

	public static void clearScr() {
		for (int i = 0; i < 100; i++)
			System.out.println("");
	}

	public static void afterList(Connection conn, User user) {
		System.out.print("영화 상세보기를 하시겠습니까? (y/n)");
		char ans = sc.nextLine().charAt(0);
		if (ans == 'y' || ans == 'Y') {

			System.out.print("상세보기를 원하는 영화의 ID를 입력해주세요.");
			try {
				int movie_id = sc.nextInt();
				sc.nextLine();
				Util.clearScr();
				RatingPage.showMovieDetail(conn, movie_id);
				if (RatingPage.evaluateMovie(conn, movie_id, user.getId())) {
					RatingPage.rerateMovie(conn, movie_id, user.getId());
				}
			} catch (Exception e) {
				System.out.println("유효하지 않는 영화 Id입니다. 다시 시도해주세요.");
			}

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
	
	/* return the number of movies */
	public static int printMovie(Connection conn, String sql, User user) {
		/* except movies rated by the user */
		int cnt = 0;
		sql += " not exists (\r\n" + "  select r.rating_id\r\n" + "  from rating r\r\n"
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
			while (rs.next()) {
				int movie_id = rs.getInt(1);
				String title = rs.getString(2);
				Double rating = rs.getDouble(3);
				System.out.println("Id: " + movie_id + ",\tTitle: " + title + ",\trating: " + rating);
				cnt++;
			}
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
}
