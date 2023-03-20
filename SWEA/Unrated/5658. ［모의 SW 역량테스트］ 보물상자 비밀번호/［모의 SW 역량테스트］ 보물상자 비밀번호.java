// 보물상자 비밀번호

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		// 개행문자 제거
		input.nextLine();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 숫자의 개수 
			int N = input.nextInt();
			
			// 구하려는 비밀번호의 크기 순서
			int K = input.nextInt();

			// 개행문자 제거
			input.nextLine();
			
			// 숫자 입력받을 queue
			Queue<Integer> queue = new LinkedList<>();
			
			
			// 숫자 입력
			String[] sInput = input.nextLine().split("");
			
			// 16진수라 알파벳도 있으므로 해당경우 숫자로 변환해서 입력
			for (int idx = 0; idx < N; idx++) {
				switch (sInput[idx]) {
				case "A":
					queue.offer(10);
					break;
				case "B":
					queue.offer(11);
					break;
				case "C":
					queue.offer(12);
					break;
				case "D":
					queue.offer(13);
					break;
				case "E":
					queue.offer(14);
					break;
				case "F":
					queue.offer(15);
					break;
				default:
					queue.offer(Integer.parseInt(sInput[idx]));		
				}
			}
			
			// 한 변에 있는 숫자 개수
			int l = N/4; // line
			
			// 가능한 문자 배열을 저장하는 Set
			Set<Integer> nSet = new HashSet<>();
			
			int turn = 0;
			
			// 가능한 문자의 배열 찾기 위한 순회
			while (turn < l) {

				for (int idx = 1; idx <= 4; idx++) {

					// 16진수를 10진수로 전환하기 위한 임시변수
					int temp = 0;
					
					for (int j = 0; j < l; j++) {
						// 한자리씩 빼오고
						int digit = queue.poll();
						
						// 자리별로 16진수 -> 10진수 전환
						temp += digit * Math.pow(16, l-1-j);
						
						// 계산했으면 다시 push
						queue.offer(digit);
					}
					
					// 계산된 수는 set에 저장
					nSet.add(temp);
				}
				
				// 한칸씩 이동
				queue.offer(queue.poll());
				turn++;
			}
			
			// 모든 경우의 수를 담을 arraylist
			List<Integer> results = new ArrayList<>();
			
			// set에 있는 친구들 배열로 꺼내오기
			for (int num : nSet) results.add(num);
			
			// 배열 정렬
			results.sort(null);
			
			// 정답 추가
			int max = results.size();
			sb.append(results.get(max-K)).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}