import java.util.*;

class Solution {
    List<Integer>[] adj;
    int answer;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
        }
        
        findBestRoute(0, 0, 0, info, adj[0]);
        
        return answer;
    }
    
    void findBestRoute(int cur, int sheep, int wolf, int[] info, List<Integer> prevAdj) {
        if (info[cur] == 0) sheep++;
        else wolf++;
        if (wolf >= sheep) return;
        
        answer = Math.max(answer, sheep);
        
        for (int next : prevAdj) {
            List<Integer> newAdj = new ArrayList<>(prevAdj);
            newAdj.remove(Integer.valueOf(next));
            newAdj.addAll(adj[next]);
            findBestRoute(next, sheep, wolf, info, newAdj);
        }
    }
}