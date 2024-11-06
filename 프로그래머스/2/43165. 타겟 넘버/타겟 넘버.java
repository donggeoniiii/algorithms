class Solution {
    static int answer, n;
    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;
        
        int[] selected = new int[n];
        findTargetCase(numbers, target, selected, 0);
        
        return answer;
    }
    
    static void findTargetCase(int[] numbers, int target, int[] selected, int count) {
        if (count == n) {
            int total = 0;
            for (int i = 0; i < n; i++) {
                total += selected[i] * numbers[i];
            }
            
            if (total == target) answer++;
            return;
        }
        
        // 더하고 빼는 두 경우로 나눠서 진행
        selected[count] = 1;
        findTargetCase(numbers, target, selected, count+1);
        selected[count] = -1;
        findTargetCase(numbers, target, selected, count+1);
    }
}