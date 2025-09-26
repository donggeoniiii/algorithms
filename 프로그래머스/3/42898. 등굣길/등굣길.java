class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n+1][m+1];
        
        for (int[] p : puddles) {
            dp[p[1]][p[0]] = -1;
        }
        
        // 초기값 설정: 집에서 오른쪽/아래쪽으로 쭉 가는 최단루트는 막히기 전까지 1가지 경우뿐
        for (int r = 2; r <= n; r++) {
            if (dp[r][1] == -1) break;
            dp[r][1] = 1;
        }
        for (int c = 2; c <= m; c++) {
            if (dp[1][c] == -1) break;
            dp[1][c] = 1;
        }
        
        for (int r = 2; r <= n; r++) {
            for (int c = 2; c <= m; c++) {                
                if (dp[r][c] == -1) continue;
                
                int top = (dp[r-1][c] == -1) ? 0 : dp[r-1][c];
                int left = (dp[r][c-1] == -1) ? 0 : dp[r][c-1];
                dp[r][c] = (top + left) % 1_000_000_007;
            }
        }
        
        return dp[n][m];
    }
}