import java.util.*;

class Solution {
    int n, m;
    boolean[][] visited;
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        int sr = -1;
        int sc = -1;
        int lr = -1;
        int lc = -1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maps[r].charAt(c) == 'S') {
                    sr = r;
                    sc = c;
                }
                
                if (maps[r].charAt(c) == 'L') {
                    lr = r;
                    lc = c;
                }
            }
        }
        
        int startToLever = findTarget(sr, sc, maps, 'L');
        int leverToExit = findTarget(lr, lc, maps, 'E');
        
        if (startToLever == -1 || leverToExit == -1) {
            return -1;
        }
        
        return startToLever + leverToExit;
    }
    
    int findTarget(int sr, int sc, String[] maps, char target) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[n][m];
        
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            int time = cur[2];
            
            for (int dt = 0; dt < 4; dt++) {
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];
                
                if (outOfIndex(nr, nc)) continue;
                
                if (maps[nr].charAt(nc) == 'X') continue;
                 
                if (visited[nr][nc]) continue;
                
                if (maps[nr].charAt(nc) == target) {
                    return time + 1;
                }
                
                queue.offer(new int[]{nr, nc, time + 1});
                visited[nr][nc] = true;
            }
        }
        
        // 목표까지 도달하지 못하고 탐색이 끝나면 -1 반환
        return -1;
    }
    
    
    boolean outOfIndex(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}