import java.util.*;

class Solution {
    Deque<Integer> sub;
    int answer, cur;
    
    public int solution(int[] order) {
        sub = new ArrayDeque<>();
        
        for (int box = 1; box <= order.length; box++) {
            // 메인에서 바로 실을 수 있으면 
            if (box == order[cur]) {
                // 짐 올리기
                shipNewBox();
                
                // 보조 컨테이너 확인
                checkSub(order);
            } 
            // 메인에서 바로 못 올리면
            else {
                // 제일 마지막에 서브로 빼놓은 짐 실을 수 있는지 보고
                if (!sub.isEmpty() && sub.peekLast() == order[cur]) {
                    shipNewBox();
                    sub.pollLast();
                    checkSub(order);
                } else {
                    // 이번에 못 실은 짐 서브에 올리기
                    sub.offerLast(box);
                }
            }
        }
        
        // 남은 서브 컨테이너 확인
        checkSub(order);
        
        return answer;
    }
    
    void shipNewBox() {
        answer++;
        cur++;
    }
    
    void checkSub(int[] order) {
        while (!sub.isEmpty()) {
            if (sub.peekLast() != order[cur]) {
                return;
            }
            
            shipNewBox();
            sub.pollLast();
        }
    }
}