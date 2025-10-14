import java.util.*;

class Solution {
    static HashMap<String, Integer> fashions;
    
    public int solution(String[][] clothes) {
        int answer = 1;
        
        fashions = new HashMap<>();
        
        for (String[] item : clothes) {
            String category = item[1];
            
            // 해당 카테고리 무선택도 한가지 경우의 수
            fashions.put(category, fashions.getOrDefault(category, 1) + 1);
        }
        
        for (String category : fashions.keySet()) {
            answer *= fashions.get(category);
        }
        
        // 아무것도 안고르는 경우 하나 제외
        return answer - 1;
    }
}