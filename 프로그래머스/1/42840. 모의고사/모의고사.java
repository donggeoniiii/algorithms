import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[][] scores = new int[3][2];
        
        scores[0][0] = 1;
        scores[0][1] = solve1(answers);
        scores[1][0] = 2;
        scores[1][1] = solve2(answers);
        scores[2][0] = 3;
        scores[2][1] = solve3(answers);
        
        // 점수 기준으로 정렬
        Arrays.sort(scores, Comparator.comparingInt((int[] o) -> o[1]));
        
        int maxScore = scores[2][1];
        int maxCount = 0;
        
        for (int i = 0; i < 3; i++) {
            if (scores[i][1] == maxScore) maxCount++;
        }
        
        answer = new int[maxCount];
        
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            if (scores[i][1] == maxScore) {         
                answer[idx++] = scores[i][0];   
            }
        }
        
        return answer;
    }
    
    static int solve1(int[] answers) {
        int score = 0;
        
        for (int q = 1; q <= answers.length; q++) {
            int seq = q % 5;
            int qIdx = q-1;
            
            if (seq == 0) {
                if (answers[qIdx] == 5) score++;
            } else { 
                if (answers[qIdx] == seq) score++;
            }
        }
        
        return score;
    }
    
    static int solve2(int[] answers) {
        int score = 0;
        
        for (int q = 1; q <= answers.length; q++) {
            int seq = q % 8;
            int qIdx = q-1;
                
            switch (seq) {
                case 0:
                    if (answers[qIdx] == 5) score++;
                    break;
                case 2:
                    if (answers[qIdx] == 1) score++;
                    break;
                case 4:
                    if (answers[qIdx] == 3) score++;
                    break;
                case 6:
                    if (answers[qIdx] == 4) score++;
                    break;
                default:
                    if (answers[qIdx] == 2) score++;
                    break;
            }
        }
        
        return score;
    }
    
    static int solve3(int[] answers) {
        int score = 0;
        
        for (int q = 1; q <= answers.length; q++) {
            int seq = q % 10;
            int qIdx = q-1;
            
            if (seq == 0) {
                if (answers[qIdx] == 5) score++;
            } else if (seq <= 2) {
                if (answers[qIdx] == 3) score++;
            } else if (seq <= 4) {
                if (answers[qIdx] == 1) score++;
            } else if (seq <= 6) {
                if (answers[qIdx] == 2) score++;
            } else if (seq <= 8) {
                if (answers[qIdx] == 4) score++;
            } else { // seq == 9
                if (answers[qIdx] == 5) score++;
            }
        }
        
        return score;
    }
}