import java.util.Scanner;

public class Main {
    // 유저의 수, 친구 관계의 수
    static int n, m;

    // 인접배열
    static int[][] adj;

    // 배열 초기화를 위한 큰 수
    static final int INF = Integer.MAX_VALUE;

    // 케빈 베이컨 수를 합산한 배열
    static int[] totalCnt;

    // 케빈 베이컨 최솟값
    static int minCnt;

    // 최솟값을 갖는 사람
    static int answer;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 유저의 수, 친구 관계의 수
        n = input.nextInt();
        m = input.nextInt();

        // 배열 및 변수 초기화
        adj = new int[n+1][n+1];
        totalCnt = new int[n+1];
        minCnt = 9999;
        answer = 0;

        // 거리 계산의 편의성을 위해 인접배열의 값은 큰 값으로 초기화
        for (int src = 1; src <= n; src++){
            for (int dest = 1; dest <= n; dest++){
                // 시작점과 도착점이 같으면 자기자신, 거리는 0이어야 하니까 제외
                if (src != dest)
                    adj[src][dest] = INF;
            }
        }

        // 친구관계 입력
        for (int edge = 1; edge <= m; edge++){
            int src = input.nextInt();
            int dest = input.nextInt();

            // 양방향으로 입력
            adj[src][dest] = adj[dest][src] = 1;
        }

        // floyd-warshall: 모든 정점에서 모든 정점간에 최소 거리 계산
        for (int joint = 1; joint <= n; joint++) {
            for (int src = 1; src <= n; src++) {
                for (int dest = 1; dest <= n; dest++) {
                    // 최솟값으로 갱신
                    if (adj[src][joint] != INF && adj[joint][dest] != INF)
                        adj[src][dest] = Math.min(adj[src][dest], adj[src][joint] + adj[joint][dest]);
                }
            }
        }

        // 거리 합 구하기, 모든 사람들은 연결되어 있다는 가정이 있으므로 inf값이 들어갈 일 없음
        for (int src = 1; src <= n; src++) {
            for (int dest = 1; dest <= n; dest++){
                totalCnt[src] += adj[src][dest];
            }

            // 케빈베이컨 점수가 최솟값이 갱신되었으면
            if (totalCnt[src] < minCnt){
                // 정답 수정
                answer = src;

                // 최솟값 갱신
                minCnt = totalCnt[src];
            }
        }

        // 정답 출력
        System.out.println(answer);
    }
}