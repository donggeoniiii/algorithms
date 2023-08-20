// 색종이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받을 종이의 수
        int n = Integer.parseInt(br.readLine());

        // 색종이를 붙일 종이 입력
        int[][] paper = new int[1001][1001];
        for (int i = 1; i <= n; i++) {
            // 종이 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 종이 사이즈에 맞게 for문 돌려서 해당 색종이가 제일 위라고 표시
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    paper[sr+r][sc+c] = i;
                }
            }
        }

        // 색깔별로 몇개가 있는지 저장할 배열
        int[] area = new int[n+1];

        // 돌면서 저장
        for (int r = 0; r < 1001; r++) {
            for (int c = 0; c < 1001; c++) {
                int cur = paper[r][c];
                // 넓이 카운트
                area[cur]++;
            }
        }

        // 정답 출력
        if (n == 0){
            System.out.println(0);
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++)
                sb.append(area[i]).append("\n");
            System.out.println(sb);
        }
    }
}