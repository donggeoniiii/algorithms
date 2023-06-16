// 토마토

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // 토마토 박스의 크기
    static int M, N, H;

    // 델타배열
    static int[] dh = {-1,1,0,0,0,0};
    static int[] dr = {0,0,-1,1,0,0};
    static int[] dc = {0,0,0,0,-1,1};

    // 전체 토마토 박스
    static int[][][] box;

    // 같은 크기의 방문배열
    static int[][][] visited;

    // 너비 우선 탐색을 위한 queue
    static Queue<int[]> queue;

    // 최대값 저장 변수
    static int maxDate;

    // 가능/불가능 판단 변수들
    static boolean isFinished, isImpossible;

    // 너비 우선 탐색 알고리즘
    static void BFS(){
        // 더 탐색할 지점이 없을때까지
        while (!queue.isEmpty()) {

            // 방문지점 받아오기
            int[] cur = queue.poll();
            int ch = cur[0];
            int cr = cur[1];
            int cc = cur[2];

            // 델타탐색
            for (int dt = 0; dt < 6; dt++) {
                int nh = ch + dh[dt];
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];

                // out of index 처리
                if (nh >= H || nr >= N || nc >= M || nh < 0 || nr < 0 || nc < 0)
                    continue;

                // 만약 안익은 토마토면
                if (visited[nh][nr][nc] == 0) {

                    // 새로운 곳 queue에 추가
                    queue.offer(new int[] {nh,nr,nc});

                    // 방문시간 갱신
                    visited[nh][nr][nc] = visited[ch][cr][cc] + 1;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 박스 크기 입력받기
        M = input.nextInt();
        N = input.nextInt();
        H = input.nextInt();

        // 배열 초기화
        box = new int[H][N][M];
        visited = new int[H][N][M];

        // 너비우선 탐색을 위한 queue 초기화
        queue = new LinkedList<>();

        // 이미 끝난 경우인지 확인
        isFinished = true;

        // 토마토 박스 정보 입력
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    box[h][r][c] = input.nextInt();

                    // 안익은 토마토 하나라도 있으면 일단 0은 아님
                    if (box[h][r][c] == 0){
                        isFinished = false;
                    } else {
                        // -1로 입력되는 벽이어도 일단 방문처리는 해야된다
                        visited[h][r][c] = 1;

                        // 만약 토마토면 bfs 돌리게 시작점 추가
                        if (box[h][r][c] == 1) {
                            queue.offer(new int[] {h,r,c});
                        }
                    }

                }
            }
        }

        // 만약 시작부터 다 차있었다면
        if (isFinished)
            // 0 출력하고 마무리
            System.out.println(0);
        else {
            // 아니면
            // BFS ON
            BFS();

            // 최대값 찾기
            maxDate = 0;
            isImpossible = false;

            for (int h = 0; h < H; h++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        // 만약 안익은 토마토가 있다면(빈칸도 아님)
                        if (visited[h][r][c] == 0 && box[h][r][c] != -1) {
                            // 불가능 표시
                            isImpossible = true;
                        } else {
                            maxDate = Math.max(maxDate, visited[h][r][c]);
                        }
                    }
                }
            }

            // 불가능 여부에 따라 정답 출력
            if (isImpossible){
                System.out.println(-1);
            } else {
                System.out.println(maxDate -1);
            }
        }

        input.close();
    }
}