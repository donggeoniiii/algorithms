import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        
        // 모든 원소가 100 이하이므로 최대 100 + 100 = 200
        boolean[] isExist = new boolean[201];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                isExist[numbers[i] + numbers[j]] = true;
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 201; i++) {
            if (isExist[i]) answer.add(i);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}