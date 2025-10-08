import java.util.*;

class Solution {
    public int solution(int n, int number) {
        /*
            1번 -> dp[1]
            2번 -> dp[1] & dp[1]
            3번 -> dp[2] & dp[1] / dp[1] & dp[2]
            4번 -> dp[3] & dp[1] / dp[2] & dp[2] / dp[1] & dp[3]
            ...
        */
        Set<Integer>[] dp = new HashSet[9];
        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
        }
        
        for (int i = 1; i <= 8; i++) {
            String nn = String.valueOf(n);
            dp[i].add(Integer.parseInt(nn.repeat(i)));
            
            for (int j = 1; j < i; j++) {
                for (int prev1 : dp[j]) {
                    for (int prev2 : dp[i-j]) {
                        dp[i].add(prev1 + prev2);
                        dp[i].add(prev1 - prev2);
                        dp[i].add(prev2 - prev1);
                        dp[i].add(prev1 * prev2);
                        
                        if (prev2 != 0) dp[i].add(prev1 / prev2);
                        if (prev1 != 0) dp[i].add(prev2 / prev1);
                    }
                }
            }
            
            if (dp[i].contains(number)) return i;
        }
        
        return -1;
    }
}