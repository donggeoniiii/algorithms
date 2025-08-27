class Solution {
    int videoLen;
    
    int cur;
    
    int start, end;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        videoLen = Integer.parseInt(video_len.substring(0, 2)) * 100 // min
            + Integer.parseInt(video_len.substring(3)); // sec
        
        cur = Integer.parseInt(pos.substring(0, 2)) * 100 // min 
            + Integer.parseInt(pos.substring(3)); // sec
        
        start = Integer.parseInt(op_start.substring(0, 2)) * 100 // min
            + Integer.parseInt(op_start.substring(3)); // sec
        
        end = Integer.parseInt(op_end.substring(0, 2)) * 100 // min
            + Integer.parseInt(op_end.substring(3)); // sec
        
        // 시작지점이 오프닝 스킵구간인지 체크
        cur = checkOpening(cur);
        
        // 명령에 따라 이동
        for (String command : commands) {
            move(command);
        }
        
        if (cur / 100 < 10) answer += "0";
        answer += cur / 100;
        answer += ":";
        if (cur % 100 < 10) answer += "0";
        answer += cur % 100;
        
        return answer;
    }
    
    int checkOpening(int cur) {
        if (start <= cur && cur <= end) {
            return end;
        }
        
        return cur;
    }
    
    void move(String command) {
        int min = cur / 100;
        int sec = cur % 100;
        
        if (command.equals("next")) sec += 10;
        else sec -= 10;
        
        // 시간 포맷 맞추기
        if (sec < 0) {
            min--;
            sec += 60;
        }
        if (sec >= 60) {
            min++;
            sec -= 60;
        }
        
        // 범위 넘어가는 경우 확인 후 조정
        if (min < 0) {
            min = 0;
            sec = 0;
        }
        
        cur = min * 100 + sec;
        if (cur > videoLen) {
            cur = videoLen;
        }
        
        // 스킵범위 해당하면 조정하고 마무리
        cur = checkOpening(cur);
    }
}