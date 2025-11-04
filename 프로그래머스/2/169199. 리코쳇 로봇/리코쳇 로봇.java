import java.util.*;

class Solution {
    int[] dr = {0, 0, -1, 1}; 
    int[] dc = {-1, 1, 0, 0};
    
    int n, m;
    boolean[][] visited;
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        
        visited = new boolean[n][m];
        
        int sr = -1;
        int sc = -1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r].charAt(c) == 'R') {
                    sr = r;
                    sc = c;
                } 
            }
        }
        
        return findAnswer(sr, sc, board);
    }
    
    int findAnswer(int sr, int sc, String[] board) {
        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[]{sr, sc, 0});   
        visited[sr][sc] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            int time = cur[2];
            
            if (board[cr].charAt(cc) == 'G') {
                return time;
            }
            
            for (int dt = 0; dt < 4; dt++) {
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];            
                
                if (outOfIndex(nr, nc)) continue;
                
                // 벽이나 장애물에 맞기 전까지 전진
                while (!outOfIndex(nr, nc) 
                     && board[nr].charAt(nc) != 'D') {
                    
                    nr += dr[dt];
                    nc += dc[dt];
                }
                
                // 최종 위치
                nr -= dr[dt];
                nc -= dc[dt];
                
                if (visited[nr][nc]) continue;
                
                queue.offer(new int[]{nr, nc, time + 1});
                visited[nr][nc] = true;
            }
        }
        
        return -1;
    }
    
    
    
    boolean outOfIndex(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}