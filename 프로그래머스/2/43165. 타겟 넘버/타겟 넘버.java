class Solution {
    static int answer;
    static int[] dt = {-1, 1};
    
    public int solution(int[] numbers, int target) {
        selectSign(numbers, target, 0, 0);
        
        return answer;
    }
    
    void selectSign(int[] numbers, int target, int cur, int idx) {
        if (idx >= numbers.length) {
            if (cur == target) answer++;
            return;
        }
        
        for (int i = 0; i < 2; i++) {
            int next = numbers[idx] * dt[i];
            
            selectSign(numbers, target, cur + next, idx+1);
        }
    }
}