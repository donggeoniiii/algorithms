import java.util.*;

class Solution {
    static int[] network;
    static List<Integer>[] adj;
    
    public int solution(int n, int[][] computers) { // computers: 인접 정보
        int answer = 0;
        
        network = new int[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (network[i] > 0) continue;
            
            check(++answer, i);
        }
        
        return answer;
    }
    
    static void check(int networkIdx, int src) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(src);
        network[src] = networkIdx;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next : adj[cur]) {
                if (network[next] > 0) continue;
                
                queue.offer(next);
                network[next] = networkIdx;
            }
        }
    }
}