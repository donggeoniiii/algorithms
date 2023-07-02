// 구슬 찾기

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
    어떤 문제인지 이해하고 들어가는 것이 중요하다
    이건 그래프 문제라고 알려주지 않아도 입력받은 값의 형태를 보면 충분히 유추 가능한 문제
    다른 건 몰라도 저울에 올려본 쌍의 결과값 개수는 누가 봐도 '정보'로 보여진다.
    그래프문제 같다는 생각에서 이 정보를 좀 더 가공해보자면,
    무게를 재서 한쪽으로 기울어졌다는 것은 곧 방향으로 표현할 수 있고,
    이는 곧 인접리스트에 저장된 값은 자신보다 큰/작은 값의 모음이라고 표현할 수 있다.
    즉, 하던 대로 dfs나 bfs를 이용해 탐색을 쭉 이어나가면 자신보다 크거나 작은 값들의 개수를 셀 수 있다.

    무게가 중간이 될 수 없다는 말의 의미는
    곧 이미 주어진 정보만으로도 정렬시 그 구슬의 위치가 중간을 벗어나서 있음을 알 수 있기 때문일 것이다.
    즉, 탐색을 통해 발견한 크거나, 작은 수의 개수가 n+1/2 개 이상 있다는 말이다.

    목표1: 인접리스트에 자기보다 작은 값 저장하기
    이유: a b 로 주어지면 a>b였다는 말이므로 그대로 adj[a].add(b)로 저장하면 됨
    목표2: 각 구슬별로 bfs/dfs를 통해 자기보다 작은 값을 갖는 구슬의 개수 세기
    이유: 그래야지 이 구슬이 가능성이 있는지 없는지를 헤아릴 수 있음.

    ------> 생각해보니, 단순히 크거나 작은 쪽 한 번만 본다고 이 구슬의 가운데 있을 수 없다는 것을 확정할 수는 없다.
    물론, 자기보다 큰 구슬만 봤을 때 이미 가운데 있을 수 없다는 것을 확정할 수는 없지만,
    만약 큰 구슬만 봤을 때는 나보다 큰 구슬의 수가 n+1/2개보다 작다고 하더라도, 작은 구슬에서 이를 알 수도 있다.
    즉, 검사는 두번 해보되, 그 중 한번이라도 유효하게 나오면 불가능으로 처리해야 한다.
    ===> 인접리스트가 2개 필요하다!
 */

public class Main {

    // 구슬의 개수
    static int N;

    // 저울에 올려본 쌍 정보의 개수
    static int M;

    // 해당 구슬보다 작은 무게를 갖는 구슬을 저장할 인접 리스트
    static List<Integer>[] adjLighter;

    // 해당 구슬보다 큰 무게를 갖는 구슬을 저장할 인접 리스트
    static List<Integer>[] adjHeavier;

    // bfs 알고리즘, 탐색한 구슬의 개수 return
    static int countMarbles(int src, List<Integer>[] adj) {

        // 탐색한 구슬의 개수
        int marbleCnt = 0;

        // bfs를 위한 queue
        Queue<Integer> queue = new LinkedList<>();

        // bfs를 위한 방문배열 초기화
        boolean[] visited = new boolean[N+1];

        // queue에 시작점 추가
        queue.offer(src);

        // 다시 탐색하지 않도록 방문 체크
        visited[src] = true;

        // 더 탐색할 수 없을 때까지
        while (!queue.isEmpty()) {
            // 이번에 탐색할 구슬 뽑기
            int cur = queue.poll();

            // 인접리스트를 통해 직접 저울을 통해 비교한, 무게가 다른 구슬의 개수 가져오기
            for (int next : adj[cur]){

                // 만약 체크하지 않은 구슬이라면
                if (!visited[next]){

                    // 방문체크
                    visited[next] = true;

                    // 자기와 무게가 다른 새로운 구슬을 찾았으니까 개수 증가
                    marbleCnt++;

                    // 새로운 탐색을 위해 queue에 등록
                    queue.offer(next);
                }
            }
        }

        // 구슬의 개수 반환
        return marbleCnt;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 구슬의 수
        N = input.nextInt();

        // 정보의 수
        M = input.nextInt();

        // 정보 입력을 위한 인접리스트 초기화
        adjLighter = new LinkedList[N+1];
        adjHeavier = new LinkedList[N+1];
        for (int marble = 1; marble <= N; marble++){
            adjLighter[marble] = new LinkedList<>();
            adjHeavier[marble] = new LinkedList<>();
        }

        // 정보 입력
        for (int info = 1; info <= M; info++) {
            int lighterMarble = input.nextInt();
            int heavierMarble = input.nextInt();

            // 각 인접 리스트에 값 입력
            adjHeavier[lighterMarble].add(heavierMarble);
            adjLighter[heavierMarble].add(lighterMarble);
        }

        // 무게가 중간이 될 수 없는 구슬의 개수
        int answer = 0;

        // 각 구슬별로 가볍고 무거운 구슬의 수 세기
        for (int marble = 1; marble <= N; marble++) {
            // 확정 가벼운 구슬의 개수
            int lighterMarbleCnt = countMarbles(marble, adjLighter);

            // 확정 무거운 구슬의 개수
            int heavierMarbleCnt = countMarbles(marble, adjHeavier);

            // 만약 둘 중 하나가 N+1개 이상이면
            if ((lighterMarbleCnt >= (N+1)/2) || (heavierMarbleCnt >= (N+1)/2)) {

                // 무게가 중간이 될 수 없는 구슬이므로 카운트 증가
                answer++;
            }
        }

        // 정답 출력
        System.out.println(answer);

    }
}