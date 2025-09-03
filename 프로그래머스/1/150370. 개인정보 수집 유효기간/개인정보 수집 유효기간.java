import java.util.*;

class Solution {    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int n = privacies.length;
        boolean[] isExpired = new boolean[n];
        
        // 파기해야 할 정보를 분별하기 위해 숫자 8자리로 날짜 표현
        int t = convertDate(today);
        
        HashMap<String, Integer> termMonths = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] curTerm = terms[i].split(" ");
            String type = curTerm[0];
            int month = Integer.parseInt(curTerm[1]);
            termMonths.put(type, month);
        }
        
        int expireCnt = 0;
        for (int i = 0; i < n; i++) {
            String[] cur = privacies[i].split(" ");
            int date = convertDate(cur[0]);
            int month = termMonths.get(cur[1]);
            
            int cy = date / 10000;
            int cm = (date % 10000) / 100;
            int cd = date % 100;
        
            cm += month;
            
            if (cm > 12) {
                cy += cm / 12;
                
                if (cm % 12 == 0) {
                    cy--;
                    cm = 12;
                } else {            
                    cm %= 12;     
                }
            }
            
            int newDate = cy * 10000 + cm * 100 + cd;
            
            if (t >= newDate) {
                isExpired[i] = true;
                expireCnt++;
            }
        }
        
        int[] answer = new int[expireCnt];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (isExpired[i]) answer[idx++] = i+1;
        }
        
        return answer;
    }
    
    int convertDate(String date) {
        
        int ty = Integer.parseInt(date.substring(0, 4));
        int tm = Integer.parseInt(date.substring(5, 7));
        int td = Integer.parseInt(date.substring(8));
        
        return ty * 10000 + tm * 100 + td;
    }
}