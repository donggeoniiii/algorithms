// 영식이와 친구들

import java.util.Deque;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 플레이하는 사람의 수
        int N = input.nextInt();

        // 공 던지는 횟수
        int M = input.nextInt();

        // 총 던지는 횟수
        int L = input.nextInt();

        // 애들을 담을 deque
        Deque<Integer> deque = new LinkedList<>();

        // 초기값(공을 받은 횟수)을 0으로 설정
        for (int i = 1; i <= N; i++) deque.offer(0);

        // 돌리는 횟수 카운트
        int cnt = 0;

        // while문 탈출변수
        boolean terminated = false;

        while (!terminated) {

            // 공 받기
            deque.offerFirst(deque.poll() + 1);

            // 만약 peek한 값이 M이면 종료
            int tagger = deque.peek();
            if (tagger == M) break;

            // 받은 횟수가 홀수면
            if (deque.peek() % 2 != 0) {

                // 시계방향(여기서는 first -> last)으로 l만큼 이동
                for (int i = 1; i <= L; i++) deque.offerLast(deque.pollFirst());

            } else { // 카운트가 짝수라면 : if (deque.peek() % 2 == 0)

                // 반시계방향(여기서는 last -> first)으로 l만큼 이동
                for (int i = 1; i <= L; i++) deque.offerFirst(deque.pollLast());
            }

            // 카운트 증가
            cnt++;
        }
        
        // 정답 출력
        System.out.println(cnt);
    }
}