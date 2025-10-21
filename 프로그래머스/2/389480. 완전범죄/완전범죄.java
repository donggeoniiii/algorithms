import java.util.*;

class Solution {    
    public int solution(int[][] info, int n, int m) {
        int INF = 999999;
        int answer = INF;
        int count = info.length;
    
        // dp[i][j]: b가 i번째까지 j개의 흔적을 남겼을 때 a의 흔적 최소개수
        int[][] dp = new int[count][m];
        for (int i = 0; i < count; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        // 초기화: 첫번째를 각각 A, B가 훔치는 경우
        dp[0][0] = info[0][0];
        if (info[0][1] < m) {        
           dp[0][info[0][1]] = 0;    
        }
        
        for (int i = 1; i < count; i++) {
            int a = info[i][0];
            int b = info[i][1];
            
            for (int j = 0; j < m; j++) {
                // a가 훔친 경우 a의 흔적: i-1번째에서 b가 j개 흔적을 남긴 상황 + a
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
            
                if (j + b >= m) continue;
                
                // b가 훔친 경우 a의 흔적: i-1번째에서 b가 j개 흔적을 남긴 상황과 동일
                dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
            }
        }
        
        for (int b = 0; b < m; b++) {
            answer = Math.min(answer, dp[count-1][b]);
        }
        
        return (answer >= n) ? -1 : answer;
    }
}