import java.util.*;

class Solution {
    static List<Integer>[] adj;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] v : edge) {
            adj[v[0]].add(v[1]);
            adj[v[1]].add(v[0]);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        int maxDist = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next : adj[cur]) {
                if (dist[next] >= 0) continue;
                
                dist[next] = dist[cur] + 1;
                queue.offer(next);
                
                maxDist = Math.max(maxDist, dist[next]);
            }
        }
        
        for (int node = 1; node <= n; node++) {
            if (dist[node] == maxDist) answer++;
        }
        
        return answer;
    }
}