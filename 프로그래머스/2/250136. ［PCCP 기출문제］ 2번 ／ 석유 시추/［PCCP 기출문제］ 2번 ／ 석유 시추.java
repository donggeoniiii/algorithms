import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        
        // 격자 체크를 위한 배열, 방문한 지역은 해당 지역의 크기로 표시  
        int[][] visited = new int[n][m];
        
        // 석유덩어리 탐색을 위한 델타배열
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        // 격자 탐색하며 bfs로 자리 체크
        int odx = 1;
        Queue<int[]> sizeQueue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 0) continue;
                
                // 이미 한번 체크한 석유 덩어리는 넘기고
                if (visited[r][c] > 0) continue;
                
                // 처음 보는 석유 덩어리를 발견하면 bfs on
                int curOilCnt = 0;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {r, c});
                visited[r][c] = odx;
                curOilCnt++;
                
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int cr = cur[0];
                    int cc = cur[1];
                    
                    for (int dt = 0; dt < 4; dt++) {
                        int nr = cr + dr[dt];
                        int nc = cc + dc[dt];
                        
                        if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                        
                        if (land[nr][nc] == 0) continue;
                        
                        if (visited[nr][nc] > 0) continue;
                        
                        queue.offer(new int[] {nr, nc});
                        visited[nr][nc] = odx;
                        curOilCnt++;
                    }
                }
                
                // 탐색이 끝나면 해당 탐색으로 찾은 사이즈 저장
                sizeQueue.offer(new int[] {odx++, curOilCnt});
            }
        }
        
        // 탐색으로 찾은 덩어리별 크기 배열로 저장
        int[] oilSize = new int[sizeQueue.size() + 1];
        while (!sizeQueue.isEmpty()) {
            int[] cur = sizeQueue.poll();
            int cdx = cur[0];
            int cSize = cur[1];
            
            oilSize[cdx] = cSize;
        }
        
        // 열별로 시추량 비교
        for (int c = 0; c < m; c++) {
            int curPoint = 0;
            
            HashSet<Integer> oils = new HashSet<>();
            for (int r = 0; r < n; r++) {
                int curOdx = visited[r][c];
                oils.add(curOdx);
            }
            
            for (int oil : oils) curPoint += oilSize[oil];
            answer = Math.max(curPoint, answer);
        }
        
        return answer;
    }
}