import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        // 각 값은 이전까지 최댓값이 중요
        Deque<Integer> stack = new ArrayDeque<>();
        answer[n-1] = -1;
        stack.offerLast(numbers[n-1]);
        
        for (int i = n-2; i >= 0; i--) {
            int cur = numbers[i];
            
            while (!stack.isEmpty() && stack.peekLast() <= cur) {
                stack.pollLast();
            }
            answer[i] = (stack.isEmpty()) ? -1 : stack.peekLast();
            
            stack.offerLast(cur);
        }
        
        return answer;
    }
}