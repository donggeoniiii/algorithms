import java.util.*;

class Solution {
    char[][] map;
    boolean[][] visited;
    int n, m;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int answer;
    
    public int solution(String[] storage, String[] requests) {
        answer = 0;
        n = storage.length;
        m = storage[0].length();
        map = new char[n][m];
        for (int r = 0; r < n; r++) {
            map[r] = storage[r].toCharArray();
        }
        
        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 1) {
                goWithLift(target);
            }
            else { // req.length() == 2
                goWithCrane(target);
            }
        }
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if ('A' <= map[r][c] && map[r][c] <= 'Z') answer++;
            }
        }
        
        return answer;
    }
    
    void goWithCrane(char target) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == target) {
                    map[r][c] = 'x';
                }
            }
        }
    }
    
    void goWithLift(char target) {
        visited = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            if (!visited[r][0]) {
                findTarget(r, 0, target);
            }
            if (!visited[r][m-1]) {         
                findTarget(r, m-1, target);
            }
        }
        for (int c = 0; c < m; c++) {
            if (!visited[0][c]) {
                findTarget(0, c, target);
            }
            if (!visited[n-1][c]) {
                findTarget(n-1, c, target);
            }
        }
    }
    
    void findTarget(int cr, int cc, char target) {
        visited[cr][cc] = true;
        
        // 찾는 컨테이너를 찾으면 빼고 탐색 종료
        if (map[cr][cc] == target) {
            map[cr][cc] = 'x';
            return;
        }
        
        if (map[cr][cc] == 'x') {
            for (int dt = 0; dt < 4; dt++) {
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];

                if (outOfIndex(nr, nc)) continue;

                if (visited[nr][nc]) continue;

                findTarget(nr, nc, target);
            }
        }
    }
    
    boolean outOfIndex(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}