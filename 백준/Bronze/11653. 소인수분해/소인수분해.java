// 소인수분해

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 소인수 모음
        StringBuilder sb = new StringBuilder();

        // n이 1이면 출력 안함
        if (n != 1) {
            // 2부터 하나씩 나눠지는지 확인
            int div = 2;
            while (n > 1) {
                // 나눠지면 계속 나누기
                if (n % div == 0) {
                    n /= div;
                    sb.append(div).append("\n");
                }
                // 안 나눠지면 나누는 수 +1
                else {
                    div++;
                }
            }
            // 출력
            System.out.println(sb);
        }

    }
}