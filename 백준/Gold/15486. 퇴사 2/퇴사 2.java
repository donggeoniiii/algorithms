// 퇴사 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 퇴사일까지 남은 업무일, n+1일에 퇴사
    static int n;

    // 상담별 정보 저장 테이블
    static int[] consultTime; // 상담에 걸리는 날짜
    static int[] consultWage; // 상담을 버는 돈

    // 메모이제이션 배열
    static int[] memo;

    public static void main(String[] args) throws IOException {
        init();
        dp();
        answer();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 상담 정보 저장
        consultTime = new int[1500001];
        consultWage = new int[1500001];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            consultTime[i] = Integer.parseInt(st.nextToken());
            consultWage[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dp() {
        // memo[i] : i일부터 상담해서 벌 수 있는 최고 액수
        memo = new int[n+2];

        // 퇴사 바로 전날부터 채워 나가기
        for (int day = n; day >= 1; day--) {
            // 만약 오늘 하는 상담이 퇴사일 전에 종료되지 않으면
            int consultCompleteDate = day + consultTime[day];

            if (consultCompleteDate > n + 1) {
                // 해당 일자의 일이 퇴직날 포함되지 못하므로 그 다음날부터 상담 시작하는 경우와 동일
                memo[day] = memo[day+1];
            }
            // 종료된다면
            else {
                // 이 상담을 하는 경우, 하지 않는 경우 중 더 큰 값을 배열에 입력
                memo[day] = Math.max(memo[day+1], memo[consultCompleteDate] + consultWage[day]);
            }
        }
    }

    private static void answer() {
        // 첫날부터 일했을 때 벌 수 있는 최댓값 출력
        System.out.println(memo[1]);
    }
}