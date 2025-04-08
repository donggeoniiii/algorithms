import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int m = triangle[n-1].length;
        
        // dp[i][j] : i+1번째 level j칸까지 더했을 때 최대합
        int[][] dp = new int[n][m];
        dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];
        dp[1][1] = dp[0][0] + triangle[1][1];
        
        for (int i = 2; i < n; i++) {
            int cm = triangle[i].length;
            for (int j = 0; j < cm; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == cm-1) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;
    }
}