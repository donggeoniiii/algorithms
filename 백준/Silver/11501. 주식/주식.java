// 주식

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 테케 개수
    static int t;

    // 테케별 주식 정보
    static String[] stockInfo;

    // 결과 저장 배열
    static long[] answer;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        answer();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        stockInfo = new String[t+1];
        for (int tc = 1; tc <= t; tc++) {
            String sb = br.readLine() // 일 수
                    + " " + br.readLine(); // 날짜별 정보
            stockInfo[tc] = sb;
        }

        answer = new long[t+1];
    }

    static void solution() {
        for (int tc = 1; tc <= t; tc++) {
            // 테케 정보 불러오기
            String[] testcase = stockInfo[tc].split(" ");
            int day = Integer.parseInt(testcase[0]);
            long[] stock = new long[day+1];
            for (int i = 1; i <= day; i++) {
                stock[i] = Integer.parseInt(testcase[i]);
            }

            // 이번 테케에서 벌 수 있는 돈
            long curTotal = 0;

            // 최대로 벌 수 있는 돈: 부분 최대값을 갖는 날에 이전까지 매도한 것을 다 팔면 됨
            // 마지막 날부터 최댓값 갱신해가면서 갱신하지 못하는 경우에 그 차이만큼 이익으로 계산
            long maxPrice = 0;
            for (int i = day; i > 0; i--) {
                if (stock[i] > maxPrice){
                    maxPrice = stock[i];
                } else {
                    curTotal += maxPrice - stock[i];
                }
            }

            // 테케 결과
            answer[tc] = curTotal;
        }
    }

    static void answer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}