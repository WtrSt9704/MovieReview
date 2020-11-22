package console;

import java.util.Scanner;
import java.sql.*;

public class FunctionPage {
	public static Scanner sc = new Scanner(System.in);

	/*
	 * return: false if logout, true if terminated
	 */
	public static boolean doFunctions(Connection conn, Statement stmt, User user) {
		while (true) {
			Util.clearScr();
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 영상물 전체 조회");
			System.out.println("2: 제목으로 영상물 검색");
			System.out.println("3: 조건으로 영상물 검색");
			System.out.println("4: 평가 내역 확인");
			System.out.println("5: 마이페이지");
			System.out.println("6: 로그아웃");
			System.out.println("7: 종료");

			int func = sc.nextInt();
			if (func == 6)
				return false;
			if (func == 7)
				break;

			sc.nextLine(); // flush the buffer

			if (func == 1) {
				SearchingPage.retrieveAll(conn, user);
			} else if (func == 2) {
				Util.clearScr();
				System.out.println("검색할 영상물 제목을 입력해주세요: ");
				String stitle = sc.nextLine();
				SearchingPage.retrieveTitle(conn, stitle, user);
			} else if (func == 3) {
				SearchingPage.retrieveFilter(conn, user);
			} else if (func == 4) {
				Util.clearScr();
				RatingPage.totalRating(conn, user.getId());
			} else if (func == 5) {
				Util.clearScr();
				MyPage.display(conn, stmt, user);
			}
		}
		return true;
	}
}
