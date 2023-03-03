// 평균

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 시험 성적중 최고값
		double high = 0;
		
		// 시험 개수
		int N = input.nextInt();
		
		// 시험 성적 저장하는 배열
		double[] test = new double[N];
		
		// 시험 성적 입력
		for (int i = 0; i < N; i++) {
			test[i] = input.nextInt();
			
			// 최댓값과 비교하며 갱신
			if (test[i] > high) high = test[i];
			
		}
		
		// 새로운 평균 계산을 위한 합계 변수
		double sum = 0;
		
		// 시험 성적 변환 후 합산
		for (double point : test) {
			point = (point / high) * 100; 
			sum += point;
		}
		
		// 정답 출력
		System.out.println(sum / N);
	}
}