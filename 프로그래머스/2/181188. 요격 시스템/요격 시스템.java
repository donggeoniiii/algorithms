import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 미사일의 e 좌표 순으로 정렬 -> 최대한 e 좌표쪽에 맞추는게 최소
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        // 가장 마지막 요격 미사일의 위치
        int prev = 0;
        
        for (int[] missile : targets) {
            // 이전 요격 미사일이 (s, e)로 처리 가능하면 패스
            if (prev > missile[0]) continue;
                
            // 안되면 새 요격 미사일 발사 -> 이번 미사일의 끝위치
            answer++;
            prev = missile[1];
        }
        
        return answer;
    }
}