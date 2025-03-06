import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[][] patterns = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        int[] scores = new int[3];
        for (int q = 0; q < answers.length; q++) {
            for (int p = 0; p < patterns.length; p++) {
                if (answers[q] == patterns[p][q % patterns[p].length]) scores[p]++;
            }     
        }
        
        int maxScore = -1;
        int maxCount = 0;
        for (int p = 0; p < patterns.length; p++) {
            if (scores[p] >= maxScore) {
                if (scores[p] > maxScore) {
                    maxScore = scores[p];
                    maxCount = 1;
                } else {
                    maxCount++;
                }
            }
        }
        
        answer = new int[maxCount];
        
        int idx = 0;
        for (int p = 0; p < patterns.length; p++) {
            if (scores[p] == maxScore) answer[idx++] = p+1;
        }
    
        return answer;
    }
}