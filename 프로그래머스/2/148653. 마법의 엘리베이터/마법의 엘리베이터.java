import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        int size = String.valueOf(storey).length();
        
        int cur = storey;
        int[] digit = new int[size];
        
        /*
        각 자릿수 별로 최소로 누르는 것이 전체로 봐도 최소가 됨
        각 자릿수 별로 0을 만들려면 반올림이 제일 빠르다
        다만 해당 자리가 5인 경우 유의
        ex) 
        95 -> 내림하면 14번, 반올림하면 6번
        85 -> 내림하면 13번, 반올림하면 7번
        ...
        55 -> 내림하면 10번, 반올림하면 10번(동일)
        45 -> 내림하면 9번, 반올림하면 10번(손해)
        35 -> 내림하면 8번, 반올림하면 9번
        */
        for (int i = 0; i < digit.length; i++) {
            digit[i] = cur % 10;
            cur /= 10;
        }
        
        for (int i = 0; i < digit.length; i++) {
            // 마지막 자리인 경우
            if (i + 1 == digit.length) {
                // 반올림 or 내림 중 적게 누르는 쪽으로 처리
                answer += Math.min((10 - digit[i]) + 1, digit[i]);
            } 
            // 상위 자리가 있는 경우
            else { 
                if (digit[i] < 5) {
                    answer += digit[i];
                } 
                // 현재 자리값이 5이면 상위 자리 값에 따라 달라짐
                else if (digit[i] == 5) { 
                    if (digit[i+1] <= 4) {
                        answer += digit[i];
                    } else { // digit[i+1] > 4
                        answer += 10 - digit[i];
                        digit[i+1]++;
                    }
                } else { // digit[i] > 5
                    answer += 10 - digit[i];
                    digit[i+1]++;
                }
            }
        }
    
        return answer;
    }
}