import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int n = players.length;
        
        // servers[i]: i시간대에 확정적으로 열려있는 서버
        int[] servers = new int[n];
        Arrays.fill(servers, 1); // 기본 1대는 열려있음
  
        for (int i = 0; i < n; i++) {
            
            // 현재 인원 수 수용에 필요한 총 서버 수
            int req = players[i] / m + 1;
            
            // 해당 서버로 커버가 안되면 증설
            if (req > servers[i]) {
                
                int ext = req - servers[i];
                
                // k 시간동안 운영 예정
                for (int j = 0; j < k; j++) {
                    if (i + j >= n) break;

                    servers[i + j] += ext;
                }
                
                answer += ext;
            }
        }
        
        return answer;
    }
}