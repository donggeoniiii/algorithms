import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        int idx = 0;
        int cw = 0;
        
        // int[0]: 트럭 무게, int[1]: 도착 시간
        Queue<int[]> queue = new ArrayDeque<>();
        
        // 첫 트럭 올리기
        queue.offer(new int[]{truck_weights[idx], time + bridge_length});
        cw += truck_weights[idx++];
        time++;
        
        while (!queue.isEmpty()) {
            // 빠지는 트럭 있는지 확인
            if (queue.peek()[1] == time) {
                cw -= queue.poll()[0];
            }
            
            // 다리에 올라갈 수 있으면 새 트럭 넣기
            if (idx < truck_weights.length && queue.size() <= bridge_length 
                && cw + truck_weights[idx] <= weight) {
                queue.offer(new int[]{truck_weights[idx], time + bridge_length});
                cw += truck_weights[idx++];
            }
            
            time++;
        }
        
        return time;
    }
}