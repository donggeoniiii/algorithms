// 주사위 굴리기

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 지도의 크기
		int N = input.nextInt();
		int M = input.nextInt();
		
		// 지도 배열 생성
		int[][] map = new int[N][M];
		
		// 주사위 배열 생성 (index 0은 공석)
		int[] dice = new int[7];
		
		// 좌표를 이동시키는데 사용할 델타배열
		int[] dx = {0,0,0,-1,1};
		int[] dy = {0,1,-1,0,0};
		
		// 주사위가 시작하는 좌표
		int x = input.nextInt();
		int y = input.nextInt();
		
		// 주사위를 굴리는 명령의 개수 
		int K = input.nextInt();
		
		// 지도 초기값 입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = input.nextInt(); 
			}
		}
		
		
		// 주사위가 다 굴러갈 때까지
		while (K > 0) {
			
			// 굴릴 방향
			int dt = input.nextInt();
			
			// 해당 방향으로 굴리기
			int nx = x + dx[dt];
			int ny = y + dy[dt];
			
			// 만약 index를 벗어나면 스킵
			if (nx >= N || ny >= M || nx < 0 || ny < 0) {	
				
				// 이번턴 날리고
				K--;
				continue;		
			}
			else { // index 내면 굴리자
				// 좌표 저장할 임시 변수, 어떤 경우든 위에 있는 값이 무조건 먼저 바뀌니까
				int temp = dice[1];
				
				switch (dt) {
				case 1: // 동쪽으로 이동
					// 쭉쭉 갱신
					dice[1] = dice[4];
					dice[4] = dice[6];
					dice[6] = dice[3];
					dice[3] = temp;
					break;
				case 2: // 서쪽으로 이동
					// 쭉쭉 갱신
					dice[1] = dice[3];
					dice[3] = dice[6];
					dice[6] = dice[4];
					dice[4] = temp;
					break;
				case 3: // 북쪽으로 이동
					// 쭉쭉 갱신
					dice[1] = dice[5];
					dice[5] = dice[6];
					dice[6] = dice[2];
					dice[2] = temp;
					break;
				case 4: // 남쪽으로 갱신
					// 쭉쭉 갱신
					dice[1] = dice[2];
					dice[2] = dice[6];
					dice[6] = dice[5];
					dice[5] = temp;
					break;
				}
				
				// 만약 방문한 지점의 값이 0이면
				if (map[nx][ny] == 0) {
					
					// 주사위 바닥의 값을 지도에 복사
					map[nx][ny] = dice[6]; 
					
				} else { // 방문한 지점의 값이 0이 아니면
					
					// 주사위 바닥이 값을 흡수
					dice[6] = map[nx][ny]; 
					
					// 바닥의 값은 초기화
					map[nx][ny] = 0; 
				}
				
				// 결과 출력하고
				sb.append(dice[1]).append("\n");
				
				// 이동한 좌표 반영하기
				x = nx;
				y = ny;
				
				// 명령 하나 처리 끝!
				K--;
			}
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}