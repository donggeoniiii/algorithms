import java.util.*;

class Solution {
    static boolean[] visited;
    
    static int n, answer;
    
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        
        // 던전 개수가 8개 이하 -> 모든 경우 탐색 8!(몇개 안됨)
        n = dungeons.length;
        visited = new boolean[n];
        solve(dungeons, 0, 0, k);
        
        return answer;
    }
    
    static void solve(int[][] dungeons, int dungeonCount, int clearedDungeonCount, int energy) {
        if (dungeonCount >= n) {
            
        
            answer = Math.max(answer, clearedDungeonCount);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            // 이미 체크한 던전 건너뛰기
            if (visited[i]) continue;
            
            visited[i] = true;
            
            // 던전 입장이 되는 경우에만 피로도 반영
            if (energy >= dungeons[i][0]) {
                solve(dungeons, dungeonCount + 1, clearedDungeonCount + 1, energy - dungeons[i][1]);
            } else {
                solve(dungeons, dungeonCount + 1, clearedDungeonCount, energy);
            }
            
            // 다른 케이스를 위해 탐색여부 초기화
            visited[i] = false;
        }
    }
}