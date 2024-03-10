import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// 연산의 개수
	static int n;

	// 연산에 사용할 정수
	static int[] order;

	// 힙으로 사용할 배열
	static int[] heap;

	// 힙의 루트는 index 1로 (0 사용 안함)
	static int root = 1;

	// 현재 원소의 개수
	static int total;

	// 출력형태
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		order = new int[n];
		heap = new int[100001];

		for (int i = 0; i < n; i++) {
			order[i] = Integer.parseInt(br.readLine());
		}

		sb = new StringBuilder();
	}

	private static void solution() {
		for (int i = 0; i < n; i++) {
			// 수행할 연산이 0이면 pop, 아니면 push(i)
			if (order[i] == 0){
				pop();
			}
			else {
				push(order[i]);
			}
		}

		// 출력
		System.out.println(sb);
	}

	private static void pop() {
		// 힙이 비어있으면 0 출력
		if (total == 0) {
			sb.append(0).append("\n");
			return;
		}

		// 비어있지 않으면 루트에 있는 값 출력하고
		sb.append(heap[root]).append("\n");

		// 순서 재배치를 위해 root에 마지막 index값 넣기, 하나 빠져나갔음을 표시하기 위해 최대 index 수정
		heap[root] = heap[total--];

		// 남은 순서 배치
		int idx = root;

		// 리프에 도달하면 종료
		while (2 * idx <= total) {
			// 두 자식의 index는 현재 index에서 x2배, x2배 + 1
			int left = 2 * idx;
			int right = 2 * idx + 1;

			// 두 자식 중 더 작은 값을 갖는 index (기본값은 일단 왼쪽으로 두고)
			int minChildIdx = left;

			// 만약 오른쪽 자식이 존재하면서 왼쪽 자식보다 더 작다면
			if (right <= total && heap[right] < heap[left]) {
				// 교체할 거면 오른쪽 자식과 교체 해야 됨
				minChildIdx = right;
			}

			// 둘 중 더 작은 자식이 현재 index보다 값이 크거나 같으면 바꿀 필요 없음
			if (heap[idx] <= heap[minChildIdx]) {
				break;
			}

			// 더 작은 값이 있다면 자리 바꾸기
			int temp = heap[idx];
			heap[idx] = heap[minChildIdx];
			heap[minChildIdx] = temp;

			// 하위 트리 구조에서 비교하기 위해 index 갱신
			idx = minChildIdx;
		}
	}

	private static void push(int num) {
		// 다음 자리에 숫자를 넣고
		heap[++total] = num;

		// 순서 배치
		int idx = total;

		// 루트에 도달하면 종료
		while (idx != root) {
			// 힙은 이진트리이므로 부모의 index는 정확히 자녀의 index를 2로 나눈 몫
			int parent = idx / 2;

			// 최소 힙이므로 부모가 더 작으면 올바른 형태이므로 종료
			if (heap[parent] <= heap[idx]) {
				break;
			}

			// 만약 부모가 더 크면 자리 바꾸기
			int temp = heap[parent];
			heap[parent] = heap[idx];
			heap[idx] = temp;

			// 상위 트리 구조에서 비교하기 위해 index 갱신
			idx = parent;
		}
	}


}