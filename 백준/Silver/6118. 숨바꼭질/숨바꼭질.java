import java.util.*;

public class Main {
    // 헛간의 수, 길의 수
    static int n, m;

    // 인접리스트
    static List<Integer>[] adj;

    // bfs를 위한 queue
    static Queue<Integer> queue;

    // bfs를 위한 방문배열 겸 거리 배열
    static int[] dist;

    // bfs 알고리즘
    static void bfs(){

        // 시작점은 1
        int src = 1;

        // queue에 시작점 추가
        queue.offer(src);

        // 방문체크, 시작점이니까 거리 0
        dist[src] = 0;

        // 탐색이 끝날 때까지
        while (!queue.isEmpty()){

            // 다음 탐색좌표
            int cur = queue.poll();

            // 이어지는 좌표 탐색
            for (int next : adj[cur]){

                // 아직 방문하지 않은 노드면
                if (dist[next] == -1){

                    // 방문체크, 거리+1
                    dist[next] = dist[cur] + 1;

                    // 다음 탐색좌표 저장
                    queue.offer(next);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 헛간과 길의 수
        n = input.nextInt();
        m = input.nextInt();

        // 인접리스트
        adj = new LinkedList[n+1];
        for (int node = 0; node < n; node++) {
            adj[node+1] = new LinkedList<>();
        }

        // 길 정보 입력
        for (int edge = 0; edge < m; edge++) {
            int src = input.nextInt();
            int dest = input.nextInt();

            adj[src].add(dest);
            adj[dest].add(src);
        }

        // queue 초기화
        queue = new LinkedList<>();

        // 방문배열 초기화, 시작점 거리를 0으로 쓰기 위해 -1로 초기화
        dist = new int[n+1];
        Arrays.fill(dist, -1);

        // bfs on
        bfs();

        // 최댓값 저장 변수
        int max = 0;

        // 최대값을 갖는 idx 저장 변수
        int maxIdx = 0;

        // 최대 거리를 갖는 list
        List<Integer> maxDistList = new LinkedList<>();

        // 거리 출력
        for (int node = 1; node <= n; node++) {
            int cur = dist[node];

            // 최댓값이 바뀌면
            if (cur > max){
                // 최대거리 저장 list 초기화
                maxDistList.clear();

                // 최댓값 정보 저장
                maxIdx = node;
                max = cur;
                maxDistList.add(cur);
            }
            // 최댓값과 같은 값이 들어오면
            else if (cur == max){
                // 리스트에 추가
                maxDistList.add(cur);
            }
        }

        // 출력해야 하는 애들 sb에 추가
        sb.append(maxIdx).append(" ");
        sb.append(max).append(" ");
        sb.append(maxDistList.size());

        // 출력
        System.out.println(sb);
    }
}