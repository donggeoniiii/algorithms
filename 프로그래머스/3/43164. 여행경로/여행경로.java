import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        List<String> answer = new ArrayList<>();
        boolean[] visited = new boolean[tickets.length];
        findPossibleRoutes(0, "ICN", "ICN", tickets, answer, visited);
        
        answer.sort(null);
        return answer.get(0).split(" ");
    }
    
    void findPossibleRoutes(int count, String src, String route,
                            String[][] tickets, List<String> answer, boolean[] visited) {
        if (count >= tickets.length) {
            answer.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;
            
            if (!src.equals(tickets[i][0])) continue;
            
            visited[i] = true;
            findPossibleRoutes(count + 1, tickets[i][1], route + " " + tickets[i][1],
                             tickets, answer, visited);
            visited[i] = false;
        }
    }
}