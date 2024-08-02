class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        // 시작 시간, 종료 시간 초단위로 변환
        int t1 = h1 * 60 * 60 + m1 * 60 + s1;
        int t2 = h2 * 60 * 60 + m2 * 60 + s2;
        
        // 만약 시작 시간이 자정이나 정오면 카운트하고 시작(이미 만나있음)
        if (t1 == 0 || t1 == 43200) answer++;
        
        // 시작시간부터 종료시간까지 초단위 진행
        while (t1 < t2) {
            // 현재 위치 각도로 표시
            double curHourIdx = (t1 / 120D) % 360;
            double curMinuteIdx = (t1 / 10D) % 360;
            double curSecondIdx = (t1 * 6) % 360;
            
            // 다음 위치 각도로 표시
            double nextHourIdx = ((t1 + 1) / 120D) % 360;
            double nextMinuteIdx = ((t1 + 1) / 10D) % 360;
            double nextSecondIdx = ((t1 + 1) * 6) % 360;
            
            // 다시 정각이 되어 0도가 되는 경우 360도로 계산
            if (nextHourIdx == 0) nextHourIdx = 360;
            if (nextMinuteIdx == 0) nextMinuteIdx = 360;
            if (nextSecondIdx == 0) nextSecondIdx = 360;
            
            // 만약 초침이 시, 분침을 지나면 +1
            if (curSecondIdx < curHourIdx && nextSecondIdx >= nextHourIdx) answer++;
            if (curSecondIdx < curMinuteIdx && nextSecondIdx >= nextMinuteIdx) answer++;
            
            // 그중 시, 분침이 겹치는 경우는 카운트 중복 하나 제거
            if (nextHourIdx == nextMinuteIdx) answer--;;
            
            // 1초 경과
            t1 += 1;
        }
        
        return answer;
    }
}