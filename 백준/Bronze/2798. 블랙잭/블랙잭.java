// 블랙잭

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // 카드 수
    static int N;

    // 맞춰야 하는 숫자
    static int M;

    // 현재까지 가장 근접한 값
    static int best;

    // 카드번호
    static int[] cards;

    static boolean[] visited;

    // 백트래킹 알고리즘
    static void track(int cnt, int sum){
        // pruning: 만약 블랙잭이 이미 됐으면 종료
        if (best == M){
            return;
        }
        
        // base case: 3개 다 고르면
        if (cnt >= 3) {

            // 값 갱신
            if (sum <= M && sum > best)
                best = sum;

            return;
        }

        // recursive case
        for (int i = 0; i < N; i++){

            if (!visited[i]){

                // 방문체크
                visited[i] = true;

                // 다음 선택하러 이동
                track(cnt+1, sum+cards[i]);

                // 다른 경우를 위해 체크해제
                visited[i] = false;
            }

        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 카드 수
        N = input.nextInt();

        // 목표하는 점수
        M = input.nextInt();

        // 카드 번호 입력
        cards = new int[N];
        for (int i = 0; i < N; i++){
            cards[i] = input.nextInt();
        }

        // 골랐는지 여부 보기
        visited = new boolean[N];

        // 백트래킹 on
        track(0, 0);

        // 정답 출력
        System.out.println(best);
    }
}