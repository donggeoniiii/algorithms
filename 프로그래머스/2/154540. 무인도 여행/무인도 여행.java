import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    boolean[][] visited;
    int n, m;
    
    public int[] solution(String[] maps) {
        List<Integer> lands = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        visited = new boolean[n][m];
        
        int count = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maps[r].charAt(c) == 'X') continue;
                
                if (visited[r][c]) continue;
                
                // 처음 보는 땅이면 무인도 크기랑 식량 확인
                int foods = checkFoods(r, c, maps);
                lands.add(foods);
            }
        }
        
        // 오름차순 정렬
        lands.sort(null);
        
        if (lands.isEmpty()) lands.add(-1);
        
        return lands.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int checkFoods(int r, int c, String[] maps) {
        int foodCount = 0;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        foodCount += maps[r].charAt(c) - '0';
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            
            for (int dt = 0; dt < 4; dt++) {
                int nr = cr + dr[dt];
                int nc = cc + dc[dt];
                
                // out of index
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                
                if (maps[nr].charAt(nc) == 'X') continue;
                
                if (visited[nr][nc]) continue;
                
                foodCount += maps[nr].charAt(nc) - '0';
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        
        return foodCount;
    }
}