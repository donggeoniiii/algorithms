// 비밀

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 스위치의 개수
		int N = input.nextInt();
		
		// 스위치 배열, 켜지고 꺼지고만 보면 되지만 숫자로 들어오니까 int배열, index는 문제가 1부터 쓰니까 크기 +1
		int[] switches = new int[N+1];
		
		// 스위치 배열 초기상태, idx 0은 필요 없음 
		for (int idx = 1; idx <= N; idx++) {
			switches[idx] =  input.nextInt();
		}
		
		// 사람수 
		int M = input.nextInt();
		
		for (int human = 1; human <= M; human++) {
			
			// 성별, 스위치 번호
			int s = input.nextInt();
			int num = input.nextInt();
			
			// 만약 남자면
			if (s == 1) {
				
				// 배열 순회하면서 스위치 번호 확인
				for (int idx = 1; idx <= N; idx++) {
					
					// 만약 스위치 번호가 받은 숫자 num의 배수면
					if (idx % num == 0) {
						
						// 현재 상태와 반대로
						if (switches[idx] == 1) switches[idx] = 0;
						else switches[idx] = 1;
						
					}
				}
			}
			
			// 만약 여자면
			if (s == 2) {
				
				// 우선 받은 번호에 맞는 스위치 상태 변경
				if (switches[num] == 1) switches[num] = 0;
				else switches[num] = 1;
				
				// 임시 변수 left, right로 주변 확인
				int left = num-1;
			    int right = num+1;
			    
			    // index를 벗어나지 않고 양쪽 값이 같은 동안
			    while (left >= 1 && right <= N && switches[left] == switches[right]) {
			    	// 현재 상태와 반대로
					if (switches[left] == 1) switches[left] = 0;
					else switches[left] = 1;

					if (switches[right] == 1) switches[right] = 0;
					else switches[right] = 1;
			    	
					// 옆으로 이동
					left--;
					right++;
					
			    }    
			}
			
		}
		
		// 스위치 상태 입력
		for (int idx = 1; idx <= N; idx++) {
			if (idx % 20 == 0) sb.append(switches[idx]).append("\n");
			else sb.append(switches[idx]).append(" ");
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}