import java.util.*;

class Solution {
    Set<Integer> sums;
    
    public int solution(int[] elements) {
        int n = elements.length;
        
        sums = new HashSet<>();
        
        // dp[i] : i에서 시작하는 len 길이 원통수열 부분합
        int[] dp = new int[n];
        
        // 초기화
        for (int i = 0; i < n; i++) {
            dp[i] += elements[i];
            sums.add(dp[i]);
        }
        
        // 길이 증가시키면서 부분합 배열 갱신
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                dp[i] += elements[(i + len - 1) % n];
                
                // i에서 시작하는 len 길이 부분합 생길 때마다 set에 추가
                sums.add(dp[i]);
            }
        }
        
        return sums.size();
    }
}