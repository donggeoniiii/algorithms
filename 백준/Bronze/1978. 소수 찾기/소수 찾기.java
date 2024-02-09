// 소수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 소수이려면 1과 자기자신을 제외하고 나누어 떨어지지 않아야 함
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (isPrime(cur)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPrime(int num) {
        // 1은 애초에 소수도 합성수도 아니다
        if (num == 1) {
            return false;
        }
        for (int j = 2; j * j <= num; j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }
}