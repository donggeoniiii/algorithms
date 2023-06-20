// 경로 찾기

import java.util.Scanner;

public class Main {
    // 정점의 개수
    static int n;

    // 인접행렬
    static int[][] adj;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 정점의 개수
        n = input.nextInt();

        // 인접행렬
        adj = new int[n+1][n+1];

        // 인접행렬에 주어진 정보 저장
        for (int cur = 1; cur <= n; cur++) {
            for (int next = 1; next <= n; next++){
                adj[cur][next] = input.nextInt();
            }
        }

        // 인접리스트 돌면서 이동 가능 여부 확인 후 저장
        /*
         floyd-warshall: i->j로의 경로가 있다 == i -> k / k -> j로의 경로가 있다
         */
        for (int joint = 1; joint <= n; joint++){ // 경유 좌표
            for (int src = 1; src <= n; src++){ // 출발 좌표
                for (int dest = 1; dest <= n; dest++){ // 도착 좌표

                    // 만약 src -> joint & joint -> dest로 이동이 가능하다면
                    if (adj[src][joint] == 1 && adj[joint][dest] == 1){

                        // src -> dest로 이동 가능하다는 뜻
                        adj[src][dest] = 1;
                    }
                }
            }
        }


        // 정답 출력
        for (int src = 1; src <= n; src++){
            for (int dest = 1; dest <= n; dest++){
                sb.append(adj[src][dest]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

        input.close();
    }

}