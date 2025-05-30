import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        
        // 배포 순서는 먼저 시작한 애들이 먼저 빠져나가야 함 -> FIFO -> queue
        // 근데 이전작업보다 먼저 끝나는 작업도 배포 예정일은 이전 작업이 끝나야 가능 -> top을 확인할 필요 있음 -> stack
        // 둘다 가능한 arraydeque 사용
        ArrayDeque<Integer> pipeline = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            int remains = 100 - progresses[i];
            int cur = remains / speeds[i];
            
            if (remains % speeds[i] != 0) cur++;
            
            if (pipeline.isEmpty()) {
                pipeline.push(cur);
            } else {
                int top = pipeline.peek();
                if (cur <= top) {
                    pipeline.push(top);
                } else {
                    pipeline.push(cur);
                }
            }
        }
        
        int[] releases = new int[101];
        
        int releaseCnt = 0;
        while (!pipeline.isEmpty()) {
            int date = pipeline.poll();
            releases[date]++;
            
            // 새로운 배포일이 생겨나면 카운트 증가
            if (releases[date] == 1) releaseCnt++;
        }
        
        int[] answer = new int[releaseCnt];
        int idx = 0;
        for (int date = 0; date < releases.length; date++) {
            if (releases[date] > 0) {
                answer[idx++] = releases[date];
            }
        }
        
        return answer;
    }
}