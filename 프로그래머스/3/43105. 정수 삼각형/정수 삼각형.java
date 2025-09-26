import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        
        List<int[]> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dp.add(new int[i+1]);
        }
        
        // 초기값 입력
        dp.get(0)[0] = triangle[0][0];
        
        for (int i = 1; i < n; i++) {
            int[] prev = dp.get(i-1);
            int[] cur = dp.get(i);
            for (int j = 0; j < cur.length; j++) {
                int left = (j == 0) ? 0 : prev[j-1];
                int right = (j == cur.length-1) ? 0 : prev[j];
                
                cur[j] = Math.max(left, right) + triangle[i][j];
            }            
        }
        
        int[] floors = dp.get(n-1);
        answer = floors[0];
        for (int i = 1; i < n; i++) {
            answer = Math.max(floors[i], answer);
        }
        
        return answer;
    }
}