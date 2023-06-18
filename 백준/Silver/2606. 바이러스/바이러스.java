// 바이러스

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 컴퓨터의 수, 연결된 네트워크의 수
    static int n, m;

    // 컴퓨터 네트워크(인접리스트)
    static LinkedList<Integer>[] adj;

    // bfs를 위한 queue
    static Queue<Integer> queue;

    // bfs를 위한 감염확인배열
    static boolean[] checked;

    // 감염된 컴퓨터 수
    static int infectedComputerCnt;

    // bfs 알고리즘
    static void bfs(int src) {

        // 감염된 컴퓨터 queue에 추가
        queue.offer(src);

        // 감염체크
        checked[src] = true;

        // 더 확인할 컴퓨터가 없을 때까지
        while (!queue.isEmpty()){

            // 확인할 차례인 컴퓨터 번호 가져오기
            int cur = queue.poll();

            // 해당 컴퓨터와 연결된 컴퓨터 목록
            for (int next : adj[cur]){

                // 아직 확인해보지 않은 컴퓨터면
                if (!checked[next]){
                    // 다음에 네트워크 체크해보게 queue에 추가
                    queue.offer(next);

                    // 감염 체크
                    checked[next] = true;

                    // 감염된 컴퓨터 수 증가
                    infectedComputerCnt++;
                }
            }
        }

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 컴퓨터의 수
        n = input.nextInt();

        // 연결된 컴퓨터 쌍의 수
        m = input.nextInt();

        // 연결리스트 생성
        adj = new LinkedList[n+1];

        // 초기화
        for (int cpu = 1; cpu <= n; cpu++){
            adj[cpu] = new LinkedList<>();
        }

        // 네트워크 상태 입력
        for (int edge = 1; edge <= m; edge++){
            int a = input.nextInt();
            int b = input.nextInt();

            // 양방향 연결이니까 둘다 추가
            adj[a].add(b);
            adj[b].add(a);
        }

        // bfs를 위한 queue, 방문배열, 카운트 초기화
        queue = new LinkedList<>();
        checked = new boolean[n+1];
        infectedComputerCnt = 0;

        // bfs on
        bfs(1);

        // 카운트 출력
        System.out.println(infectedComputerCnt);
    }
}