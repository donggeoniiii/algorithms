// 소수 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // n 이하의 소수로 나누어져 합성수인 애들 거르기
        boolean[] isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);
        
        // 1은 소수도 합성수도 아니다
        isPrime[1] = false;
        
        for (int i = 2; i*i <= n; i++) {
            // 합성수면 스킵
            if (!isPrime[i]) {
                continue;
            }
            // 소수인 경우 해당 소수의 배수들은 모두 합성수이므로 표시
            for (int j = i*i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if (isPrime[i]){
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }
}