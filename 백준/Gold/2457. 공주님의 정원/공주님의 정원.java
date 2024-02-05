// 공주님의정원

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 꽃의 개수
    static int n;

    // 각 꽃이 지는 날짜, 피는 날짜 저장
    static int[][] flower;

    // 정답 출력을 위한 카운트
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        greedy();
        answer();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        flower = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startDate = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int endDate = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flower[i][0] = startDate;
            flower[i][1] = endDate;
        }
    }

    static void greedy() {
        // 시작: 3월 1일에 피는 꽃 중에서 가장 오래 피는 꽃 찾기
        int curTime = 301;

        // 카운트 초기화
        answer = 0;

        // 11월 30일에 도달할 때까지 이어서 꽃 피우기
        while (curTime < 1201) {
            // 이 다음 꽃이 지는 시간, 초기값은 시작일인 3월 1일
            int nextTime = curTime;

            for (int i = 0; i < n; i++) {
                // 만약 꽃이 기준 날짜에 피어 있다면 기존 최댓값과 비교해서 갱신
                if (flower[i][0] <= curTime && flower[i][1] > nextTime) {
                    nextTime = flower[i][1];
                }
            }
            // 만약 시작점에서 더 나아가지 못한다면 불가능하므로 불가능 표시
            if (nextTime == curTime) {
                answer = 0;
                return;
            }

            // 카운트 하나 증가
            answer++;

            // 마지막 꽃이 지는 시간 변경
            curTime = nextTime;
        }
    }

    static void answer() {
        System.out.println(answer);
    }
}