import java.util.*;

class Solution {
    Map<String, Integer> yearningIndex;
    
    void init(String[] name) {
        yearningIndex = new HashMap<>();
        
        for (int i = 0; i < name.length; i++) {
            yearningIndex.put(name[i], i);
        }
    }
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        init(name);
        
        for (int i = 0; i < photo.length; i++) {
            for (String person : photo[i]) {
                if (!yearningIndex.containsKey(person)) continue;
                
                answer[i] += yearning[yearningIndex.get(person)];
            }
        }
        
        return answer;
    }
}