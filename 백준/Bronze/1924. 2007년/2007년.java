import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int startDate = 1; // 1월 1일은 월요일 (일요일이 달력기준 맨 왼쪽이므로 0으로 함)
		int x = input.nextInt(); // 월
		int y = input.nextInt(); // 일
		/*
		 * startDate = 1월 1일 월요일 = 1 (일요일이 달력 기준 맨 왼쪽이므로 0)
		 * 한달이 31 -> 31 % 7 = 3 다음달 시작요일 : +3
		 * 한달이 30 -> 30 % 7 = 2 다음달 시작요일 : +2
		 * 한달이 28 -> 28 % 7 = 0 다음달 시작요일 : 0
		 * 만약 7월 27일이 무슨 요일인지 알고싶다면
		 * 6월까지 +3 +0 +3 +2 +3 +2 = +13
		 * 13 % 7 = 6 -> 시작요일 +6  -> 7 % 7 = 0 (일요일)
		 * 27일까지 이미 포함된 7월 1일을 제외하고 +26
		 * 26 % 7 = 5 -> 금요일.
		 */
		int[] dateUpdate = {0, 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2}; // 1일이 무슨 요일인지 체크하기 위한 표시
		for (int i = 1; i < x; i++) { // 1월은 더할 필요 없음, 해당월 전까지 
			startDate += dateUpdate[i];
			startDate %= 7; // 7로 나누어서 시작요일 계산 (0: 일요일 ~ 6: 토요일)
		}
		startDate += y-1; // 해당 일수까지 이미 포함된 1일을 제외
		startDate %= 7; // 7로 나누어서 최종 시작요일 계산
		switch (startDate) { // switch 문을 활용해 출력
		case 0:
			System.out.println("SUN");
			break;
		case 1:
			System.out.println("MON");
			break;
		case 2:
			System.out.println("TUE");
			break;
		case 3:
			System.out.println("WED");
			break;
		case 4:
			System.out.println("THU");
			break;
		case 5:
			System.out.println("FRI");
			break;
		case 6:
			System.out.println("SAT");
			break;
		}
		
	}
}
