import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 시간
		int time = input.nextInt();

		// 분
		int minute = input.nextInt();

		// 초
		int second = input.nextInt();

		// 필요한 시간
		int D = input.nextInt();

		// 얼마나 걸리는지 계산
		StringBuilder sb = new StringBuilder();

		// 초부터 갱신, 60초 이상은 넘어가야 되니까 나머지값만큼만
		int newSecond = (second + D) % 60;

		// 분 단위로 얼마나 바뀌는지 확인
		int overSecond = (second + D) / 60;

		// 분단위 갱신, 60분 이상은 1시간이니까 나머지값 만큼만
		int newMinute = (minute + overSecond) % 60;

		// 넘어가는 시간 계산
		int overMinute = (minute + overSecond) / 60;

		// 시간 단위 갱신, 24시 == 0시이므로 24로 나눈 나머지값만큼만
		int newTime = (time + overMinute) % 24;

		// 정답 입력
		sb.append(newTime).append(" ")
			.append(newMinute).append(" ")
			.append(newSecond);

		System.out.println(sb);
	}
}