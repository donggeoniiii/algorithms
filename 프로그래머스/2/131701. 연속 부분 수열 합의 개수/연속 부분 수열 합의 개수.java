import java.util.*;

class Solution {
    Set<Integer> sums;
    
    public int solution(int[] elements) {
        int n = elements.length;
        
        sums = new HashSet<>();
        
        // 최대 길이가 1000 -> 길이 1짜리 1000개 + 2짜리 1000개 + 3짜리 1000개 + ... + 1000 * 1000
        // ((n * (n + 1)) / 2) * n -> O(n^3)
        // 최대 1000이면 터짐
        for (int start = 0; start < n; start++) {
            for (int len = 1; len <= n; len++) {
                int curSum = 0;
                
                for (int i = 0; i < len; i++) {
                    int cur = start + i;
                    if (cur >= n) {
                        cur -= n;
                    }
                    curSum += elements[cur];
                }
                
                sums.add(curSum);                            
            }
        }
        
        return sums.size();
    }
}