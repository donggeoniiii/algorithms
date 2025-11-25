import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int n = topping.length;
        
        Map<Integer, Integer> toppingRemains = new HashMap<>();
        for (int t : topping) {
            toppingRemains.put(t, toppingRemains.getOrDefault(t, 0) + 1);
        }
        
        Map<Integer, Integer> slicedCake = new HashMap<>();
        for (int t : topping) {
            slicedCake.put(t, slicedCake.getOrDefault(t, 0) + 1);
            
            if (toppingRemains.getOrDefault(t, 1) == 1) {
                toppingRemains.remove(t);
            } else {
                toppingRemains.put(t, toppingRemains.get(t) - 1);
            }   
            
            if (slicedCake.keySet().size() == toppingRemains.keySet().size()) {
                answer++;
            }
        }
        
        return answer;
    }
}