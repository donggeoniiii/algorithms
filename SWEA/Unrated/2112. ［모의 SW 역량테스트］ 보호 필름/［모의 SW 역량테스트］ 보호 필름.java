import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	// 보호필름
	static int[][] film;
	
	// 약품 투입 횟수
	static int N;
	
	// 필름 두께, 가로 크기, 합격 기준
	static int D, W, K;
	
	// 0으로 약품처리, 1로 약품처리
	static int[] zero;
	static int[] one;
	
	// 최소 약품처리 횟수
	static int minCnt;
	
	
	// 검사
	public static void test(int row, int cnt) { // row: 검사하는 행, cnt: 약품처리횟수
		// pruning: 이미 cnt가 minCnt보다 크면 스킵
		if (cnt > minCnt) return;
		
		// base case: 모든 행을 검사하고 나면
		if (row == D) {
			
			// 열별로 해당 방식이 유효한지 테스트
			boolean malfunc = false;
			
			for (int c = 0; c < W; c++) {
				
				// 패턴의 최대길이
				int max = Integer.MIN_VALUE;
				
				// 현재 패턴 길이
				int cur = 1;
				
				for (int r = 0; r < D; r++) {
					if (r == 0) continue;
					
					// 만약 패턴이 끊기면
					if (film[r][c] != film[r-1][c]) {
						
						// 패턴길이 최대값과 비교
						if (cur > max) max = cur;
						cur = 1; // 다시 카운트 초기화
						
					}
					else cur++; // 안 끊기면 계속 카운트 증가

					// 끝까지 오면 다시 패턴길이 최대값과 비교
					if (r == D-1 && cur > max) max = cur;
				}
				
				// 만약 해당 열의 최대길이가 K 미만이면 안되는 경우로 표시하고 검사 종료
				if (max < K) { malfunc = true; break; }
			}
			
			// 만약 끝까지 잘 통과했고 약품 처리횟수가 최소값보다 작다면 갱신
			if (!malfunc && cnt < minCnt) minCnt = cnt;
			
			return;
		}
		
		// recursive case: 행별로 처리
		
		// 약품처리 안하는 경우
		test(row+1, cnt);
		
		// 약품처리 하는 경우
		// 임시변수를 이용해 값 변경
		int[] temp = film[row];
		film[row] = zero; // 0으로 약품처리하고 재귀 진행
		test(row+1, cnt+1);
		film[row] = temp; // 값 되돌리기
		
		film[row] = one; // 1로 약품처리하고 재귀 진행
		test(row+1, cnt+1);
		film[row] = temp; // 값 되돌리기
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb;
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			// 필름 두께, 가로 크기, 합격 기준
			D = input.nextInt();
			W = input.nextInt();
			K = input.nextInt();
			
			// 배열 크기 설정
			film = new int[D][W];
			zero = new int[W];
			one = new int[W];
			
			// 배열 one은 전부 1이니까 1로 변경
			Arrays.fill(one, 1);
			
			// 보호필름 정보 입력
			for (int r = 0; r < D; r++) {
				for (int c = 0; c < W; c++) {
					film[r][c] = input.nextInt(); // A면 0, B면 1
				}
			}

			// 최소값 초기화
			minCnt = Integer.MAX_VALUE;
			
			// 테스트 on
			test(0, 0);
			
			// 최소값 추가
			sb.append(minCnt);
			
			// 테케별 출력
			System.out.println(sb.toString());
		}
		
		
	}
}