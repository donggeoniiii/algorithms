import java.util.*;

class Solution {
    static String[] answer;
    static boolean[] isUsed;
    
    public String[] solution(String[][] tickets) {
        answer = new String[1];
        isUsed = new boolean[tickets.length];
        
		List<String> route = new ArrayList<>();
		route.add("ICN");
		findPossibleRoute(tickets, 0, route.get(0), isUsed, route);

        return answer;
    }
    
    void findPossibleRoute(String[][] tickets, int count, String cur, boolean[] isUsed, List<String> route) {
        if (count >= tickets.length) {
            String[] curRoute = route.toArray(String[]::new);
            
            if (answer.length == 1) {
                answer = curRoute;
                return;
            }
            
            // 만약 이번 루트가 사전순으로 먼저 오면 갱신
            if (Arrays.compare(answer, curRoute) >= 0) answer = curRoute;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            // 이미 탑승한 동선이면 스킵
            if (isUsed[i]) continue;
            
            String src = tickets[i][0];
            String dest = tickets[i][1];
            
            // 출발지점이 여기가 아니면 스킵
            if (!src.equals(cur)) continue;
            
            route.add(dest);
            isUsed[i] = true;
            findPossibleRoute(tickets, count+1, dest, isUsed, route);
            
            // 다른 루트 탐색을 위해 초기화
            route.remove(route.size() - 1);
            isUsed[i] = false;
        }
    }
}