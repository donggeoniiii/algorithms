import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Integer> sizes = new ArrayList<>(map.values());
        
        // 내림차순 정렬
        sizes.sort(Collections.reverseOrder());
        
        Iterator<Integer> e = sizes.iterator();
        
        int total = 0;
        while (e.hasNext()) {
            total += e.next();    
            answer++;
            
            if (total >= k) {
                break;
            }
        }
               
        return answer;
    }
}