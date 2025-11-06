import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        // 입장 시간 기준 정렬
        Arrays.sort(book_time, (o1, o2) -> toTime(o1[0]) - toTime(o2[0]));
        
        // System.out.println(Arrays.deepToString(book_time));
        
        // queue에는 방 빼는 시간 기준 정렬
        PriorityQueue<String[]> pqueue = new PriorityQueue<>((o1, o2) -> toTime(o1[1]) - toTime(o2[1]));
        for (String[] cur : book_time) {
            
            // 이전에 빠질사람 다 내보내기
            while (!pqueue.isEmpty()) {
                // 제일 먼저 퇴실하는 고객 
                String[] prev = pqueue.poll();
                
                // 이번 손님 입장 이후에 나가는 고객이면 아직 입실상태이므로 queue에 추가, loop 탈출
                if (toTime(prev[1]) + 10 > toTime(cur[0])) {
                    pqueue.offer(prev);
                    break;
                }
            }
            
            // 이번 손님 입장
            pqueue.offer(cur);
            
            // 현재 사용중인 방 개수
            answer = Math.max(answer, pqueue.size());
        }
        
        return answer;
    }
    
    int toTime(String s) {
        String[] ht = s.split(":");
        
        return Integer.parseInt(ht[0]) * 60 + Integer.parseInt(ht[1]);
    }
}