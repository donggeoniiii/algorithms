import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        // dp[i][j] : (i+1, j+1)까지 이동하는 최단경로 수
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                
                if (isWet(i, j, puddles)) continue;
                
                if (i == 0) {
                    dp[i][j] = dp[i][j-1];
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
                }
            }
        }
        
        
        
        return dp[n-1][m-1];
    }
    
    static boolean isWet(int r, int c, int[][] puddles) {
        for (int i = 0; i < puddles.length; i++) {
            if (puddles[i][0] == c+1 && puddles[i][1] == r+1) {
                return true;
            }
        }
        
        return false;
    }
}