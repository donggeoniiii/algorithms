class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int n = schedules.length; // 직원수
        boolean[] isFailed = new boolean[n]; // startday 이후 출근 성공횟수
        
        // 시작요일부터 일주일간 진행
        int day = startday;
        for (int i = 0; i < 7; i++) {
            // 오늘 요일 확인
            day %= 7;
            if (day == 0) day = 7;

            // 주말이면 이벤트 해당 없음
            if (day >= 6) {
                day++;
                continue;
            }
            
            for (int j = 0; j < n; j++) {
                if (isFailed[j]) continue;
                
                // 출근 희망시간과 비교
                int targetTime = convertTime(schedules[j]);
                
                // 희망시간 이후 출근이면 실패 체크
                if (timelogs[j][i] > targetTime) {
                    isFailed[j] = true;
                }
            }   
            
            day++;
        }
        
        for (int i = 0; i < n; i++) {
            if (isFailed[i]) continue;
            
            answer++;
        }
        
        return answer;
    }
    
    int convertTime(int time) {
        time += 10;
        
        // 분 단위 넘기기
        if (time % 100 >= 60) {
            time -= 60;
            time += 100;
        }
        
        return time;
    }
}