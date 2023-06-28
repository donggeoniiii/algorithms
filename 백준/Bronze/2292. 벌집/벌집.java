// 벌집

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		// 찾으려는 값
		int num = input.nextInt();
		
		// 찾으려는 값의 위치르 찾기 위해 현재까지 거친 방수
		int roomCnt = 1;
		
		// 방에 도달할 때까지
		int curNum = 2;
		
		//1이면 해볼 필요 없으므로
		if (num != 1) {
			// 아닌경우는 찾을 때까지
			while (curNum <= num) {
				
				// 일단 아직 도달하지 못했으면 카운트 증가
				curNum += 6 * roomCnt;
				
				// 거친 방수 +1
				roomCnt++;
			}
			
			// 정답 출력
			System.out.println(roomCnt);
		} else {
			System.out.println(1);
		}
		
	}
}