import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int r = 0;
        int min = sequence.length;
        int total = 0;
        
        for (int l = 0; l < sequence.length; l++) {
            while (total < k && r < sequence.length) {
                total += sequence[r++];
            }

            if (total == k && (r-1) - l < min) {
                answer = new int[]{l, r - 1};
                min = (r-1) - l;
            }
            
            total -= sequence[l];
        }
        
        return answer;
    }
}