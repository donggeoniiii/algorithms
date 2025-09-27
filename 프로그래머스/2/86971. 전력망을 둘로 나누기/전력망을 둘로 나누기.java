import java.util.*;

class Solution {
    static List<Integer>[] adj;
    
    public int solution(int n, int[][] wires) {
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            
            adj[a].add(b);
            adj[b].add(a);
        }
        
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int t1 = searchTree(wire[0], wire[1]);
            int t2 = searchTree(wire[1], wire[0]);
            
            answer = Math.min(answer, Math.abs(t1-t2));
        }
        
        return answer;
    }
    
    int searchTree(int root, int otherRoot) {
        int totalNode = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[adj.length];
        
        queue.offer(root);
        totalNode++;
        visited[root] = true;
        visited[otherRoot] = true; // 넘어가지 않도록 미리 방문 처리
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next : adj[cur]) {
                if (visited[next]) continue;
                
                queue.offer(next);
                totalNode++;
                visited[next] = true;
            }
        }
        
        return totalNode;
    }
}