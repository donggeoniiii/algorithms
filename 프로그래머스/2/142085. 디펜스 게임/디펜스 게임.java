import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        // 세이브-로드를 한다고 생각하자
        // 막히는 순간에 가장 빡셌던(?) 웨이브를 무적권으로 깨고 되돌아오는 식
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        int hp = n;
        int cheatCount = k;
        for (int cur : enemy) {
            // 무적권 없이 깬 라운드 중 제일 빡셌던 구간을 기억하기 위해 저장
            queue.offer(cur);
            
            // 그냥 깨지는데 까지는 직접 밀기
            if (hp - cur >= 0) {
                hp -= cur;
            } 
            // 안 깨지면 지금까지 중 가장 큰 웨이브를 무적권으로 처리, 상태 복원 후 진행
            else {
                if (cheatCount <= 0) {
                    return answer;
                }
                
                if (cheatCount > 0) {
                    int prevMax = queue.poll();

                    hp += prevMax;
                    hp -= cur;
                    cheatCount--;
                }
            }
            
            answer++;
        }
        
        // 끝까지 다 밀어지면 그냥 enemy 길이 리턴
        return enemy.length;
    }
}