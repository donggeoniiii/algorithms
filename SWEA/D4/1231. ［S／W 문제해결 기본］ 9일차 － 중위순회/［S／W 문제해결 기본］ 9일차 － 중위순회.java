//중위순회

import java.util.Scanner;
import java.util.StringTokenizer;


// tree의 노드
class Node {
	
	// 값이 되는 문자
	char item; 
	
	// 왼쪽, 오른쪽 노드 번호
	int left;
	int right;
}

public class Solution {	
	
	// 2차원 배열을 이용한 트리 구조 기억
	static Node[] tree;
	
	// 정답 입력을 위한 StringBuilder
	static StringBuilder sb = new StringBuilder();
	
	// 중위 순회 : L - C - R
	static void inTraverse(Node[] tree, int idx) {
		
		// tree의 크기
		int N = tree.length;
		
		// tree의 범위 내에서
		if (idx < N && idx > 0) {
			
			// 왼쪽 - 자기 자신 - 오른쪽 방향으로 순회
			inTraverse(tree, tree[idx].left);
			sb.append(tree[idx].item);
			inTraverse(tree, tree[idx].right);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 테스트 케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			
			// 양식 맞추기
			sb.append("#").append(tc).append(" ");
			
			// 노드의 개수
			int N = Integer.parseInt(input.nextLine());
			
			// 노드의 개수에 맞춰 이진트리 생성
			tree = new Node[N+1];
			
			// 노드의 정보 입력
			for (int data = 1; data <= N; data++) {
				st = new StringTokenizer(input.nextLine());
				
				// 트리에 새로운 노드 생성
				tree[data] = new Node();
				
				// 처음에 들어오는 숫자는 노드 번호인데 이는 반복문으로 저장 가능하므로 따로 저장 x
				st.nextToken();
				
				// 노드별 문자 입력
				tree[data].item = st.nextToken().charAt(0);
				
				// 만약 추가정보(자식노드)가 있다면 입력
				if (st.hasMoreTokens()) {
					tree[data].left = Integer.parseInt(st.nextToken());
					if (st.hasMoreTokens()) tree[data].right = Integer.parseInt(st.nextToken());
				}
			}
			
			// 중위순회 on
			inTraverse(tree, 1);
			
			// 다음 테스트케이스 정답 입력을 위해 개행문자 추가
			sb.append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}	
}
