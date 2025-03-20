import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {    
        int answer = 0;
        
        answer = solve(maps);
        
        return answer;
    }
    
    static int solve(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int[][] visited = new int[n][m];
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {0, 0});
        visited[0][0] = 1;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for (int dt = 0; dt < 4; dt++) {
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];
                
                // out of index
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                
                // 벽
                if (maps[nr][nc] == 0) continue;
                
                // 이미 방문한 지역
                if (visited[nr][nc] > 0) continue;
                
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = visited[cr][cc] + 1;
                
                if (nr == n-1 && nc == m-1) {
                    return visited[nr][nc];
                }
            }
        }
        
        return -1;
    }
}