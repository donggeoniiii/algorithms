import java.util.*;

class Solution {
    /*
        입국심사 인원이 최대 10억명이면 완전탐색으로는 불가
        모든 심사가 끝나는 최소 시간을 찾는 것이 더 바람직함
        최대시간: 제일 오래 걸리는 사람한테 전부 받기
    */
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        long answer = right; // 최악의 경우로 초기값 설정
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            // 전원 심사가 가능한 시간을 찾으면 더 적은 시간 내에 가능한지 탐색
            if (check(mid, n, times)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    boolean check(long cur, int n, int[] times) {
        long sum = 0;
        
        for (int i = 0; i < times.length; i++) {
            // 주어진 시간동안 해당 심사관이 처리할 수 있는 사람 수
            sum += cur / times[i];
        }
        
        // 사람 수보다 주어진 시간동안 심사관들이 처리할 수 있는 사람이 많으면 유효
        return sum >= n;
    }
}