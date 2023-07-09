// 직각삼각형

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        boolean terminated = false;

        while (!terminated){

            // 세 변의 길이 입력
            int[] lines = new int[3];
            for (int i = 0; i < 3; i++) {
                lines[i] = input.nextInt();
            }

            // 입력이 000이면 종료
            if (lines[0] == 0 && lines[1] == 0 && lines[2] == 0){
                terminated = true;
            }
            else {
                // 혹시 모르니까 정렬
                Arrays.sort(lines);

                // 피타고라스 정리로 직각삼각형임을 확인
                int a = (int) Math.pow(lines[0], 2);
                int b = (int) Math.pow(lines[1], 2);
                int c = (int) Math.pow(lines[2], 2);

                // 결과에 따라 입력
                if (a + b == c){
                    sb.append("right");
                }
                else
                    sb.append("wrong");

                sb.append("\n");
            }
        }

        // 정답 출력
        System.out.println(sb);
    }
}