import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
     
        Map<Integer, Integer> weightMap = new HashMap<>();
        
        for (int i = 0; i < weights.length; i++) {
            weightMap.put(weights[i], weightMap.getOrDefault(weights[i], 0) + 1);
        }
        
        for (int x : weightMap.keySet()) {
            long xCount = weightMap.get(x);
            
            // 같은 몸무게인 경우
            answer += xCount * (xCount - 1) / 2;
            
            // 다른 몸무게인 경우
            int y;
            for (int i = 0; i < 3; i++) {
                // x : y = 2 : 1 
                if (i == 0) y = 2 * x;
                // x : y = 4 : 3 
                else if (i == 1) { 
                    y = 4 * x / 3;
                    
                    if (x % 3 != 0) continue;
                } 
                // x : y = 2 : 3 
                else {
                    y = 3 * x / 2;
                    
                    if (x % 2 != 0) continue;
                }
                
                long yCount = weightMap.getOrDefault(y, 0);
                
                if (yCount == 0) continue;
                
                answer += xCount * yCount;
            }
        }
        
        return answer;
    }
}