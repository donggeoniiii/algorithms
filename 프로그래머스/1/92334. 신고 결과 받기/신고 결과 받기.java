import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        
        HashMap<String, Integer> reportIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            reportIndex.put(id_list[i], i);
        }
        
        boolean[][] isReported = new boolean[n][n]; // isReported[a][b]: a가 b를 신고했는지
        int[] reportCount = new int[n]; // reportCount[i]: i번째 index의 id가 신고받은 횟수
        int[] answer = new int[n];
        
        for (String s : report) {
            String[] ids = s.split(" ");
            int a = reportIndex.get(ids[0]);
            int b = reportIndex.get(ids[1]);

            if (isReported[a][b]) continue;
            
            isReported[a][b] = true;
            reportCount[b]++;
        }
        
        // 신고 k회 누적 시 신고자들에게 해당 id 정지사실 발송
        for (int b = 0; b < n; b++) {
            if (reportCount[b] < k) continue;
            
            for (int a = 0; a < n; a++) {
                if (isReported[a][b]) answer[a]++;
            }
        }
        
        return answer;
    }
}