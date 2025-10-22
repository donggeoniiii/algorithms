import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        boolean[] isChosen = new boolean[n+1];
            
        checkPossibleCases(1, 0, n, isChosen, q, ans);
        
        return answer;
    }
    
    void checkPossibleCases(int start, int count, int n, boolean[] isChosen, int[][] q, int[] ans) {
        if (count >= 5) {
            if (isPossible(isChosen, q, ans)) {
                answer++;
            }
            
            return;
        }
        
        // nC5
        for (int i = start; i <= n; i++) {
            isChosen[i] = true;
            checkPossibleCases(i + 1, count + 1, n, isChosen, q, ans);
            isChosen[i] = false;
        }
    }
    
    boolean isPossible(boolean[] isChosen, int[][] q, int[] ans) {
        Set<Integer> choices = new HashSet<>();
        
        for (int i = 1; i < isChosen.length; i++) {
            if (isChosen[i]) choices.add(i);
        }
        
        for (int i = 0; i < q.length; i++) {
            int target = ans[i];
            int cur = 0;
            
            for (int trial : q[i]) {
                if (choices.contains(trial)) cur++;
            }
            
            if (cur != target) {
                return false;
            }
        }
        
        return true;
    }
}