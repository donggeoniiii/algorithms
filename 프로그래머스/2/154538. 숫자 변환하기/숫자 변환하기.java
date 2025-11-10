import java.util.*;

class Solution {
    // 탐색 최대 범위: y 최댓값에서 3배 하는 경우가 최대
    static final int MAX = 1_000_000 * 3;
    
    public int solution(int x, int y, int n) {
        
        return findY(x, y, n);
    }
    
    int findY(int x, int y, int n) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1];
        
        queue.offer(new int[]{x, 0});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int idx = cur[0];
            int count = cur[1];
            
            if (idx == y) return count;
            
            int next;
            for (int i = 0; i < 3; i++) {
                if (i == 0) next = idx + n;
                else if (i == 1) next = idx * 2;
                else next = idx * 3;
                
                // x는 자연수이고 더하거나 곱하기만 하므로 가능성 x
                if (next > y) continue;
                
                if (visited[next]) continue;
                
                queue.offer(new int[]{next, count + 1});
                visited[next] = true;
            }
        }
        
        return -1;
    }
}