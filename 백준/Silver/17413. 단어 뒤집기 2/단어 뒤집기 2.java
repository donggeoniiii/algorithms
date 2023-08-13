// 단어 뒤집기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력 받을 전체 배열
		String input = br.readLine();

		// 배열을 출력할 때 부분적으로 끊어서 저장할 queue
		Queue<String> queue = new LinkedList<>();

		// 해당 입력이 태그 내인지 확인하는 변수
		boolean isTag = false;

		// 배열을 돌면서 저장
		for (int idx = 0; idx < input.length(); idx++) {
			// 만약 해당 index가 태그의 여는 값이면
			if (input.charAt(idx) == '<') {
				// 이제부터 태그 안이니까
				isTag = true;

				// 이전까지 index 모은 값 queue에 저장
				if (sb.length() != 0)
					queue.offer(String.valueOf(sb));

				// stringBuilder 초기화
				sb = new StringBuilder();

				// queue에 태그 넣기
				sb.append(input.charAt(idx));
			}
			// 만약 해당 index가 태그의 닫는 값이면
			else if (input.charAt(idx) == '>') {
				// 일단 stringBuilder에 태그 닫는 기호 추가
				sb.append(input.charAt(idx));

				// 그대로 queue에 추가하고 초기화
				queue.offer(String.valueOf(sb));
				sb = new StringBuilder();

				// 이제부터 태그 밖이니까
				isTag = false;
			}
			// 만약 해당 index가 빈칸이면
			else if (input.charAt(idx) == ' ') {
				// 일단 stringbuilder에 띄어쓰기 추가
				sb.append(" ");

				// 만약 태그 바깥이면
				if (!isTag) {
					// 이전까지 index 모은 값 queue에 저장
					queue.offer(String.valueOf(sb));

					// stringbuilder 초기화
					sb = new StringBuilder();
				}
			}
			// 그냥 값이면 그대로 stringBuilder에 추가
			else {
				sb.append(input.charAt(idx));

				// 만약 마지막 index면 그대로 queue에 추가
				if (idx == input.length() - 1) {
					queue.offer(String.valueOf(sb));
				}
			}
		}

		// 이제 전체 문자열로 합치기
		sb = new StringBuilder();

		// queue를 비우면서 순서대로 출력
		while (!queue.isEmpty()) {
			// 출력할 문자열
			String cur = queue.poll();

			// 만약 첫 index가 태그면 그대로 출력형태에 추가
			if (cur.charAt(0) == '<')
				sb.append(cur);
			else {
				// 아니면 뒤집어야 하니까 뒤집어서 넣기
				int n = cur.length();

				// 근데 만약 마지막 index가 빈칸이면
				if (cur.charAt(n - 1) == ' ') {
					// 그 전칸까지 하고
					for (int i = n - 2; i >= 0; i--) {
						sb.append(cur.charAt(i));
					}
					// 띄어쓰기 입력
					sb.append(" ");
				}
				// 아니면 그대로 진행
				else {
					for (int i = n - 1; i >= 0; i--) {
						sb.append(cur.charAt(i));
					}
				}
			}
		}

		// 정답 출력
		System.out.println(sb);
	}
}