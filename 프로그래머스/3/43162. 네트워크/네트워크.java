import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            findConnectedComputers(i, computers, visited);
            answer++;
        }
        
        return answer;
    }
    
    void findConnectedComputers(int src, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(src);
        visited[src] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next = 0; next < computers.length; next++) {
                if (computers[cur][next] == 0) continue;
                
                if (visited[next]) continue;
                
                queue.offer(next);
                visited[next] = true;
            }
        }
    }
}