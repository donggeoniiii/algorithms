class Solution {        
    // attacks[i] : i번째 공격의 공격 시간[0], 피해량[1]
    public int solution(int[] bandage, int health, int[][] attacks) { 
        int answer = 0;
        
        // 시전시간, 초당 회복량, 추가 회복량
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        // 현재 체력
        int hp = health;

        // 초단위로 게임 진행
        int time = 0;
        int snt = 0; // success count, 연속성공 카운트
        int adx = 0; // attack index
        
        // 모든 공격이 끝날 때까지
        while (adx < attacks.length) {
            // 시간 경과, 연속감기 카운트 증가
            time++;
            snt++;
            
            // 만약 공격이 있다면 hp 감소, 연속성공 카운트 초기화
            if (attacks[adx][0] == time) {
                hp -= attacks[adx][1];
                snt = 0;
                
                // 만약 공격했는데 죽을 피면 그대로 종료
                if (hp <= 0) break;
                
                // 공격했으니 공격시점은 다음으로 이동
                adx++;
                
            }
            // 공격이 없었다면 이번 턴 회복하는 양만큼 hp 증가
            else {
                hp = Math.min(health, hp + x);
    
                // 시전시간만큼 채워지면 hp 추가 회복, 카운트 초기화
                if (snt == t) {
                    hp = Math.min(health, hp + y);
                    snt = 0;
                }
            }
        }
        
        // 죽었으면 -1, 살았으면 hp 반환
        answer = (hp <= 0) ? -1 : hp;
        return answer;
    }
}