import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        
        HashMap<String, Integer> nameToIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nameToIdx.put(friends[i], i);
        }

        // present[i][j] : i에게 j가 준 선물 수
        int[][] present = new int[n][n];
        
        // 선물지수
        int[] giftPoint = new int[n];
        
        for (String s : gifts) {
            StringTokenizer st = new StringTokenizer(s);
            int adx = nameToIdx.get(st.nextToken());
            int bdx = nameToIdx.get(st.nextToken());
            present[adx][bdx]++;
            giftPoint[adx]++;
            giftPoint[bdx]--;
        }
        
        // 다음달에 선물을 얼마나 받을 지
        int[] next = new int[n];
        
        // 서로 간에 선물 상황을 보고 판단
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (present[i][j] > present[j][i]) next[i]++;
                else if (present[i][j] < present[j][i]) next[j]++;
                // 이전까지 선물 주고 받은 상황이 같으면 선물지수 확인
                else {
                    // 선물지수마저 같으면 다음달에 선물 안 주고 받음
                    if (giftPoint[i] == giftPoint[j]) continue;
                    else if (giftPoint[i] > giftPoint[j]) next[i]++;
                    else next[j]++;
                }
            }
        }
        
        Arrays.sort(next);
        answer = next[n-1];
        
        return answer;
    }
}