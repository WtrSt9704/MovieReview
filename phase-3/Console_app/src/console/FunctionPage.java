package console;

import java.util.Scanner;

import java.sql.*;
import java.util.Scanner;

public class FunctionPage {
	public static Scanner sc = new Scanner(System.in);
	/* 
	 * return: false if logout, true if terminated
	 */
	public static boolean doFunctions(Connection conn) {
		while (true) {
			System.out.println("원하는 기능을 선택하세요.");
			System.out.println("1: 영상물 전체 조회");
			System.out.println("2: 제목으로 영상물 검색");
			System.out.println("3: 조건으로 영상물 검색");
			System.out.println("4: 평가 내역 확인");
			System.out.println("5: 마이페이지");
			
			int func = sc.nextInt();
			if (func == 4) break;
			
			if (func == 1) {
				retrieveAll(conn);
			} else if (func == 2) {
				sc.nextLine(); // flush the buffer
				clearScr();
				System.out.println("검색할 영상물 제목을 입력해주세요: ");
				String stitle = sc.nextLine();		
				retrieveTitle(conn, stitle);
			} else if (func == 3) {
				sc.nextLine(); // flush the buffer
				retrieveFilter(conn);
			} else if (func == 4) {
				sc.nextLine(); // flush the buffer
			}
		}
	}
	
	
	
}
