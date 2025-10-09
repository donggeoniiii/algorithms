import java.util.PriorityQueue;
import java.util.List;
import java.util.Arrays;

import java.util.stream.Collectors;

class Solution {
    public int solution(int[] scoville, int k) {
        int answer = 0;
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();
        List<Integer> scovilles = Arrays.stream(scoville)
                                        .boxed()
                                        .collect(Collectors.toList());
        pqueue.addAll(scovilles);
        
        while (pqueue.size() >= 2) {
            int first = pqueue.poll();
            
            // 가장 안매운 음식의 스코빌 지수가 k 이상이면 종료
            if (first >= k) break;
            
            int second = pqueue.poll();
            
            pqueue.offer(first + second * 2);
            
            answer++;
        }
        
        // 반복문을 통과했는데 최소값이 k보다 작으면 불가능
        if (pqueue.poll() < k) return -1;
        
        return answer;
    }
}