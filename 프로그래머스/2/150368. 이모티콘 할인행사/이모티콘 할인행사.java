import java.util.*;
        
class Solution {
    static int n, m;
        
    // 이모티콘 가입자 수
    static int emoPlusCnt = 0;

    // 최대 총 매출액
    static int maxPrice = 0;
    
    // 이번에 선택한 이모티콘별 할인 비율
    static int[] selected;
    
    // users[i][0]: 선호할인비율, users[i][1]: 임티 플러스 가입기준 금액
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;

        // 할인율에 따른 최대 가입자수, 최대 금액 찾기
        selected = new int[m];
        findHighestEmoPlus(0, users, emoticons);
        
        return new int[] {emoPlusCnt, maxPrice};
    }
    
    static void findHighestEmoPlus(int cnt, int[][] users, int[] emoticons) {
        // base case: 모든 이모티콘의 할인율이 정해지면 종료
        if (cnt == m) {
            // 이모티콘별 결정된 할인율로 계산하기
            int[] curResult = checkEmoPlusCnt(selected, users, emoticons);
            
            // 최대 갱신여부 확인
            if (curResult[0] > emoPlusCnt) {
                emoPlusCnt = curResult[0];
                maxPrice = curResult[1];
            }
            else if (curResult[0] == emoPlusCnt) {
                maxPrice = Math.max(curResult[1], maxPrice);
            }
            
            return;
        }
        
        // recursive case
        for (int i = 1; i <= 4; i++) {
            // 할인율 정하기
            selected[cnt] = i * 10;
            
            // 다음 이모티콘 할인율 정하러 이동
            findHighestEmoPlus(cnt+1, users, emoticons);
        }
    }
    
    static int[] checkEmoPlusCnt(int[] discount, int[][] users, int[] emoticons) {
        int curEmoPlusCnt = 0;
        int totalPrice = 0;
        
        for (int i = 0; i < n; i++) {
            // 이번 고객이 소비할 금액
            int userTotalPrice = 0;
            
            for (int j = 0; j < m; j++) {
                // 이번 이모티콘 할인비율이 고객의 선호비율 이상이면 구매
                if (discount[j] >= users[i][0]) {
                    userTotalPrice += emoticons[j] * (100 - discount[j]) / 100; 
                }
            }
            
            // 총 금액이 기준 이상이면 임티플러스 구입, 아니면 전체 비용에 합산
            if (userTotalPrice >= users[i][1]) {
                curEmoPlusCnt++;
            }
            else {
                totalPrice += userTotalPrice;
            }
        }
        
        return new int[] {curEmoPlusCnt, totalPrice};
    }
}