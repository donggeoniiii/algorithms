import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(priorities[i]);
        }
        
        while (!queue.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                // 이번에 나올 순서가 아니면 패스
                int next = queue.peek();
                if (priorities[i] != next) continue;
                
                // 이번 프로세스 실행
                queue.poll();
                answer++;
                
                // 만약 이번에 실행한 프로세스가 찾던 프로세스면 종료
                if (i == location) return answer;
            }
            
        }
        
        
        return answer;
    }
}