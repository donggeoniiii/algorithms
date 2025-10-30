import java.util.*;

class Solution {
    class Study {
        String name;
        int duration;
        
        Study(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }
    }
    
    public String[] solution(String[][] plans) {
        List<String> answers = new ArrayList<>();
        
        // 시작시간 순 정렬
        Arrays.sort(plans, Comparator.comparing(o -> o[1]));
        
        Deque<Study> stack = new ArrayDeque<>();
        
        for (int i = 0; i < plans.length; i++) {
            // 새로운 과제가 시작되면 새로운 과제부터 시작
            String name = plans[i][0];
            int duration = Integer.parseInt(plans[i][2]);            
            stack.offerLast(new Study(name, duration));
            
            int next = 100000; // 임의의 큰 수로 초기화(마지막 과제 처리 위함)
            if (i + 1 < plans.length) next = getStartTime(plans[i+1]);
            
            // 다음 과제 시작 전까지 쌓인 과제 처리
            int gap = next - getStartTime(plans[i]);
            while (!stack.isEmpty() && gap > 0) {
                Study s = stack.pollLast();
                
                if (gap >= s.duration) {
                    gap -= s.duration;
                    s.duration = 0;
                } else {
                    s.duration -= gap;
                    gap = 0;
                }

                // 마쳤으면 완료 리스트에 추가
                if (s.duration == 0) {
                    answers.add(s.name);            
                } else {
                    stack.offerLast(s);                               
                }
            }
        }
        
        
        return answers.toArray(String[]::new);
    }
    
    // 시간차이 계산(분단위)
    int getStartTime(String[] plan) {
        String[] startTime = plan[1].split(":");
        return Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
    }
}