// 스택 수열

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 정렬할 숫자개수
        int N = input.nextInt();

        // 집어넣을 stack
        Stack<Integer> stack = new Stack<>();
        int[] stackArr = new int[N];

        // 값과 비교해보며 가능한지 확인
        int max = 0;
        for (int i = 1; i <= N; i++) {
            // 숫자 입력
            int num = input.nextInt();

            // 최댓값이 갱신됐으면 해당 최댓값까지 값 입력
            if (num > max){
                for (int j = max+1; j <= num; j++) {
                    stack.push(j);
                    sb.append("+").append("\n");
                }

                // 최댓값 갱신
                max = num;
            }

            // top에 있는 값과 비교해서 같으면 pop하고 출력에 저장
            if (stack.peek() == num){
                stack.pop();
                sb.append("-").append("\n");
            }
            // 만약 다르다면 출력값에 no 넣고 종료
            else {
                System.out.println("NO");
                return;
            }
        }

        // 정답 출력
        System.out.println(sb);

    }
}