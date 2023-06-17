// 최소 스패닝 트리

import java.util.*;

public class Main {

    // 정점 N개, 간선 M개
    static int N, M;

    // 연결리스트
    static List<Integer>[] adj;

    // bfs를 위한 queue
    static Queue<Integer> queue;

    // bfs를 위한 방문배열
    static boolean[] visited;

    // bfs 알고리즘
    static void bfs(int src){

        // 시작점 추가
        queue.offer(src);

        // 시작점 방문 체크
        visited[src] = true;

        // 더 탐색할 지점이 없을 때까지
        while (!queue.isEmpty()){

            // 탐색할 다음 지점 꺼내오기
            int cur = queue.poll();

            // 연결된 모든 간선으로 넘어가기
            for (int next : adj[cur]) {

                // 만약 이미 방문한 곳이면 제외
                if (visited[next])
                    continue;

                // 처음 가는 곳이면 다음 탐색에 추가
                queue.offer(next);

                // 방문 체크
                visited[next] = true;
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 정점 N개, 간선 M개
        N = input.nextInt();
        M = input.nextInt();

        // 연결리스트 만들기
        adj = new LinkedList[N+1];

        for (int i = 0; i <= N; i++){
            adj[i] = new LinkedList<>();
        }

        // 간선 정보 입력
        for (int edge = 1; edge <= M; edge++) {
            int src = input.nextInt();
            int dest = input.nextInt();
            
            // 무방향 그래프이므로 양쪽에 입력
            adj[src].add(dest);
            adj[dest].add(src);
        }

        // BFS를 위해 queue, 방문배열 만들기
        queue = new LinkedList<>();
        visited = new boolean[N+1];

        // 연결요소의 개수는 곧 bfs로 한번에 묶이는 수와 같으므로 bfs 카운트 생성
        int bfsCnt = 0;

        // 전체 정점을 체크하면서
        for (int node = 1; node <= N; node++){

            // 아직 체크하지 않았으면 새로 bfs on
            if (!visited[node]){
                bfs(node);

                // bfs 카운트 == 연결요소의 개수
                bfsCnt++;
            }
        }

        // 정답 출력
        System.out.println(bfsCnt);

    }
}