import java.util.*;

class Solution {
    Queue<String> queue;
    int[] latest10;
    Map<String, Integer> targetInfoIndex;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        init(want);
        
        for (String s : discount) {
            if (beforeFirst10()) {
                addNewDiscountInfo(s);
                
                if (isMatched(latest10, number)) answer++;
    
                continue;
            }
            
            removeExpiredDiscountInfo();
            
            addNewDiscountInfo(s);
            
            if (isMatched(latest10, number)) answer++;
        }
        
        return answer;
    }
    
    void init(String[] want) {
        queue = new ArrayDeque<>();
        latest10 = new int[want.length];
        targetInfoIndex = new HashMap<>();
        
        for (int i = 0; i < want.length; i++) {
            targetInfoIndex.put(want[i], i);
        }
    }
    
    boolean beforeFirst10() {
        return queue.size() < 10;
    }
    
    void addNewDiscountInfo(String newInfo) {
        queue.offer(newInfo);

        if (targetInfoIndex.containsKey(newInfo)) {
            latest10[targetInfoIndex.get(newInfo)]++;
        }
    }
    
    void removeExpiredDiscountInfo() {    
        String item = queue.poll();

        if (targetInfoIndex.containsKey(item)) {
            latest10[targetInfoIndex.get(item)]--;
        }
    }
    
    boolean isMatched(int[] latest10, int[] number) {
        for (int i = 0; i < latest10.length; i++) {
            if (latest10[i] < number[i]) return false;
        }
        
        return true;
    }
}