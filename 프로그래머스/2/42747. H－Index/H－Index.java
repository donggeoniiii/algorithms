import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        
        // 오름차순 정렬
        Arrays.sort(citations);
        
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) return n - i;
        }
        
        // h-index의 최솟값: 모든 논문이 한번도 인용 안됨
        return 0;
    }
}