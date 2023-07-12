// 환승

import java.util.*;

public class Main {

    // 역의 수
    static int N;

    // 하이퍼튜브(환승역)이 연결하는 역의 수
    static int K;

    // 하이퍼 튜브의 개수
    static int M;

    // 역별로 이어지는 '환승역(하이퍼튜브)'를 저장하는 리스트
    static List<Integer>[] transfers;

    // 환승역별로 묶여있는 역을 저장하는 리스트
    static List<Integer>[] stations;

    // bfs를 위한 환승역 방문 체크 배열(boolean)
    static boolean[] visitedTransferStation;

    // bfs를 위한 역 방문 체크 배열(int)
    static int[] visitedStation;

    // bfs 알고리즘
    static void findNthStation(){
        // queue에 시작점 추가
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // 시작점 방문체크
        visitedStation[1] = 1;

        // 더 탐색할 지점이 없을 때까지
        while (!queue.isEmpty()){

            // 다음에 탐색할 역 가져오기
            int cur = queue.poll();

            // 해당 역이 들어있는 환승역 리스트
            for (int nextTransfer : transfers[cur]){

                // 이미 방문한 환승역이면 스킵
                if (!visitedTransferStation[nextTransfer]){

                    // 방문 체크
                    visitedTransferStation[nextTransfer] = true;

                    // 환승역에서 이어지는 역 리스트
                    for (int nextStation : stations[nextTransfer]){

                        // 만약 처음 보는 역이면
                        if (visitedStation[nextStation] == 0){

                            // visited에 거리 기록
                            visitedStation[nextStation] = visitedStation[cur] + 1;

                            // 만약 해당 역이 N번역이면 종료
                            if (nextStation == N)
                                return;

                            // queue에 다음 탐색을 위해 추가
                            queue.offer(nextStation);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 역의 수
        N = input.nextInt();

        // 연결하는 역의 개수
        K = input.nextInt();

        // 하이퍼 튜브의 개수
        M = input.nextInt();

        // 하이퍼튜브 초기화
        transfers = new LinkedList[N+1]; // 역별로 환승역 리스트
        for (int i = 1; i <= N; i++) {
            transfers[i] = new LinkedList<>();
        }

        stations = new LinkedList[M+1]; // 하이퍼튜브별 묶여있는 역 리스트
        for (int i = 1; i <= M; i++) {
            stations[i] = new LinkedList<>();
        }

        // 하이퍼튜브 연결 저장
        for (int i = 1; i <= M; i++) {

            for (int j = 1; j <= K; j++) {
                int cur = input.nextInt();

                // 하이퍼튜브(환승역)별 이어지는 역 정보 저장
                stations[i].add(cur);

                // 역별로 이어지는 하이퍼튜브 정보 저장
                transfers[cur].add(i);
            }
        }

        // bfs를 위한 방문배열 초기화
        visitedTransferStation = new boolean[M+1];
        visitedStation = new int[N+1];

        // bfs on
        findNthStation();

        // 정답 출력
        System.out.println(visitedStation[N] != 0 ? visitedStation[N] : -1);
    }
}