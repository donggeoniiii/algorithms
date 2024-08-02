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
            double chdx = (t1 / 120D) % 360;
            double cmdx = (t1 / 10D) % 360;
            double csdx = (t1 * 6) % 360;
            
            // 다음 위치 각도로 표시
            double nhdx = ((t1 + 1) / 120D) % 360;
            double nmdx = ((t1 + 1) / 10D) % 360;
            double nsdx = ((t1 + 1) * 6) % 360;
            
            // 다시 정각이 되어 0도가 되는 경우 360도로 계산
            if (nhdx == 0) nhdx = 360;
            if (nmdx == 0) nmdx = 360;
            if (nsdx == 0) nsdx = 360;
            
            // 만약 초침이 시, 분침을 지나면 +1
            if (csdx < chdx && nsdx >= nhdx) answer++;
            if (csdx < cmdx && nsdx >= nmdx) answer++;
            
            // 그중 시, 분침이 겹치는 경우는 카운트 중복 하나 제거
            if (nhdx == nmdx) answer--;;
            
            // 1초 경과
            t1 += 1;
        }
        
        return answer;
    }
}