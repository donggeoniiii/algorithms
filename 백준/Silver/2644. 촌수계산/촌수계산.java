// 촌수계산

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 사람수
    static int n;

    // 인접리스트
    static List<Integer>[] adj;

    // 방문배열, 거리도 저장
    static int[] visited;

    // 촌수 계산 알고리즘
    static int countRelation(int src, int dest) {
        // bfs로 진행할거니까 queue 생성
        Queue<Integer> queue = new LinkedList<>();

        // 방문배열 초기화
        visited = new int[n+1];

        // 시작점 추가
        queue.offer(src);

        // 시작점 방문체크, 자기 자신은 1촌이지만 +1한 값으로 세고 마지막에 -1하자
        visited[src] = 1;

        // 다음 지점 탐색
        while (!queue.isEmpty()){
            // 탐색 좌표 꺼내오기
            int cur = queue.poll();

            // 인접리스트에서 다음 좌표 가져오기
            for (int next : adj[cur]){

                // 만약 방문한 좌표가 도착지점이면
                if (next == dest){
                    // 촌수 출력, 원래는 cur까지 촌수+1이지만 시작점을 1로 계산했으니까 그대로 cur까지 촌수 출력
                    return visited[cur];
                }

                // 처음 방문하면
                if (visited[next] == 0){
                    // 방문좌표 추가
                    queue.offer(next);

                    // 방문체크, 촌수+1
                    visited[next] = visited[cur] + 1;
                }

            }
        }

        // 끝까지 돌았는데 도착점에 도달하지 못하면 종료
        return -1;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 사람수
        n = input.nextInt();

        // 시작점, 도착점
        int src = input.nextInt();
        int dest = input.nextInt();

        // 촌수 계산을 위한 인맥정보 수
        int m = input.nextInt();

        // 인접리스트 초기화
        adj = new LinkedList[n+1];
        for (int person = 1; person <= n; person++)
            adj[person] = new LinkedList<>();

        for (int edge = 1; edge <= m; edge++){
            // 정보 입력
            int x = input.nextInt();
            int y = input.nextInt();

            adj[x].add(y);
            adj[y].add(x);
        }

        // 촌수 계산
        int answer = countRelation(src, dest);

        // 정답 출력
        System.out.println(answer);
    }
}