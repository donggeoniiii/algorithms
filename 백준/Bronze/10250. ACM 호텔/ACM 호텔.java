// ACM 호텔

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 개수
        int T = input.nextInt();

        for (int tc = 1; tc <= T; tc++){
            // 호텔의 층 수
            int R = input.nextInt();

            // 각 층의 방 수
            int C = input.nextInt();

            // 몇번째 손님인지
            int N = input.nextInt();

            /*
            손님이 들어온 순서를 층수로 나눈 나머지가 곧 층수와 연결되고,
            몫이 곧 해당 층수의 방 번호를 결정한다.
            층수는 나머지가 0일 때 꼭대기(C), 나머지는 나머지값 그대로
            몫은 n일 때 n+1번방에 들어가면 된다
             */

            // 층수: N % R, 나머지가 0인 경우 따로 고려
            int floor = (N % R == 0) ? R : N%R;

            // 방번호: N / R, 나머지가 0인 경우 따로 고려
            int room = (N % R == 0) ? N/R : N/R+1;

            // 테케별 정답 입력
            sb.append(floor);
            if (room < 10)
                sb.append(0);
            sb.append(room).append("\n");
        }

        // 정답 출력
        System.out.println(sb);

        input.close();
    }
}