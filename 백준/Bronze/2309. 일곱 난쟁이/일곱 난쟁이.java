// 일곱난쟁이

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 난쟁이 수 = 9명 고정
        int n = 9;

        // 전체 키 합
        int totalHeight = 0;

        // 키를 저장할 배열, 두 난쟁이의 키 합을 저장할 배열 생성
        int[] height = new int[n+1];
        int[][] sum = new int[n+1][n+1];

        // 키 저장
        for (int i = 1; i <= n; i++) {
            height[i] = input.nextInt();
            totalHeight += height[i];
        }

        // 편리한 출력을 위해 배열 정렬
        Arrays.sort(height);

        // 두 난쟁이의 키 합 모든 경우에 대해 계산 저장
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (i != j)
                    sum[i][j] = height[i] + height[j];
            }
        }

        // 전체 키 합 - 100 == 빼야할 키
        int targetHeight = totalHeight - 100;

        // 두 난쟁이의 키 합을 보면서 해당하는 값을 찾게 되면 해당 난쟁이 index 저장
        int delete1 = 0;
        int delete2 = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (sum[i][j] == targetHeight){
                    delete1 = i;
                    delete2 = j;
                }
            }
        }

        // 정답 출력
        for (int i = 1; i <= n; i++) {
            if (i != delete1 && i != delete2)
                sb.append(height[i]).append("\n");
        }

        System.out.println(sb);
    }
}