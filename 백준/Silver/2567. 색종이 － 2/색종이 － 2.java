// 색종이2

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 전체 색종이 배열. 덮여있는지 아닌지만 보면 되니까 boolean으로
        boolean[][] map = new boolean[100][100];

        // 색종이의 수
        int paperNum = input.nextInt();

        // 주변탐색을 위한 델타배열
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        // 색종이 n개 덮기
        for (int i = 0; i < paperNum; i++) {
            // 시작지점(좌하단)
            int sr = input.nextInt();
            int sc = input.nextInt();

            // 덮기
            for (int r = sr; r < sr + 10; r++) {
                for (int c = sc; c < sc + 10; c++) {
                    map[r][c] = true;
                }
            }
        }

        // 둘레 길이
        int answer = 0;

        // 전체 종이 돌면서, 가장자리에 있는 칸 수 세기
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {

                // 만약 발견한 지점이 종이로 덮여진 곳이면
                if (map[r][c]){ // == if (map[r][c] == true)
                    // 주변 델타탐색 on
                    for (int dt = 0; dt < 4; dt++) {
                        int nr = r + dr[dt];
                        int nc = c + dc[dt];

                        // 만약 체크한 지점이 종이를 벗어나면 가장자리도 둘레긴 하니까 추가
                        if (nr >= 100 || nc >= 100 || nr < 0 || nc < 0)
                            answer++;

                        // 둘레 벗어나는 경우는 이제 더 생각하지 않음. if 조건문에서 오류나니까 else case에서 처리
                        else {
                            // 만약 인접좌표의 값이 0이면
                            if (!map[nr][nc]) // == if (map[r][c] == false)
                                // 가장자리니까 둘레길이 1 추가
                                answer++;
                        }
                    }
                }
            }
        }

        // 둘레 길이 출력
        System.out.println(answer);

        input.close();
    }
}